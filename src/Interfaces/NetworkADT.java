package Interfaces;


/**
 * Created by antoniomagalhaes on 03/01/15.
 */
public interface NetworkADT<T> extends GraphADT<T> {


    /**
     * Inserts an edge between two vertices of the graph
.     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    public void addEdge(T vertex1, T vertex2, double weight);


    /**
     * Returns te+he weight of the shortest path of the network
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return
     */
    public  double shortestPathWeight(T vertex1, T vertex2);




}
