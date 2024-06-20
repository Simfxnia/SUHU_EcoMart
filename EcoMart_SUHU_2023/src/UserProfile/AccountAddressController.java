package UserProfile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AccountAddressController implements Initializable {
    Data data;
    SaveLoad saveload;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TextField city;
    @FXML
    private TextField province;
    @FXML
    private TextField road;
    @FXML
    private TextField postalCode;
    @FXML
    private Label warning;
    @FXML
    private Label saved;

    @FXML
    private void saveData(ActionEvent event) {
        String ct = city.getText();
        String prov = province.getText();
        String rd = road.getText();
        String pc = postalCode.getText();
        if (city.equals("") || prov.equals("") || rd.equals("") || pc.equals("")) {
            saved.setVisible(false);
            warning.setVisible(true);
            warning.setText("Please fill in your address!");
        } else {
            data.setAddress(rd, ct, prov, pc);
            saveload.saveData(saveload.loadData(file).getUsername(),saveload.loadData(file).getEmail(),
                    saveload.loadData(file).getPassword(),
                    saveload.loadData(file).getPoints(),
                    data.getAddress(), saveload.loadData(file).getBricksAmount600(),
                    saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(), saveload.loadData(file).getFullNameCreDebit(),
                    saveload.loadData(file).getAccountDebitNum(), saveload.loadData(file).getEWallet(),
                    saveload.loadData(file).getPhoneNumEWallet(), saveload.loadData(file).getTotalPrice());
            warning.setVisible(false);
            saved.setVisible(true);
            saved.setText("Your contact and address has been saved!");
            System.out.println("Save Button is clicked!");
        }
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        if (saveload.loadData(file).getTotalPrice() == 0) {
            AnchorPane profile = FXMLLoader.load(getClass().getResource("/UserProfile/profilePage.fxml"));
            cntrPane.getChildren().setAll(profile);
        } else {
            AnchorPane cart = FXMLLoader.load(getClass().getResource("/Shop/shopMyCart.fxml"));
            cntrPane.getChildren().setAll(cart);
        }
            System.out.println("Back Button is clicked!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Data();
        saveload = new SaveLoad();
    }
}
