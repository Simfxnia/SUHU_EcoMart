package UserProfile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class AccountChangePasswordController implements Initializable {
    SaveLoad save;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane ctrlpane;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField newpass;
    @FXML
    private Label warning;
    @FXML
    private Label saved;

    @FXML
    private void changePass(ActionEvent event) {
        if (pass.getText().equals(null) || pass.getText().equals("")) {
            warning.setVisible(true);
            saved.setVisible(false);
        } else if (pass.getText().equals(newpass.getText())) {
            warning.setVisible(false);
            saved.setVisible(true);
            save.saveData(save.loadData(file).getUsername(),
                    save.loadData(file).getEmail(), pass.getText(), save.loadData(file).getPoints(),
                    save.loadData(file).getAddress(), save.loadData(file).getBricksAmount600(),
                    save.loadData(file).getBricksAmount1000(), save.loadData(file).getCard(),
                    save.loadData(file).getFullNameCreDebit(), save.loadData(file).getAccountDebitNum(),
                    save.loadData(file).getEWallet(), save.loadData(file).getPhoneNumEWallet(), 0);
            System.out.println("Change Password Button is clicked!");
        } else {
            warning.setVisible(true);
            saved.setVisible(false);
        }
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane email = FXMLLoader.load(getClass().getResource("/UserProfile/accountEmail.fxml"));
        ctrlpane.getChildren().setAll(email);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warning.setVisible(false);
        saved.setVisible(false);
        save = new SaveLoad();
    }

}
