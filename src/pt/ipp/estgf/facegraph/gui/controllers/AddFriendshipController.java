package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pt.ipp.estgf.facegraph.Interfaces.VertexInterface;
import pt.ipp.estgf.facegraph.entities.Vertice;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.gui.Main;


import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class AddFriendshipController extends Pane {

    /**
     * Class instance.
     */
    private static AddFriendshipController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static AddFriendshipController getInstance() {
        if (instance == null) {
            instance = new AddFriendshipController();
        }

        instance.actionToPerformOnGetInstance();

        return instance;
    }

    @FXML
    private ChoiceBox<VertexInterface> person1;
    @FXML
    private ChoiceBox<VertexInterface> person2;
    @FXML
    private TextField distance;
    @FXML
    private TextArea output;
    @FXML
    private Button buttonConfirm;


    // lista com todos os vertices
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Main.getInstance().getGrath().getVertexs());

    private AddFriendshipController() {
        // loads the view
        FXMLLoader loader = new FXMLLoader(AddFriendshipController.class.getResource("../views/addFriendship.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.person1.setItems(this.vertices);
        this.person2.setItems(this.vertices);

        buttonConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Double peso;

                // faz o parse para Double
                try {
                     peso = Double.parseDouble(distance.getText());
                } catch (Exception ex) {
                    output.setText("O valor da distancia é invalido.");
                    return;
                }

                try {
                    // adiciona a nova aresta
                    Main.getInstance().getGrath().addEdge(person1.getValue(), person2.getValue(), peso);

                    // informa que a aresta foi adicionada
                    output.setText("Uma nova amizade foi criada");
                } catch (IlegalArgumentException e) {
                    output.setText("Impossivel criar a amizade: " + e.getMessage());
                }
            }
        });
    }

    private void actionToPerformOnGetInstance() {
        // obtem todos os vertices
        this.vertices.clear();
        this.vertices.addAll(Main.getGraphInstance().getVertexs());

        // faz o reset de todos os inputs/outputs
        this.output.setText("");
        this.distance.setText("");
    }
}