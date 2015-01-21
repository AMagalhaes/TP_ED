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
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.gui.Main;

import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class RemovePersonController extends Pane {

    /**
     * Classe instance
     */
    private static RemovePersonController instance;

    /**
     * Get the class instance
     */
    public static RemovePersonController getInstance() {
        if (instance == null) {
            instance = new RemovePersonController();
        }

        instance.resetFields(true);

        return instance;
    }


    @FXML
    private ChoiceBox<VertexInterface> person1;
    @FXML
    private TextArea output;
    @FXML
    private Button buttonConfirm;

    // lista com todos os vertices
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Main.getInstance().getGrath().getVertexs());

    private RemovePersonController() {

        // loads the view
        FXMLLoader loader = new FXMLLoader(RemovePersonController.class.getResource("../views/person.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        this.person1.setItems(this.vertices);

        buttonConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.getInstance().getGrath().removeVertex(person1.getValue());
                    RemovePersonController.this.output.setText("A pessoa '" + person1.getValue().getNome() + "' foi removida.");
                    resetFields(false);
                } catch (IlegalArgumentException e) {
                    RemovePersonController.this.output.setText("Erro ao remover a pessoa.");
                }

            }
        });
    }

    private void resetFields(boolean all) {
        this.vertices.clear();
        this.vertices.addAll(Main.getGraphInstance().getVertexs());

        if (all) {
            this.output.setText("");
        }
    }
}
