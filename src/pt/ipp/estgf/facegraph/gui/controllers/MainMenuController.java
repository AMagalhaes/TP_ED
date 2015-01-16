package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import pt.ipp.estgf.facegraph.gui.Teste;

import java.io.IOException;

/**
 * Created by antoniomagalhaes on 16/01/15.
 */
public class MainMenuController extends MenuBar {

    @FXML
    private MenuItem addPerson;

    public MainMenuController() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/menu.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Teste.getInstance().setCenterNode(AddPersonController.getInstance());
            }
        });
    }

}
