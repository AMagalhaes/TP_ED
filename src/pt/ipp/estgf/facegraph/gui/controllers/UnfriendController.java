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
public class UnfriendController extends Pane {

    /**
     * Class instance.
     */
    private static UnfriendController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static UnfriendController getInstance() {
        if (instance == null) {
            instance = new UnfriendController();
        }

        instance.reset(true);

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


    private UnfriendController() {
        // loads the view
        FXMLLoader loader = new FXMLLoader(UnfriendController.class.getResource("../views/person1And2.fxml"));
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
                buttonAction();
            }
        });
    }

    private void reset(boolean all) {
        this.vertices.clear();
        this.vertices.addAll(Main.getGraphInstance().getVertexs());

        if (all) {
            this.output.setText("");
        }
    }

    private void buttonAction() {
        if (this.person1.getValue().equals(this.person2.getValue())) {
            this.output.setText("Não pode remover uma amizade com a mesma pessoa.");
            return;
        }

        try {
            Main.getInstance().getGrath().removeEdge(person1.getValue(), person2.getValue());
            this.output.setText("As duas pessoas selecionadas são agora inimigos.");
            reset(false);
        } catch (IlegalArgumentException e) {
            this.output.setText("Foi impossivel remover a amizade: " + e.getMessage());
        }
    }
}
