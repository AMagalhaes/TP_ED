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

public class Main extends Application {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) throws IlegalArgumentException {
        launch(args);
    }

    private FaceNetwork<Vertice, Aresta> graph = new FaceNetwork<Vertice, Aresta>();

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        // disponibiliza a instancia para todo o codigo
        instance = this;

        // Inicia o graph com alguns dados
        Vertice v0 = new Vertice("Pedro Fernandes", "Mondim");
        Vertice v1 = new Vertice("Luis Costa", "Mondim");
        Vertice v2 = new Vertice("Rui Mendes", "Lixa");
        Vertice v3 = new Vertice("Antonio Magalhaes", "Felgueiras");
        Vertice v4 = new Vertice("Castro Antunes", "Felgueiras");
        Vertice v5 = new Vertice("Ricardo Araujo", "Porto");
        Vertice v6 = new Vertice("Manuel Antonio", "Braga");

        Vertice v7 = new Vertice("Rita Ana", "Braga");
        Vertice v8 = new Vertice("Ana Gomes", "Celorico");
        Vertice v9 = new Vertice("Rute Oliveira", "Lixa");
        Vertice v10 = new Vertice("Fatima Araujo", "Felgueiras");
        Vertice v11 = new Vertice("Esmeralda Fonseca", "Vila real");
        Vertice v12 = new Vertice("Susana Fernandes", "Braga");
        Vertice v13 = new Vertice("Miguel Cunha", "Braga");

        Vertice v14 = new Vertice("Paulo Miguel", "Mondim");
        Vertice v15 = new Vertice("Fernando Alves", "Mondim");
        Vertice v16 = new Vertice("Vitor Cerqueira", "Lixa");
        Vertice v17 = new Vertice("Jose Manuel", "Felgueiras");
        Vertice v18 = new Vertice("Julio Freitas", "Celorico");
        Vertice v19 = new Vertice("Helena Braga", "Porto");
        Vertice v20 = new Vertice("Joao Paulo", "Braga");

        Vertice v21 = new Vertice("Altino Gomes", "Mondim");
        Vertice v22 = new Vertice("Joaquim Santos", "Celorico");
        Vertice v23 = new Vertice("Vasco Freitas", "Lixa");
        Vertice v24 = new Vertice("Tania Mendes", "Lixa");

        this.graph.addVertex(v0);
        this.graph.addVertex(v1);
        this.graph.addVertex(v2);
        this.graph.addVertex(v3);
        this.graph.addVertex(v4);
        this.graph.addVertex(v5);
        this.graph.addVertex(v6);
        this.graph.addVertex(v7);
        this.graph.addVertex(v8);
        this.graph.addVertex(v9);
        this.graph.addVertex(v10);
        this.graph.addVertex(v11);
        this.graph.addVertex(v12);
        this.graph.addVertex(v13);
        this.graph.addVertex(v14);
        this.graph.addVertex(v15);
        this.graph.addVertex(v16);
        this.graph.addVertex(v17);
        this.graph.addVertex(v18);
        this.graph.addVertex(v19);
        this.graph.addVertex(v20);
        this.graph.addVertex(v21);
        this.graph.addVertex(v22);
        this.graph.addVertex(v23);
        this.graph.addVertex(v24);

        try {
            this.graph.addEdge(v0, v1, 8);
            this.graph.addEdge(v1, v2, 3);
            this.graph.addEdge(v2, v3, 8);
            this.graph.addEdge(v3, v4, 8);
            this.graph.addEdge(v4, v5, 8);
            this.graph.addEdge(v5, v6, 8);
            this.graph.addEdge(v6, v7, 8);
            this.graph.addEdge(v7, v8, 8);
            this.graph.addEdge(v8, v9, 8);
            this.graph.addEdge(v9, v10, 8);
            this.graph.addEdge(v10, v1, 8);
            this.graph.addEdge(v10, v11, 5);
            this.graph.addEdge(v11, v12, 8);
            this.graph.addEdge(v12, v13, 8);
            this.graph.addEdge(v13, v14, 8);
            this.graph.addEdge(v14, v15, 8);
            this.graph.addEdge(v15, v16, 8);
            this.graph.addEdge(v16, v17, 8);
            this.graph.addEdge(v17, v18, 8);
            this.graph.addEdge(v18, v19, 8);
            this.graph.addEdge(v19, v20, 8);
            this.graph.addEdge(v20, v21, 8);
            this.graph.addEdge(v21, v22, 8);
            this.graph.addEdge(v22, v23, 8);
            this.graph.addEdge(v23, v24, 8);
            this.graph.addEdge(v24, v0, 8);

            this.graph.addEdge(v10, v3, 8);
            this.graph.addEdge(v10, v17, 8);
            this.graph.addEdge(v3, v17, 8);
            this.graph.addEdge(v4, v10, 8);
            this.graph.addEdge(v4, v17, 8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
            loader.setLocation(Main.class.getResource("views/all.fxml"));
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

    public static FaceNetwork getGraphInstance() {
        return getInstance().getGrath();
    }

}

