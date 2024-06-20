package Deposit;

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

public class DepositPickUpController implements Initializable {
    Data data;
    SaveLoad saveload;
    private String bricks600 = "";
    private String bricks1000 = "";
    private int br6;
    private int br10;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Label addresslb;
    @FXML
    private Label amount600;
    @FXML
    private Label amount1000;
    @FXML
    private Label warning;
    @FXML
    private TextField newAddress;

    @FXML
    private void add600Button(ActionEvent event) {
        System.out.println("Add Button is clicked!");
        bricks600 = String.valueOf(data.addBricksAmount600());
        amount600.setText(bricks600);
    }

    @FXML
    private void substract600Button(ActionEvent event) {
        System.out.println("Substract Button is clicked!");
        bricks600 = String.valueOf(data.substractBricksAmount600());
        amount600.setText(bricks600);
    }

    @FXML
    private void add1000Button(ActionEvent event) {
        System.out.println("Add Button is clicked!");
        bricks1000 = String.valueOf(data.addBricksAmount1000());
        amount1000.setText(bricks1000);
    }

    @FXML
    private void substract1000Button(ActionEvent event) {
        System.out.println("Substract Button is clicked!");
        bricks1000 = String.valueOf(data.substractBricksAmount1000());
        amount1000.setText(bricks1000);
    }

    @FXML
    private void confirmAddressButton(ActionEvent event) {
        String add = newAddress.getText();
        addresslb.setText(add);
        System.out.println("Confirm Address Button is clicked!");
    }

    @FXML
    private void pickUpButtonAction(ActionEvent event) throws IOException {
        if (amount600.getText().equals("0") && amount1000.getText().equals("0")) {
            warning.setVisible(true);
            warning.setText("You still have not deposit anything!");
        } else if (addresslb.getText() == "") {
            warning.setVisible(true);
            warning.setText("Please fill in your address first!");
        } else {
            if (amount600.getText().equals("0")) {
                br6 = 0;
                br10 = Integer.parseInt(bricks1000);
            } else if (amount1000.getText().equals("0")) {
                br6 = Integer.parseInt(bricks600);
                br10 = 0;
            } else {
                br6 = Integer.parseInt(bricks600);
                br10 = Integer.parseInt(bricks1000);
            }
            warning.setVisible(false);
            saveload.saveData(saveload.loadData(file).getUsername(), br6, br10,
                    addresslb.getText(), "", "", false, false);
            saveload.saveData(saveload.loadData(file).getUsername(), saveload.loadData(file).getEmail(),
                    saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                    addresslb.getText(), saveload.loadData(file).getBricksAmount600(),
                    saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(),
                    saveload.loadData(file).getFullNameCreDebit(), saveload.loadData(file).getAccountDebitNum(),
                    saveload.loadData(file).getEWallet(), saveload.loadData(file).getPhoneNumEWallet(), 0);
            AnchorPane delivery = FXMLLoader.load(getClass().getResource("/Deposit/depositPickUpDelivery.fxml"));
            cntrPane.getChildren().setAll(delivery);
            System.out.println("Pick Up Button is clicked!");
        }
    }

    @FXML
    private void editAddressAction(ActionEvent event) throws IOException {
        System.out.println("Edit Main Address Button is clicked!");
        AnchorPane address = FXMLLoader.load(getClass().getResource("/UserProfile/accountAddress.fxml"));
        cntrPane.getChildren().setAll(address);
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane deposit = FXMLLoader.load(getClass().getResource("/Deposit/depositPage.fxml"));
        cntrPane.getChildren().setAll(deposit);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Data();
        saveload = new SaveLoad();
        warning.setVisible(false);
        addresslb.setText(saveload.loadData(file).getAddress());
    }

}
