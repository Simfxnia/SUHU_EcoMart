package UserProfile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AccountEmailController implements Initializable {
    SaveLoad saveload;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TextField currentEmail;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField pass;
    @FXML
    private TextField confirmPass;
    @FXML
    private Label warning;
    @FXML
    private Button forgot;
    @FXML
    private Label saved;

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane profile = FXMLLoader.load(getClass().getResource("/UserProfile/profilePage.fxml"));
        cntrPane.getChildren().setAll(profile);
    }

    @FXML
    private void forgotPassButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane pass = FXMLLoader.load(getClass().getResource("/UserProfile/accountChangePassword.fxml"));
        cntrPane.getChildren().setAll(pass);
    }

    @FXML
    private void saveButton(ActionEvent event) {
        if (newEmail.getText().equals("") || newEmail.getText().equals(null)) {
            saved.setVisible(false);
            warning.setVisible(true);
            warning.setText("Please enter your new email!");
        } else if (saveload.loadData(file).getEmail().equals(currentEmail.getText())) {
            if (saveload.loadData(file).getPassword().equals(pass.getText())
                    && pass.getText().equals(confirmPass.getText())) {
                String email = newEmail.getText();
                saveload.saveData(saveload.loadData(file).getUsername(),
                        email, saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                        saveload.loadData(file).getAddress(), saveload.loadData(file).getBricksAmount600(),
                        saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(),
                        saveload.loadData(file).getFullNameCreDebit(), saveload.loadData(file).getAccountDebitNum(),
                        saveload.loadData(file).getEWallet(), saveload.loadData(file).getPhoneNumEWallet(),
                        saveload.loadData(file).getTotalPrice());
                warning.setVisible(false);
                forgot.setVisible(false);
                saved.setVisible(true);
                saved.setText("Your new email has been saved!");
            } else {
                saved.setVisible(false);
                forgot.setVisible(true);
                warning.setVisible(true);
                warning.setText("Please check your password again!");
            }
        } else {
            saved.setVisible(false);
            warning.setVisible(true);
            warning.setText("Please enter your current email correctly!");
        }
        System.out.println("Save Button is clicked!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
        forgot.setVisible(false);
        if (saveload.loadData(file).getEmail() == null || saveload.loadData(file).getEmail() == "") {
            saved.setText("Leave the current email as blank if you have not confirmed your email before.");
        }
    }

}
