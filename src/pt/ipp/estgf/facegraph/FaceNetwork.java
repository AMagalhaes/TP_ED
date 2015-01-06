package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Interfaces.FaceNetworkADT;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;

import java.util.Iterator;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class FaceNetwork<T> extends Graph<T> implements FaceNetworkADT<T> {

    public float DEFAULT_WEIGHT = 1.0F;
    protected double [][] weights;

    public FaceNetwork() {
        super();
        // no código usa size();
        this.weights = new double[super.DEFAULT_CAPACITY][super.DEFAULT_CAPACITY];
    }





    /**
     * Inserts an edge between two vertices of the graph
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
    if(weight<0.0){
        try {
            throw new IlegalArgumentException("o peso da aresta tem que ser > 0.0");
        } catch (IlegalArgumentException e) { }
    }
        super.addEdge(vertex1,vertex2);

        this.setEdgeWeights(vertex1, weight, vertex2);
    }


    /**
     *
     * @param src
     * @param dest
     * @return
     */
    @Override
    public double shortestPathWeight(T src, T dest) {
   return 0;
    }

    @Override
    public void addVertex(T vertex) {

    }

    @Override
    public void removeVertex(T vertex) {

    }

    @Override
    public void addEdge(T vertex1, T vertex2) {

    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {

    }

    @Override
    public Iterator iterartorBFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator iterartorDFS(T startVertex) throws EmptyCollectionException {
        return null;
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public boolean isempty() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String tooString() {
        return null;
    }

    /**
     *
     * @param vertex1
     * @param vertex2
     * @return
     */
    public double getEdgeWeight(T vertex1, T vertex2){
        int vPos1=super.getIndex(vertex1);
        int vPos2 = super.getIndex(vertex2);
        return  weights[vPos1][vPos2];
    }

    /**
     *
     * @param vertex1
     * @param newWeight
     * @param vertex2
     */
    public void setEdgeWeights(T vertex1, double newWeight, T vertex2) {
        if (newWeight <0.0){
            try {
                throw new IlegalArgumentException("O peso da aresta tem que ser >= 0.0 ");
            } catch (IlegalArgumentException e) {}
        }
        int vPos1=super.getIndex(vertex1);
        int vPos2 = super.getIndex(vertex2);
        weights[vPos1][vPos2] = newWeight;
        weights[vPos2][vPos1] = newWeight;
    }
}
