package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import sun.applet.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;

/**
 * Created by PedroFernandes on 13/01/15.
 */

public class addPersonController {         //falta o extends

    /**
     * Class instance.
     */
    private static addPersonController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static addPersonController getInstance(){
        if(instance == null){
            instance = new addPersonController();
        }
        return instance;
    }

    @FXML
    private TextField personName;
    @FXML
    private TextField personCity;
    @FXML
    private Button buttonConfirm;




    private addPersonController(){
        // loads the view
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/addPerson.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }

    }



}
