package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.classes.Aresta;
import pt.ipp.estgf.facegraph.classes.Vertice;
import pt.ipp.estgf.facegraph.gui.TestGuiMain;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */


public class Demo {

    public static void main(String[] args) {
        FaceNetwork<Vertice, Aresta> grafo = new FaceNetwork();

        Vertice vert1 =  new Vertice("A", "mondim");
        Vertice vert2 =  new Vertice("B", "celorico");
        Vertice vert3 =  new Vertice("C", "lixa");
        Vertice vert4 =  new Vertice("D", "felgueiras");
        Vertice vert5 =  new Vertice("E", "vila real");
        Vertice vert6 =  new Vertice("F", "porto");
        Vertice vert7 =  new Vertice("G", "braga");
        Vertice vert8 =  new Vertice("H", "odivelas");

        grafo.addVertex(vert1);
        grafo.addVertex(vert2);
        grafo.addVertex(vert3);
        grafo.addVertex(vert4);
        grafo.addVertex(vert5);
        grafo.addVertex(vert6);
        grafo.addVertex(vert7);
        grafo.addVertex(vert8);

       // Aresta edge1 = new Aresta(vert1, vert2, 3);
        grafo.addEdge(vert1,vert2,8);
        grafo.addEdge(vert1,vert8,8);
        grafo.addEdge(vert2,vert3,3);
        grafo.addEdge(vert3,vert4,8);
        grafo.addEdge(vert4,vert5,8);
        grafo.addEdge(vert5,vert6,8);
        grafo.addEdge(vert6,vert7,5);
        grafo.addEdge(vert7,vert8,7);
        grafo.addEdge(vert7, vert3, 1);
        //grafo.addEdge("L","J");
        //grafo.removeVertex("B");
        // grafo.removeVertex("A");
        //grafo.addEdge("Z","M");
        //grafo.removeVertex("L");
        //grafo.iteratorBFS("M");


       /** grafo.addEdge("A", "B", 3);
        grafo.addEdge("B", "C", 5);
        grafo.addEdge("B", "D", 7);
        grafo.addEdge("C", "D", 1);*/

        System.out.println(grafo.tooString());
        // grafo.removeVertex("D");
        //System.out.println(grafo.isConnected());

        System.out.println(" caminho +C :" + grafo.shortestPathWeight(vert2,vert7));
        System.out.println(" caminho +L :" + grafo.longestPathWeight(vert1, vert7));

        System.out.println(grafo.iteratorBFS(vert1));
        //System.out.println("conected: " + grafo.isConnected());
        grafo.imprimeDados("A");
        System.out.println(".....|Imprime tudo ........ \n");
        grafo.imprimeTudo();
        System.out.println(" a cidade tem " + grafo.habitantesCidade("mondim") + "habitantes");
    }


}
