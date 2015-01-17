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
import pt.ipp.estgf.facegraph.gui.Teste;

import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class PrintHabitantsController extends Pane {
    /**
     * Class instance.
     */
    private static PrintHabitantsController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static PrintHabitantsController getInstance() {
        if (instance == null) {
            instance = new PrintHabitantsController();
        }
        return instance;
    }

    @FXML
    private ChoiceBox<VertexInterface> city;
    @FXML
    private Button buttonConfirm;
    @FXML
    private TextArea output;

    //lista com as cidades
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Teste.getInstance().getGrath().getVertexs());

    private PrintHabitantsController() {
        // loads the view
        FXMLLoader loader = new FXMLLoader(PrintHabitantsController.class.getResource("../views/city.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.city.setItems(this.vertices);


        buttonConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                Teste.getInstance().getGrath().addVertex(city.getValue());

                output.setText("Adicionado");
            }
        });
    }

}
