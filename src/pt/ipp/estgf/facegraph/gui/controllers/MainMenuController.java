package pt.ipp.estgf.facegraph.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import pt.ipp.estgf.facegraph.gui.Main;

import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class MainMenuController extends MenuBar {

    @FXML
    private MenuItem addPerson;
    @FXML
    private MenuItem addFriendShip;
    @FXML
    private MenuItem removePerson;
    @FXML
    private MenuItem enemies;
    @FXML
    private MenuItem biggerProximity;
    @FXML
    private MenuItem shortProximity;
    @FXML
    private MenuItem path;
    @FXML
    private MenuItem printData;
    @FXML
    private MenuItem printAll;
    @FXML
    private MenuItem habitants;
    @FXML
    private MenuItem graphMinHabitant;
    @FXML
    private MenuItem graphHabitant;


    public MainMenuController() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/menu.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // adicionar pessoa
        this.addPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(AddPersonController.getInstance());
            }
        });

        // adicionar amizade
        this.addFriendShip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(AddFriendshipController.getInstance());
            }
        });

        // remover pessoa
        this.removePerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(RemovePersonController.getInstance());
            }
        });

        // inimigos
        this.enemies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(UnfriendController.getInstance());
            }
        });

        // maior proximidade
        this.biggerProximity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(BiggerProximityController.getInstance());
            }
        });

        // menor proximidade
        this.shortProximity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(ShortProximityController.getInstance());
            }
        });

        // caminho
        this.path.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(PathController.getInstance());
            }
        });

        // imprimir dados da pessoa
        this.printData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(PrintPersonController.getInstance());
            }
        });


        // imprimir tudo
        this.printAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(PrintAllController.getInstance());
            }
        });

        // imprime os habitantes de uma cidade
        this.habitants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(PrintHabitantsController.getInstance());
            }
        });

        // grafo minimo de habitante
        this.graphMinHabitant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(GraphMinHabitantController.getInstance());
            }
        });

        // grafo minimo de habitante
        this.graphHabitant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getInstance().setCenterNode(GraphHabitantController.getInstance());
            }
        });

    }

}
