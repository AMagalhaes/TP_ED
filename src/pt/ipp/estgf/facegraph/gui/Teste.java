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
import pt.ipp.estgf.facegraph.gui.controllers.MainMenuController;

import java.io.IOException;

public class Teste extends Application {

    private static Teste instance;

    public static Teste getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
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

