package UserProfile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AccountEWalletController implements Initializable {
    SaveLoad save;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TextField phoneNo;
    @FXML
    private ChoiceBox<String> eWalletbox = new ChoiceBox<>();
    @FXML
    private Label warning;
    @FXML
    private Label add;

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane profile = FXMLLoader.load(getClass().getResource("/UserProfile/profilePage.fxml"));
        cntrPane.getChildren().setAll(profile);
    }

    @FXML
    private void addButton(ActionEvent event) {
        if (phoneNo.getText().equals("") || eWalletbox.getValue() == null) {
            add.setVisible(false);
            warning.setVisible(true);
            warning.setText("Please enter your phone number and payment method!");
        } else {
            addeWallet(eWalletbox.getValue());
            warning.setVisible(false);
            add.setVisible(true);
            add.setText("Your E-Wallet has been added!");
            System.out.println("Add Button is clicked!");
        }
    }

    private void addeWallet(String ewallet) {
        String nm = phoneNo.getText();
        save.saveData(save.loadData(file).getUsername(), save.loadData(file).getEmail(),
                save.loadData(file).getPassword(), save.loadData(file).getPoints(), save.loadData(file).getAddress(),
                save.loadData(file).getBricksAmount600(), save.loadData(file).getBricksAmount1000(),save.loadData(file).getCard(), 
                save.loadData(file).getFullNameCreDebit(), save.loadData(file).getAccountDebitNum(),
                ewallet, nm, 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eWalletbox.getItems().addAll("GoPay", "OVO", "ShopeePay", "DANA");
        save = new SaveLoad();
    }

}
