package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Heap.LinkedHeap;
import pt.ipp.estgf.facegraph.Interfaces.*;
import pt.ipp.estgf.facegraph.entities.Aresta;
import pt.ipp.estgf.facegraph.entities.Vertice;
import pt.ipp.estgf.facegraph.exceptions.EmptyUnorderListException;
import pt.ipp.estgf.facegraph.lists.ArrayOrderedList;
import pt.ipp.estgf.facegraph.lists.ArrayUnorderedList;
import pt.ipp.estgf.facegraph.Interfaces.EdgeInterface;
import pt.ipp.estgf.facegraph.Interfaces.VertexInterface;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.EmptyQueueException;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.linkedStack.LinkedStack;

import java.util.Iterator;


/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class FaceNetwork<V extends VertexInterface, E extends EdgeInterface> extends Graph<V, E> implements NetworkADT<V> {

    public FaceNetwork() {
        super();
    }


    /**
     * Inserts an edge between two vertices of the graph
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight
     */
    @Override
    public void addEdge(V vertex1, V vertex2, double weight) throws IlegalArgumentException {
        // verifica se o peso é valido
        if (weight < 0.0) {
            throw new IlegalArgumentException("o peso da aresta tem que ser > 0.0");
        }

        // criar a aresta no grafo
        super.addEdge(vertex1, vertex2);

        // define o peso da aresta
        this.setEdgeWeights(vertex1, weight, vertex2);
    }


    /**
     * @param src
     * @param dest
     * @return
     */
    @Override
    public double shortestPathWeight(V src, V dest) {
        V temp = src;
        V temp2 = null;
        double result = 0;

        try {
            // obtem a lista de vertices do caminho mais curto
            Iterator iterator = super.iteratorShortestPath(src, dest);

            // obtem o primeiro vertice
            if (iterator.hasNext()) {
                temp = this.vertices[(Integer) iterator.next()];
            } else {
                return result;
            }

            while (iterator.hasNext()) {
                // obtem o proximo vertice
                temp2 = this.vertices[(Integer) iterator.next()];
                result += this.getEdgeWeight(temp, temp2);
                temp = temp2;
            }
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        } catch (IlegalArgumentException e) {
            e.printStackTrace();
        } catch (EmptyQueueException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * @param vertex1
     * @param vertex2
     * @return
     */
    public double getEdgeWeight(V vertex1, V vertex2) {
        int vPos1 = super.getIndex(vertex1);
        int vPos2 = super.getIndex(vertex2);

        if (super.adjMatrix[vPos1][vPos2] != null) {
            return super.adjMatrix[vPos1][vPos2].getWeight();
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    /**
     * @param vertex1
     * @param weight
     * @param vertex2
     */
    public void setEdgeWeights(V vertex1, double weight, V vertex2) {
        if (weight < 0.0) {
            try {
                throw new IlegalArgumentException("O peso da aresta tem que ser >= 0.0 ");
            } catch (IlegalArgumentException e) {
            }
        }

        E aresta = super.adjMatrix[v1Pos][v2Pos];
        aresta.setWeight(weight);
        aresta = super.adjMatrix[v2Pos][v1Pos];
        aresta.setWeight(weight);
    }

    /**
     * @param nome imprime a lista de amigois de uma dada pessoa
     */
    public void imprimeDados(String nome) {

        for (int pos = 0; pos < numVertices; pos++) {
            if (vertices[pos].getNome() == nome) {
                System.out.print("Id: " + vertices[pos].getId());
                System.out.print("  Nome: " + vertices[pos].getNome());
                System.out.println("  Cidade: " + vertices[pos].getCidade());

                System.out.println("AMIGOS : ");
                System.out.println("Id \t nome \t cidade \t");
                for (int l = 0; l <= numVertices; l++) {
                    if (adjMatrix[pos][l] != null) {
                        System.out.print(vertices[l].getId() + " \t ");
                        System.out.print(vertices[l].getNome() + " \t ");
                        System.out.print(vertices[l].getCidade() + " \n");
                    }
                }
            }
        }
    }

    /**
     * imprime todas as pessoas, assim como a lista dosseus amigos
     */
    public void imprimeTudo() {
        for (int posi = 0; posi < numVertices; posi++) {
            imprimeDados(vertices[posi].getNome());
        }
    }

    /**
     * @param cidade
     * @return numero de habitantes de uma dada cidade
     */
    public int habitantesCidade(String cidade) {
        int count = 0;
        for (int pos = 0; pos < numVertices; pos++) {
            if (vertices[pos].getCidade() == cidade) {
                count++;
            }
        }
        return count;
    }

    public Iterator iteratorLongestPath(V startVertex, V targetVertex) throws EmptyCollectionException, IlegalArgumentException, EmptyQueueException {
        int index;
        double weight;
        int endIndex = getIndex(targetVertex);
        int startIndex = getIndex(startVertex);
        int[] predecessor = new int[this.numVertices];
        StackADT<Integer> stack = new LinkedStack<Integer>();
        OrderedListADT<Double> unArrayList = new ArrayOrderedList<Double>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<Integer>(DEFAULT_CAPACITY);

        // inicializa o array que contem a soma dos pesos a 0
        double[] pathWeight = new double[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            pathWeight[i] = Double.NEGATIVE_INFINITY;
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
                    pathWeight[i] = Double.NEGATIVE_INFINITY;
                }

                predecessor[i] = startIndex;
                unArrayList.add(pathWeight[i]);
            }
        }

        do {
            try {
                weight = unArrayList.removeLast();
            } catch (EmptyUnorderListException e) {
                e.printStackTrace();
            }
            unArrayList = new ArrayOrderedList<Double>();

            if (weight == Double.NEGATIVE_INFINITY) {
                return resultList.iterator();
            } else {
                index = getIndexOfTheVertice(pathWeight, weight, visited);
                visited[index] = true;
            }

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[index][i] == null) {
                    if (!visited[i]) {
                        unArrayList.add(pathWeight[i]);
                    }

                    continue;
                }

                if (!visited[i]) {
                    if ((this.adjMatrix[index][i] != null) && (pathWeight[index] + this.adjMatrix[index][i].getWeight()) > pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + this.adjMatrix[index][i].getWeight();
                        predecessor[i] = index;
                    }

                    unArrayList.add(pathWeight[i]);
                }
            }

        } while (!unArrayList.isEmpty());

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

    public double longestPathWeight(V src, V dest) {
        V temp = src;
        V temp2 = null;
        double result = 0;

        try {
            // calcula o o caminho mais longo
            Iterator iterator = this.iteratorLongestPath(src, dest);

            // obtem o primeiro vertice
            if (iterator.hasNext()) {
                temp = this.vertices[(Integer) iterator.next()];
            } else {
                return result;
            }

            while (iterator.hasNext()) {
                temp2 = this.vertices[(Integer) iterator.next()];
                result += this.getEdgeWeight(temp, temp2);
                temp = temp2;
            }
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        } catch (IlegalArgumentException e) {
            e.printStackTrace();
        } catch (EmptyQueueException e) {
            e.printStackTrace();
        }

        return result;
    }


    protected FaceNetwork<V, E> grafoHabitantesMinimo() throws EmptyCollectionException, IlegalArgumentException {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        HeapADT<Double> miniHeap
                = new LinkedHeap<Double>();
        FaceNetwork<V, E> resultGraph = new FaceNetwork<V, E>();
        if (isEmpty() || isConnected()) {
            return resultGraph;
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j].setWeight(Double.POSITIVE_INFINITY);
            }
        }
        resultGraph.vertices = (V[]) new Vertice[numVertices];
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;


        // adiciona todos os vertices que são adjecentes ao vertice inicial á heap

        for (int i = 0; i < numVertices; i++) {
            miniHeap.addElement(new Double(adjMatrix[0][i].getWeight()));
        }
        while ((resultGraph.size() < this.size()) && !miniHeap.isEmpty()) {
            do {
                weight = (miniHeap.removeMin()).doubleValue();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (indexIsValid(edge[0]) || !indexIsValid(edge[1]));
            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }
            // adiciona uma nova aresta e um vertice ao resultGraph
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;
            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            // adiciona á heap todas as arestas que são adjacentes ao novo vertice

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && ((this.adjMatrix[i][index].getWeight()) < Double.POSITIVE_INFINITY)) {

                }
                edge[0] = index;
                edge[1] = i;
                miniHeap.addElement(new Double(adjMatrix[index][i].getWeight()));
            }
        }

        return resultGraph;
    }


    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (super.adjMatrix[i][j] != null && (super.adjMatrix[i][j].getWeight() == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /** Will only get to here if a valid edge is not found */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }


    protected E[][] grafoCidade(String cidade) {
        E[][] adjMatrixCidade = (E[][]) (new Aresta[numVertices][numVertices]);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j].getWeight() > 0 && adjMatrix[i][j].getWeight() < Double.POSITIVE_INFINITY) {
                    if (vertices[i].getCidade() == vertices[j].getCidade()) {
                        adjMatrixCidade[i][j] = adjMatrix[i][j];
                    }
                }
            }

        }

        return adjMatrixCidade;
    }


    public boolean caminho(V startVertex, V targetVertex) throws IlegalArgumentException, EmptyQueueException, EmptyCollectionException {
        if (indexIsValid(getIndex(startVertex)) && indexIsValid(getIndex(targetVertex))) {
            // verifica caminho directo,ligados por uma aresta
            if (adjMatrix[getIndex(startVertex)][getIndex(targetVertex)] != null)
                if ((adjMatrix[getIndex(startVertex)][getIndex(targetVertex)].getWeight() > 0 &&
                        adjMatrix[getIndex(startVertex)][getIndex(targetVertex)].getWeight() < Double.POSITIVE_INFINITY)) {
                    return true;
                }
            // verifica caminho indirecto, estão ligados através de amigos
            if (shortestPathWeight(startVertex, targetVertex) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Obtem todos os vertices.
     *
     * @return
     */
    public V[] getVertexs() {
        V[] result = (V[]) (new Vertice[this.size()]);
        int index = 0;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] != null) {
                result[index] = this.vertices[i];
                index++;
            }
        }

        return (V[]) result;
    }
}

