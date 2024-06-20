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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AccountEditProfileController implements Initializable {
    SaveLoad saveload;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane ctrlpane;
    @FXML
    private TextField newName;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label warning;
    @FXML
    private Label saved;

    @FXML
    private void SaveAction(ActionEvent event) {
        System.out.println("Save Button is clicked!");
        String name = newName.getText();
        if (name.equals("")) {
            saved.setVisible(false);
            warning.setVisible(true);
            warning.setText("Name cannot be blank.");
        } else {
            saveload.saveData(name, saveload.loadData(file).getEmail(),
                    saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                    saveload.loadData(file).getAddress(), saveload.loadData(file).getBricksAmount600(),
                    saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(),
                    saveload.loadData(file).getFullNameCreDebit(), saveload.loadData(file).getAccountDebitNum(),
                    saveload.loadData(file).getEWallet(), saveload.loadData(file).getPhoneNumEWallet(), 0);
            warning.setVisible(false);
            saved.setVisible(true);
            saved.setText("Your name has been saved!");
            System.out.println("Edit Name Button is clicked!");
        }
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane eWallet = FXMLLoader.load(getClass().getResource("/UserProfile/profilePage.fxml"));
        ctrlpane.getChildren().setAll(eWallet);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
        newName.setText(saveload.loadData(file).getUsername());
    }

}
