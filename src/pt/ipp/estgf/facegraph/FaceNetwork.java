package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Interfaces.*;
import pt.ipp.estgf.facegraph.lists.ArrayOrderedList;
import pt.ipp.estgf.facegraph.lists.ArrayUnorderedList;
import pt.ipp.estgf.facegraph.classes.EdgeInterface;
import pt.ipp.estgf.facegraph.classes.VertexInterface;
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
    public void addEdge(V vertex1, V vertex2, double weight) {
        if (weight < 0.0) {
            try {
                throw new IlegalArgumentException("o peso da aresta tem que ser > 0.0");
            } catch (IlegalArgumentException e) {
            }
        }

        // Criar a aresta
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
    // Atributos usados na funcao encontrarMenorCaminho
        double result = 0;
        V temp = src;
        V temp2 = null;

        try {
            Iterator iterator = super.iteratorShortestPath(src, dest);
            ArrayUnorderedList tempList = new ArrayUnorderedList(DEFAULT_CAPACITY);

            while (iterator.hasNext()) {
                tempList.addToRear(iterator.next());
            }

            iterator = tempList.iterator();

            while (iterator.hasNext()) {
                temp2 = (V) this.vertices[(Integer) iterator.next()];
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
        return super.adjMatrix[vPos1][vPos2].getWeight();
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
     *
     * @param nome
     * imprime a lista de amigois de uma dada pessoa
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
     *
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
        // Atributos usados na funcao encontrarMenorCaminho

        int index;
        double weight;
        int[] predecessor = new int[this.numVertices];
        ArrayOrderedList<Double> orderedArray = new ArrayOrderedList<Double>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<Integer>(DEFAULT_CAPACITY);
        StackADT<Integer> stack = new LinkedStack<Integer>();
        int startIndex = getIndex(startVertex);
        int endIndex = getIndex(targetVertex);

        // valida os vertices fornecidos
        if (!this.indexIsValid(startIndex) || !this.indexIsValid(endIndex) || (startIndex == endIndex)) {
            return resultList.iterator();
        }

        // inicializa o array de pesos
        double[] pathWeight = new double[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            pathWeight[i] = Double.NEGATIVE_INFINITY;
        }

        // inicializa o arrays de vertices visitados
        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        // percorre todos os vertices adjacentes à origem
        // de forma a fazer a soma dos pesos
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                if (this.adjMatrix[startIndex][i] != null) {
                    pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i].getWeight();
                } else {
                    pathWeight[i] = Double.NEGATIVE_INFINITY;
                }

                predecessor[i] = startIndex;
                orderedArray.add(new Double(pathWeight[i]));
            }
        }

        do {
            try {
                weight = (orderedArray.removeLast()).doubleValue();
            } catch (EmptyCollectionException ex) {
            }
            orderedArray.removeAll();

            if (weight == Double.NEGATIVE_INFINITY) {
                return resultList.iterator();
            } else {
                index = super.getIndexOfTheVertice(pathWeight, weight, visited);

                visited[index] = true;
            }

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[index][i] == null) {
                    if (!visited[i]) {
                        orderedArray.add(new Double(pathWeight[i]));
                    }

                    continue;
                }

                if (!visited[i]) {
                    if ((this.adjMatrix[index][i].getWeight() > Double.NEGATIVE_INFINITY) && (pathWeight[index] + this.adjMatrix[index][i].getWeight()) > pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + this.adjMatrix[index][i].getWeight();
                        predecessor[i] = index;
                    }

                    orderedArray.add(new Double(pathWeight[i]));
                }
            }

        } while (!orderedArray.isEmpty() && !visited[endIndex]);

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
        // Atributos usados na funcao encontrarMenorCaminho
        double result = 0;
        V temp = src;
        V temp2 = null;

        try {
            Iterator iterator = this.iteratorLongestPath(src, dest);
            ArrayUnorderedList tempList = new ArrayUnorderedList(DEFAULT_CAPACITY);

            while (iterator.hasNext()) {
                tempList.addToRear(iterator.next());
            }

            iterator = tempList.iterator();

            while (iterator.hasNext()) {
                temp2 = (V) this.vertices[(Integer) iterator.next()];
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

}
