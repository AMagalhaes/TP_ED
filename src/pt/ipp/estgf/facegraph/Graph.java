package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Heap.LinkedHeap;
import pt.ipp.estgf.facegraph.Interfaces.*;
import pt.ipp.estgf.facegraph.entities.Aresta;
import pt.ipp.estgf.facegraph.entities.Vertice;
import pt.ipp.estgf.facegraph.lists.ArrayUnorderedList;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.EmptyQueueException;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.linkedQueue.LinkedQueue;
import pt.ipp.estgf.facegraph.linkedStack.LinkedStack;

import java.util.Iterator;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class Graph<V extends VertexInterface, E extends EdgeInterface> implements GraphADT<V> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;                // number of the vertices in the graph
    protected int numEdges;                   // number of edges
    protected E[][] adjMatrix;                // adjacency matrix
    protected V[] vertices;                   // values of vertices
    protected int v1Pos, v2Pos;


    /**
     * Creates an empty graph
     */
    //@SuppressWarnings("unchecked")
    public Graph() {
        this.numVertices = 0;
        this.numEdges = 0;
        this.adjMatrix = (E[][]) (new Aresta[DEFAULT_CAPACITY][DEFAULT_CAPACITY]);
        this.vertices = (V[]) (new Vertice[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph (unidirecional)
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(V vertex1, V vertex2) throws IlegalArgumentException {
        // obtem o index dos vertices
        v1Pos = getIndex(vertex1);
        v2Pos = getIndex(vertex2);

        // caso o vertices não existam lança uma exception
        if (v2Pos == -1 || v2Pos == -1) {
            throw new IlegalArgumentException("O vertice não existe");
        }

        // verifica se os vertices são iguais
        if (v2Pos == v1Pos) {
            throw new IlegalArgumentException("Não pode criar uma ligação entre a mesma pessoa.");
        }

        // cria a Aresta
        Aresta newAresta = new Aresta(0);

        // impede a criação de arestas duplicadas
        if (this.adjMatrix[v1Pos][v2Pos] == null) {
            this.adjMatrix[v1Pos][v2Pos] = (E) newAresta;
            this.adjMatrix[v2Pos][v1Pos] = (E) newAresta;
            this.numEdges++;
        } else {
            throw new IlegalArgumentException("aresta duplicada" + v1Pos + " " + v2Pos);
        }
    }


    /**
     * verifica se existe o vertice,
     *
     * @param index posição do vertice no array vertices
     * @return true se existir o vertice, falso caso contrario
     */
    protected boolean indexIsValid(int index) throws IlegalArgumentException {
        // para ter uma posição valida, o index tem de estar
        // vazio,ser maior que zero e menor que a ultima posição do array
        if (index < 0 || index >= vertices.length || vertices[index] == null) {
            return false;
        }

        return true;
    }

    /**
     * Obtem o index de um vertice
     *
     * @param vertice
     * @return posição do vertice se existir,caso não exista chama exception
     */
    protected int getIndex(V vertice) {
        // tenta encontrar o vertice
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos] != null && vertices[pos].equals(vertice)) {
                return pos;
            }
        }

        // o vertice não existe
        return -1;
    }


    /**
     * Remove the edge of the given vertices
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(V vertex1, V vertex2) throws IlegalArgumentException {
        // obtem o index dos vertices
        v1Pos = getIndex(vertex1);
        v2Pos = getIndex(vertex2);

        // verifica se os vertices existem
        if (v1Pos == -1 || v2Pos == -1) {
            throw new IlegalArgumentException("O vertice não existe");
        }

        // apaga a aresta
        if (this.adjMatrix[v1Pos][v2Pos] != null) {
            this.adjMatrix[v1Pos][v2Pos] = null;
            this.adjMatrix[v2Pos][v1Pos] = null;
            this.numEdges--;
        }
    }


    /**
     * Add the given  vertex to the graph
     *
     * @param vertex the vertex to be added to this graph
     */
    @Override
    public void addVertex(V vertex) {
        // verifica se é necessario expandir o array
        if (numVertices == (vertices.length - 1)) {
            expandCapacity();
        }

        // adiciona o novo vertice
        vertices[numVertices] = vertex;
        numVertices++;

        // garante que não existe aresta ligadas a este novo vertice
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[i][numVertices] = null;
            adjMatrix[numVertices][i] = null;
        }
    }


    /**
     * Remove the given vertex of the graph
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(V vertex) throws IlegalArgumentException {
        // obtem a posição do vertice
        int pos = getIndex(vertex);

        // verifica se o vertice existe
        if (pos == -1) {
            throw new IlegalArgumentException("vertice não encontrado");
        }

        // percorrer a matriz para anular as arestas deste vertice
        for (int i = 0; i < vertices.length; i++) {
            // remove a aresta caso exista
            if (this.adjMatrix[pos][i] != null) {
                this.adjMatrix[pos][i] = null;
                this.adjMatrix[i][pos] = null;
                this.numEdges--;
            }
        }

        // recuar os vertices para a posição vazia por remover o vertice
        for (int posV = pos; posV < numVertices; posV++) {
            vertices[posV] = vertices[posV + 1];
        }

        for (int x = pos + 1; x < numVertices; x++) {
            for (int y = pos + 1; y < numVertices; y++) {
                adjMatrix[x - 1][y - 1] = adjMatrix[x][y];
            }
        }

        // atualiza o numero de vertices
        this.numVertices--;
    }

    /**
     * Return a lista dos vertices da travessia em largura
     *
     * @param startVertex the starting vertex
     * @return
     */
    @Override
    public Iterator<V> iteratorBFS(V startVertex) throws IlegalArgumentException {
        Integer x;
        int vertPos = getIndex(startVertex);
        LinkedQueue<Integer> transversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<V> resultList = new ArrayUnorderedList<V>(DEFAULT_CAPACITY);

        // verifica se o vertice é valido
        if (!this.indexIsValid(vertPos)) {
            return resultList.iterator();
        }

        // inicializa todos os vertices visitados a false
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        // adiciona o vertice de origem a queue e marca-o como visitado
        transversalQueue.enqueue(new Integer(vertPos));
        visited[vertPos] = true;


        try {
            // faz a ação a seguir até a queue estar vazia
            while (!transversalQueue.isEmpty()) {
                // obtem o elemento da queue e adiciona o vertice a lista final
                x = transversalQueue.dequeue();
                resultList.addToRear(vertices[x]);

                // encontra todos os vertices adjacentes de x que não foram visitados  e adiciona-os à queue
                for (int i = 0; i < numVertices; i++) {
                    if (visited[i] != true && this.adjMatrix[x.intValue()][i] != null) {
                        transversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }

        // returna a lista de resultados
        return resultList.iterator();
    }

    @Override
    public Iterator<V> iteratorDFS(V startVertex) throws EmptyCollectionException, IlegalArgumentException {
        Integer x;
        boolean found;
        LinkedStack<Integer> transversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<V> resultList = new ArrayUnorderedList<V>(DEFAULT_CAPACITY);

        // verifica se o vertice de origem é valido
        if (!this.indexIsValid(getIndex(startVertex))) {
            return resultList.iterator();
        }

        // inicializa todos os vertices visitados a false
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        // adiciona o vertice de origem a stack, a lista de resultados e marca-o como visitado
        transversalStack.push(new Integer(getIndex(startVertex)));
        resultList.addToRear(vertices[getIndex(startVertex)]);
        visited[getIndex(startVertex)] = true;

        // faz a ação a seguir até a stack ficar vazia
        while (!transversalStack.isEmpty()) {
            // obtem o elemento do topo da stack
            x = transversalStack.peek();
            found = false;

            // adiciona todos os vertices adjacentes a X à stack e
            // a lista de resultados
            // e informa que foram encontrados adjacentes
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] != null && !visited[i]) {
                    transversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }

            // caso não tenha sido encontrado faz o pop da stack
            if (!found && !transversalStack.isEmpty()) {
                transversalStack.pop();
            }
        }

        // return a lista de resultados
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(V startVertex, V targetVertex) throws EmptyCollectionException, IlegalArgumentException, EmptyQueueException {
        int index;
        double weight;
        int endIndex = getIndex(targetVertex);
        int startIndex = getIndex(startVertex);
        int[] predecessor = new int[this.numVertices];
        StackADT<Integer> stack = new LinkedStack<Integer>();
        HeapADT<Double> traversalMinHeap = new LinkedHeap<Double>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<Integer>(DEFAULT_CAPACITY);

        // inicializa o array que contem a soma dos pesos a 0
        double[] pathWeight = new double[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        // inicializa o array de vertices visitados a false
        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        // verifica se os vertices são validos
        if (!this.indexIsValid(startIndex) || !this.indexIsValid(endIndex) || (startIndex == endIndex)) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        // adiciona os pesos dos vertices adjacentes ao array
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                if (this.adjMatrix[startIndex][i] != null) {
                    pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i].getWeight();
                } else {
                    pathWeight[i] = Double.POSITIVE_INFINITY;
                }

                predecessor[i] = startIndex;
                traversalMinHeap.addElement(new Double(pathWeight[i]));
            }
        }

        do {
            try {
                weight = (traversalMinHeap.removeMin()).doubleValue();
            } catch (EmptyCollectionException ex) {
            }
            traversalMinHeap.removeAll();

            if (weight == Double.POSITIVE_INFINITY) {
                return resultList.iterator();
            } else {
                index = getIndexOfTheVertice(pathWeight, weight, visited);

                visited[index] = true;
            }

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[index][i] == null) {
                    if (!visited[i]) {
                        traversalMinHeap.addElement(new Double(pathWeight[i]));
                    }

                    continue;
                }

                if (!visited[i]) {
                    if ((this.adjMatrix[index][i].getWeight() < Double.POSITIVE_INFINITY) && (pathWeight[index] + this.adjMatrix[index][i].getWeight()) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + this.adjMatrix[index][i].getWeight();
                        predecessor[i] = index;
                    }

                    traversalMinHeap.addElement(new Double(pathWeight[i]));
                }
            }

        } while (!traversalMinHeap.isEmpty() && !visited[endIndex]);

        index = endIndex;
        stack.push(new Integer(index));

        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            try {
                resultList.addToRear((stack.pop()));
            } catch (EmptyCollectionException ex) {
            }
        }

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty() {
        if (numVertices == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnected() throws IlegalArgumentException {
        // Verifica se o grafo não está vazio
        if (this.isEmpty()) {
            try {
                throw new EmptyCollectionException("O grafo está vazio!");
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        }

        int vertexIndex = -1;

        // Procura o primeiro vertice da lista
        for (int inIdex = 0; inIdex < this.vertices.length; inIdex++) {
            if (this.vertices[inIdex] != null) {
                vertexIndex = inIdex;
                break;
            }
        }

        Iterator<V> it = this.iteratorBFS((V) vertices[vertexIndex]);
        int count = 0;

        // Conta o numero de elementos encontrados na pesquisa
        while (it.hasNext()) {
            it.next();
            count++;
        }

        // Caso o numero de vestices sejá igual ao numero de elementos encontrados na iteração,
        // então é um grafo conexo
        if (this.numVertices == count) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public String toString() {
        String adjMatrixTooString = " ";
        System.out.print("   ");
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos] != null) {
                adjMatrixTooString += vertices[pos].getNome() + "   ";
            }
        }
        adjMatrixTooString += "\n";
        for (int linha = 0; linha < vertices.length; linha++) {
            if (vertices[linha] != null) {
                adjMatrixTooString += vertices[linha].getNome() + "  ";

                for (int coluna = 0; coluna < vertices.length; coluna++) {
                    if (vertices[coluna] != null)
                        if (adjMatrix[linha][coluna] == null) {
                            adjMatrixTooString += "0.0 ";
                        } else {
                            adjMatrixTooString += adjMatrix[linha][coluna].getWeight() + " ";
                        }
                }
                adjMatrixTooString += "\n";
            }
        }
        return adjMatrixTooString;
    }


    private void expandCapacity() {
        //redimensiona o array de vertices
        int oldVerticesLength = this.vertices.length;
        V[] newVerteces = (V[]) (new Vertice[this.vertices.length * 2]);
        System.arraycopy(this.vertices, 0, newVerteces, 0, numVertices);
        this.vertices = newVerteces;

        //redimensiona a matriz adjacente
        E[][] auxAdjMatrix = (E[][]) new Aresta[oldVerticesLength * 2][oldVerticesLength * 2];
        for (int i = 0; i < oldVerticesLength; i++) {
            for (int x = 0; x < oldVerticesLength; x++) {
                auxAdjMatrix[i][x] = adjMatrix[x][i];
            }
        }
        this.adjMatrix = auxAdjMatrix;
    }

    protected int getIndexOfTheVertice(double pathWeight[], double weight, boolean[] visited) {
        for (int index = 0; index < this.numVertices; index++) {
            if ((pathWeight[index] == weight) && !visited[index]) {
                for (int j = 0; j < this.numVertices; j++) {
                    if (this.adjMatrix[j][index] == null) {
                        continue;
                    }

                    if ((adjMatrix[j][index].getWeight() > 0) && visited[j]) {
                        return index;
                    }
                }
            }
        }
        return -1;
    }
}

