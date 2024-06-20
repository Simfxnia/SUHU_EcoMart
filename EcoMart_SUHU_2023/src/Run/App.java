package Run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Welcome/welcome.fxml"));
        Scene scene = new Scene(root);
        Image icon = new Image("ImageIcon/Logo.png");

        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle("EcoMart");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

        // Abdullah (22523208)
        // Simfoni Sekar Bhuana (22523165)
        // Muhammad Umar Arrasyid (22523273)
        // Mikkel Bagus Santoso (22523300)

        // For management, login with:
        // Username: EcoMart@gmail.com
        // Password: 1234  
    }

}
