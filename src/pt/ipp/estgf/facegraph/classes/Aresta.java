package pt.ipp.estgf.facegraph.classes;

/**
 * Created by antoniomagalhaes on 09/01/15.
 */
public class Aresta implements EdgeInterface {

    private double weight;

    public Aresta(double weight) {
        this.weight = weight;
    }

    public Aresta() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "weight=" + weight +
                '}';
    }
}
