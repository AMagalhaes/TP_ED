package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by PedroFernandes on 13/01/15.
 */
public class ShortProximityController extends Pane {

    /**
     * Classe instance
     */
    private static ShortProximityController instance;

    /**
     * Get the class instance
     */
    public static ShortProximityController getInstance(){
        if (instance == null){
            instance = new ShortProximityController();
        }

        return instance;
    }

    @FXML
    private ChoiceBox person1;
    @FXML
    private ChoiceBox person2;
    @FXML
    private Button buttonConfirm;

    private ShortProximityController(){
        //loads the view
        FXMLLoader loader = new FXMLLoader(ShortProximityController.class.getResource("../views/person1And2.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
