package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Vue_Main extends Application {
    // Une seule méthode start
    public void start(Stage stage) {
        // Crée une instance de VBoxRoot
        VBoxRoot root = new VBoxRoot();

        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.setTitle("Hello JavaFX");
        stage.show();
    }

    // Une seule méthode main
    public static void main(String[] args) {
        Application.launch(args);
    }
}
