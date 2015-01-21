package pt.ipp.estgf.facegraph.gui;/**
 * Created by antoniomagalhaes on 16/01/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.estgf.facegraph.FaceNetwork;
import pt.ipp.estgf.facegraph.entities.Aresta;
import pt.ipp.estgf.facegraph.entities.Vertice;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.gui.controllers.MainMenuController;

import java.io.IOException;

public class Teste extends Application {

    private static Teste instance;

    public static Teste getInstance() {
        return instance;
    }

    public static void main(String[] args) throws IlegalArgumentException {

        //  launch(args);

        FaceNetwork<Vertice, Aresta> grafo = new FaceNetwork();


        Vertice v0 = new Vertice("Pedro Fernandes", "Mondim");
        Vertice v1 = new Vertice("Luis Costa", "Celorico");
        Vertice v2 = new Vertice("Rui Mendes", "Lixa");
        Vertice v3 = new Vertice("Antonio Magalhaes", "Felgueiras");
        Vertice v4 = new Vertice("Castro Antunes", "Vila real");
        Vertice v5 = new Vertice("Ricardo Araujo", "Porto");
        Vertice v6 = new Vertice("Manuel Antonio", "Braga");

        Vertice v7 = new Vertice("Rita Ana", "Mondim");
        Vertice v8 = new Vertice("Ana Gomes", "Celorico");
        Vertice v9 = new Vertice("Rute Oliveira", "Lixa");
        Vertice v10 = new Vertice("Fatima Araujo", "Felgueiras");
        Vertice v11 = new Vertice("Esmeralda Fonseca", "Vila real");
        Vertice v12 = new Vertice("Susana Fernandes", "Porto");
        Vertice v13 = new Vertice("Miguel Cunha", "Braga");

        Vertice v14 = new Vertice("Paulo Miguel", "Mondim");
        Vertice v15 = new Vertice("Fernando Alves", "Celorico");
        Vertice v16 = new Vertice("Vitor Cerqueira", "Lixa");
        Vertice v17 = new Vertice("Jose Manuel", "Felgueiras");
        Vertice v18 = new Vertice("Julio Freitas", "Vila real");
        Vertice v19 = new Vertice("Helena Braga", "Porto");
        Vertice v20 = new Vertice("Joao Paulo", "Braga");

        Vertice v21 = new Vertice("Altino Gomes", "Mondim");
        Vertice v22 = new Vertice("Joaquim Santos", "Celorico");
        Vertice v23 = new Vertice("Vasco Freitas", "Lixa");
        Vertice v24 = new Vertice("Tania Mendes", "Felgueiras");

        grafo.addVertex(v0);
        grafo.addVertex(v1);
        grafo.addVertex(v2);
        grafo.addVertex(v3);
        grafo.addVertex(v4);
        grafo.addVertex(v5);
        grafo.addVertex(v6);
        grafo.addVertex(v7);
        grafo.addVertex(v8);
        grafo.addVertex(v9);
        grafo.addVertex(v10);
        grafo.addVertex(v11);
        grafo.addVertex(v12);
        grafo.addVertex(v13);
        grafo.addVertex(v14);
        grafo.addVertex(v15);
        grafo.addVertex(v16);
        grafo.addVertex(v17);
        grafo.addVertex(v18);
        grafo.addVertex(v19);
        grafo.addVertex(v20);
        grafo.addVertex(v21);
        grafo.addVertex(v22);
        grafo.addVertex(v23);
        grafo.addVertex(v24);

        grafo.addEdge(v0, v1, 8);
        grafo.addEdge(v1, v2, 3);
        grafo.addEdge(v9, v10, 8);
        grafo.addEdge(v10, v11, 8);
        grafo.addEdge(v18, v19, 8);
        grafo.addEdge(v19, v20, 5);
        grafo.addEdge(v20, v21, 1);


    }

    private FaceNetwork<Vertice, Aresta> graph = new FaceNetwork<Vertice, Aresta>();

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        // disponibiliza a instancia para todo o codigo
        instance = this;

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Menu");

        initRootLayout();

        showMenu();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Teste.class.getResource("views/all.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCenterNode(Node node) {
        this.rootLayout.setCenter(node);
    }

    /**
     * Mostra o person overview dentro do root layout.
     */
    public void showMenu() {
        rootLayout.setTop(new MainMenuController());
    }

    /**
     * Retorna o palco principal.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public FaceNetwork getGrath() {
        return this.graph;
    }

}

