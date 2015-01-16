package pt.ipp.estgf.facegraph.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import sun.rmi.rmic.Main;

public class TestGuiMain extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage stage) throws Exception{


        // define the primary stage
        this.primaryStage = stage;
        this.primaryStage.setTitle("The Facebook");

        //try {
            //loads root layout from fxml file
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/menu.fxml"));


            // create the new Scene
        //    Scene scene = new Scene(this.rootLayout);
        //    primaryStage.setScene(scene);
        //    primaryStage.setResizable(false);
        //    primaryStage.show();

       // }

        // catch (IOException ex){
         //   System.out.println("ERROR" + ex.getMessage());
       // }

    }

    public static void main(String[] args) {

        launch(args);
    }


}
