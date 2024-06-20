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

public class welcomeController implements Initializable {
    SaveLoad saveload;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String file = "PersonalData.xml";

    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label warning;

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        String name = usernameInput.getText();
        String password = passwordInput.getText();
        if (name.equals(saveload.loadData(file).getUsername()) && password.equals(saveload.loadData(file).getPassword())) {
            root = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            System.out.println("Login successful");
        } else if (name.equals(saveload.loadData("ManagementData.xml").getUsername()) &&
        password.equals(saveload.loadData("ManagementData.xml").getPassword())) {
            root = FXMLLoader.load(getClass().getResource("/Management/home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            System.out.println("Login successful");
        } else {
            warning.setText("Username or password incorrect. Please try again.");
        }
    }

    @FXML
    public void createAccountPageButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Welcome/welcomeSignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveload = new SaveLoad();
    }
}
