package Welcome;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController implements Initializable {
    SaveLoad saveload;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label warning;

    @FXML
    private void signInButtonAction(ActionEvent event) throws IOException {
        String name = usernameInput.getText();
        String password = passwordInput.getText();
        if (name.equals("") || password.equals("")) {
            warning.setText("Please enter your username, email, and password.");
            System.out.println("No input. Please fill the text field");
        } else {
            // saveload.saveData(name, password); // management only
            saveload.saveData(name, "", password, 0, "", 0, 0, "", "", "", "", "", 0);
            saveload.clearArrayxml("Inbox.xml");
            saveload.clearArrayxml("Points.xml");
            root = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            System.out.println("Sign in successful");
        }
    }

    @FXML
    private void goBackLoginPageButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Welcome/welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
    }

}
