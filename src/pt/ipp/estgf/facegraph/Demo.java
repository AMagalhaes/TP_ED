package pt.ipp.estgf.facegraph;

import pt.ipp.estgf.facegraph.Interfaces.UnorderedList;
import pt.ipp.estgf.facegraph.entities.Aresta;
import pt.ipp.estgf.facegraph.entities.Vertice;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.EmptyQueueException;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;

import java.util.Iterator;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */


public class Demo {

    public static void main(String[] args) throws IlegalArgumentException, EmptyCollectionException, EmptyQueueException {
        FaceNetwork<Vertice, Aresta> grafo = new FaceNetwork();
        Vertice vert0 = new Vertice("A", "mondim");
        Vertice vert1 = new Vertice("B", "celorico");
        Vertice vert2 = new Vertice("C", "lixa");
        Vertice vert3 = new Vertice("D", "felgueiras");
        Vertice vert4 = new Vertice("E", "vila real");
        Vertice vert5 = new Vertice("F", "porto");
        Vertice vert6 = new Vertice("G", "braga");

        grafo.addVertex(vert0);
        grafo.addVertex(vert1);
        grafo.addVertex(vert2);
        grafo.addVertex(vert3);
        grafo.addVertex(vert4);
        grafo.addVertex(vert5);
        grafo.addVertex(vert6);

        grafo.addEdge(vert0, vert1, 8);
        grafo.addEdge(vert1, vert2, 3);
        grafo.addEdge(vert2, vert3, 8);
        grafo.addEdge(vert3, vert4, 8);
        grafo.addEdge(vert4, vert5, 8);
        grafo.addEdge(vert5, vert6, 5);
        grafo.addEdge(vert6, vert0, 1);

        grafo.addEdge(vert0, vert4, 30);
        grafo.addEdge(vert6, vert2, 30);
        grafo.addEdge(vert6, vert3, 30);

        // imprime o LongestPath
        Iterator it = grafo.iteratorLongestPath(vert0, vert6);
        System.out.print("\n LongestPath: ");
        while (it.hasNext()) {
            System.out.print(" " + it.next());
        }
        System.out.println("\n");

        // comprimento do caminho mais curto
        System.out.println("\n caminho +L :" + grafo.longestPathWeight(vert0, vert6));

        /*
        System.out.println("\n");

        grafo.imprimeDados("A");
        System.out.println(".....|Imprime tudo ........ \n");
        grafo.imprimeTudo();
        System.out.println(" a cidade tem " + grafo.habitantesCidade("mondim") + "habitantes");

        System.out.println("::::::::::::::::::::::::::::::::::::::::");
        //System.out.println(grafo.grafoHabitantesMinimo());


        //System.out.println(grafo.grafoHabitantesMinimo("mondim").toString());
        /*for (int i = 0; i <count.numVertices; i++) {
                System.out.println("graHabMin : " + count.vertices[i]);

        }*/
      //  System.out.println("Amigos : " + grafo.caminho(vert1, vert3));*/
    }


}
