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
import pt.ipp.estgf.facegraph.gui.Teste;

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
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Teste.getInstance().getGrath().getVertexs());


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


                try {
                    Teste.getInstance().getGrath().addEdge(person1.getValue(), person2.getValue());
                    output.setText("Adicionado");
                } catch (IlegalArgumentException e) {
                    System.out.println("AQUI");
                }
            }
        });
    }
}
