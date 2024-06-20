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

public class AccountCardController implements Initializable {
    SaveLoad save;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TextField name;
    @FXML
    private TextField accNo;
    @FXML
    private ChoiceBox<String> debitcreditbox = new ChoiceBox<>();
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
        if (name.getText().equals("") || accNo.getText().equals("") || debitcreditbox.getValue() == null) {
            add.setVisible(false);
            warning.setVisible(true);
            warning.setText("Please enter your name, account number, and card!");
        } else {
            addDebitCredit(debitcreditbox.getValue());
            warning.setVisible(false);
            add.setVisible(true);
            add.setText("Your Card has been added!");
            System.out.println("Add Button is clicked!");
        }
    }

    private void addDebitCredit(String card) {
        String nm = name.getText();
        String debnum = accNo.getText();
        save.saveData(save.loadData(file).getUsername(), save.loadData(file).getEmail(),
                save.loadData(file).getPassword(), save.loadData(file).getPoints(),
                save.loadData(file).getAddress(), save.loadData(file).getBricksAmount600(),
                save.loadData(file).getBricksAmount1000(), card, nm, debnum,
                save.loadData(file).getEWallet(), save.loadData(file).getPhoneNumEWallet(), 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save = new SaveLoad();
        debitcreditbox.getItems().addAll("BCA", "Mandiri", "BNI", "Visa", "BRI");
    }

}
