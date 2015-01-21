package pt.ipp.estgf.facegraph.gui.controllersss;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import pt.ipp.estgf.facegraph.gui.Teste;


import java.io.IOException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
class PrintAllController extends Pane {

    /**
     * Class instance.
     */
    private static PrintAllController instance;

    /**
     * Get the class instance.
     *
     * @return
     */
    public static PrintAllController getInstance() {
        if (instance == null) {
            instance = new PrintAllController();
        }
        return instance;
    }

    @FXML
    private TextArea output;


    private PrintAllController() {
        // loads the view
        FXMLLoader loader = new FXMLLoader(PrintAllController.class.getResource("../views/printAll.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        output.setText(String.valueOf(Teste.getInstance().getGrath().imprimeTudo()));


    }

}
