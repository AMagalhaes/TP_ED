package pt.ipp.estgf.facegraph.gui.controllersss;

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
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.IlegalArgumentException;
import pt.ipp.estgf.facegraph.gui.Teste;

import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class GraphMinHabitantController extends Pane {

    /**
     * Class instance.
     */
    private static GraphMinHabitantController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static GraphMinHabitantController getInstance() {
        if (instance == null) {
            instance = new GraphMinHabitantController();
        }
        return instance;
    }


    @FXML
    private ChoiceBox<VertexInterface> city;
    @FXML
    private Button buttonConfirm;
    @FXML
    private TextArea output;

    // lista com todos os vertices
    private ObservableList<VertexInterface> vertices = FXCollections.observableArrayList(Teste.getInstance().getGrath().getVertexs());


    private GraphMinHabitantController() {

        // loads the view
        FXMLLoader loader = new FXMLLoader(PrintHabitantsController.class.getResource("../views/city.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }




        buttonConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    output.setText(String.valueOf(Teste.getInstance().getGrath().grafoHabitantesMinimo()));
                } catch (EmptyCollectionException e) {
                    e.printStackTrace();
                } catch (IlegalArgumentException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
