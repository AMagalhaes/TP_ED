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
import javafx.scene.layout.Pane;
import pt.ipp.estgf.facegraph.Interfaces.VertexInterface;
import pt.ipp.estgf.facegraph.gui.Main;

import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class BiggerProximityController extends Pane {


    /**
     * Class instance.
     */
    private static BiggerProximityController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static BiggerProximityController getInstance() {
        if (instance == null) {
            instance = new BiggerProximityController();
        }

        instance.reset();

        return instance;
    }

    @FXML
    private ChoiceBox<VertexInterface> person1;
    @FXML
    private ChoiceBox<VertexInterface> person2;
    @FXML
    private TextArea output;
    @FXML
    private Button buttonConfirm;

    // lista com todos os vertices
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Main.getInstance().getGrath().getVertexs());


    private BiggerProximityController() {

        // loads the view
        FXMLLoader loader = new FXMLLoader(BiggerProximityController.class.getResource("../views/person1And2.fxml"));
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
                output.setText("A minima distancia entre os vertices é: " + String.valueOf(Main.getInstance().getGrath().shortestPathWeight(person1.getValue(), person2.getValue())));
            }
        });
    }

    private void reset() {
        this.vertices.clear();
        this.vertices.addAll(Main.getGraphInstance().getVertexs());
        this.output.setText("");
    }
}
