package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.applet.Main;

import java.io.IOException;

/**
 * Created by PedroFernandes on 13/01/15.
 */

public class addFriendshipController {

    /**
     * Class instance.
     */
    private static addFriendshipController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static addFriendshipController getInstance() {
        if (instance == null) {
            instance = new addFriendshipController();
        }
        return instance;
    }

    @FXML
    private TextField person1;
    @FXML
    private TextField person2;
    @FXML
    private TextField distance;
    @FXML
    private Button buttonConfirm;



private addFriendshipController(){
    // loads the view
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/addFriendship.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
        loader.load();
    }catch (IOException ex){
        throw new RuntimeException(ex);
    }

}
}