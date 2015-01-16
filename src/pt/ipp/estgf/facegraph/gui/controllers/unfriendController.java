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

public class UnfriendController {

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
    private TextField personName;
    @FXML
    private Button buttonConfirm;


    private UnfriendController(){
        // loads the view
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/person.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }

    }
}
