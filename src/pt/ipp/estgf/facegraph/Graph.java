package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Interfaces.GraphADT;
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

public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;                 // number of the vertices in the graph
    protected int numEdges;                    // number of edges
    protected boolean[][] adjMatrix;            // adjacency matrix
    protected T[] vertices;                   // values of vertices
    protected int v1Pos, v2Pos;


    /**
     * Creates an empty graph
     */
    public Graph() {
        this.numVertices = 0;
        this.numEdges = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph (unidirecional)
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        /**  try {
         addEdge(getIndex(vertex1), getIndex(vertex2));
         }catch (InvalidIndexException e){};*/
        v1Pos = getIndex(vertex1);
        v2Pos = getIndex(vertex2);

        if (v2Pos == -1 || v2Pos == -1) {
            try {
                throw new IlegalArgumentException("vertice não encontrado");
            } catch (IlegalArgumentException e) {
            }
        }
        if (this.adjMatrix[v1Pos][v2Pos] == false) {
            this.adjMatrix[v1Pos][v2Pos] = true;
            this.numEdges++;
        } else {
            try {
                throw new IlegalArgumentException("aresta duplicada" + v1Pos + " " + v2Pos);
            } catch (IlegalArgumentException e) { }
        }

    }

    /**
     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

     public void addEdge(int index1, int index2) throws InvalidIndexException {
     if (indexIsValid(index1) && indexIsValid(index2)) {
     adjMatrix[index1][index2] = true;
     adjMatrix[index2][index1] = true;
     }
     }
     */

    /**
     * verifica se existe o vertice,
     *
     * @param index posição do vertice no array vertices
     * @return true se existir o vertice, falso caso contrario
     */
    private boolean indexIsValid(int index) throws IlegalArgumentException {
        if (vertices[index] == null || index >= 0 || index >= vertices.length) { // para ter uma posição valida, o index tem de estar
            // vazio,ser maior que zero e menor que a ultima posição do array
            return false;
        }
        return true;
    }

    /**
     * @param vertice
     * @return posição do vertice se existir,caso não exista chama exception
     */
    protected int getIndex(T vertice) {
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos] != null) {
                if (vertices[pos] == vertice) {
                    return pos;
                }
            }
        }
        return -1;
    }


    /**
     * Remove the edge of the given vertices
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        v1Pos = getIndex(vertex1);
        v2Pos = getIndex(vertex2);
        if (v1Pos == -1 || v2Pos == -1) {
            try {
                throw new IlegalArgumentException("vertice não encontrado");
            } catch (IlegalArgumentException e) {
            }
        }
        if (this.adjMatrix[v1Pos][v2Pos] == true) {
            this.adjMatrix[v1Pos][v2Pos] = false;
            this.numEdges--;
        } else {
            try {
                throw new IlegalArgumentException("aresta não encontrada");
            } catch (IlegalArgumentException e) {
            }
        }
    }


    /**
     * Add the given  vertex to the graph
     *
     * @param vertex the vertex to be added to this graph
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[i][numVertices] = false;
            adjMatrix[numVertices][i] = false;
        }
        numVertices++;
    }


    /**
     * Remove the given vertex of the graph
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {
        int pos = getIndex(vertex);
        if (pos == -1) {
            try {
                throw new IlegalArgumentException("vertice não encontrado");
            } catch (IlegalArgumentException e) {
            }
        }
        // percorrer a matriz para anular as arestas deste vertice
        for (int i = 0; i < vertices.length; i++) {
            if (this.adjMatrix[pos][i] == true) {
                this.adjMatrix[pos][i] = false;
                this.numEdges--;
            }

            if (this.adjMatrix[i][pos] == true) {
                this.adjMatrix[i][pos] = false;
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
/*
        // recuar as posições da matriz adjacente
        //recuar a coluna
        for (int i = pos; i < numVertices; i++) {
            adjMatrix[pos][i] = adjMatrix[pos][i + 1];
        }
        //recuar a linha
        for (int x = pos; x < numVertices; x++) {
            adjMatrix[x][pos] = adjMatrix[x + 1][pos];
        }*/

        this.numVertices--;
    }

    /**
     * Return a lista dos vertices da travessia em largura
     *
     * @param startVertex the starting vertex
     * @return
     */
    @Override
    public Iterator<T> iterartorBFS(T startVertex) {
        Integer x;
        LinkedQueue<Integer> transversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        try {
            if (!this.indexIsValid((Integer) startVertex)) {
                return resultList.iterator();
            }
        } catch (IlegalArgumentException e) {
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        transversalQueue.enqueue(new Integer((Integer) startVertex));
        visited[(Integer) startVertex] = true;


        try {
            while (!transversalQueue.isEmpty()) {
                x = transversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);

                /**
                 * encontrar todos os vertices adjeacebtes de x que não foram visitados mas estão na QUEUE
                 */

                for (int i = 0; i < numVertices; i++) {
                    if (this.adjMatrix[x.intValue()][i]) {
                        transversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }

            }
        } catch (EmptyQueueException e) {
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iterartorDFS(T startVertex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> transversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        try {
            if (!this.indexIsValid((Integer) startVertex)) {
                return resultList.iterator();
            }
        } catch (IlegalArgumentException e) {
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        transversalStack.push(new Integer((Integer) startVertex));
        resultList.addToRear(vertices[(Integer) startVertex]);
        visited[(Integer) startVertex] = true;
        while (!transversalStack.isEmpty()) {
            x = transversalStack.peek();
            found = false;


            // find a vertex adjacent to x that hasn´t been visited
            // and push it on the stack
            for (int i = 0; i < (numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    transversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    found = true;
                }
            }
            if (!found && !transversalStack.isEmpty()) {
                transversalStack.pop();
            }
            return resultList.iterator();

        }
        return null;
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public boolean isempty() {
        if (numVertices == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        if (numEdges == 0) {
            return false;
        }
        return true;

        /**
         if (isempty()) {
         try {
         throw new IlegalArgumentException("vertice não encontrado");
         } catch (IlegalArgumentException e) {
         e.printStackTrace();
         }
         }
         int pos = getIndex(vertex);


         for (int i = 0; i < vertices.length; i++) {
         if (this.adjMatrix[pos][i] == true) {
         return false;
         */
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public String tooString() {
        String adjMatrixTooString = "  ";
        System.out.print(" ");
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos] != null) {
                adjMatrixTooString += vertices[pos] + "  ";
                /**} else {
                 adjMatrixTooString += "*  ";*/
            }
        }
        adjMatrixTooString += "\n";
        for (int linha = 0; linha < vertices.length; linha++) {
            if (vertices[linha] != null) {
                adjMatrixTooString += vertices[linha] + "  ";
                /** } else {
                 adjMatrixTooString += "#  ";*/

                for (int coluna = 0; coluna < vertices.length; coluna++) {
                    if (vertices[coluna] != null)
                        if (adjMatrix[linha][coluna]) {
                            adjMatrixTooString += "X  ";
                        } else {
                            adjMatrixTooString += "0  ";
                        }
                }
                adjMatrixTooString += "\n";
            }


        }
        return adjMatrixTooString;
    }


    private void expandCapacity() {
        //redimensiona o array de vertices
        T[] aux = vertices;
        vertices = (T[]) new Object[aux.length * 2];
        System.arraycopy(aux, 0, vertices, 0, numVertices);

        //redimensiona a matriz adjacente
        boolean[][] auxAdjMatrix = new boolean[aux.length * 2][aux.length * 2];
        for (int i = 0; i < aux.length; i++) {
            for (int x = 0; x < aux.length; x++) {
                auxAdjMatrix[i][x] = adjMatrix[x][i];
            }
        }
        adjMatrix = new boolean[aux.length * 2][aux.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            for (int x = 0; x < vertices.length; x++) {
                adjMatrix[i][x] = auxAdjMatrix[x][i];
            }
        }
    }


}
