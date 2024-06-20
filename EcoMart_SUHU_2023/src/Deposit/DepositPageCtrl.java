package Deposit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class DepositPageCtrl implements Initializable {

    @FXML
    private AnchorPane cntrPane;

    @FXML
    private void pickUpButtonAction(ActionEvent event) throws IOException {
        System.out.println("Pick Up Page Button is clicked!");
        AnchorPane pick = FXMLLoader.load(getClass().getResource("/Deposit/depositPickUpPage.fxml"));
        cntrPane.getChildren().setAll(pick);
    }

    @FXML
    private void dropOffButtonAction(ActionEvent event) throws IOException {
        System.out.println("Drop Off Page Button is clicked!");
        AnchorPane drop = FXMLLoader.load(getClass().getResource("/Deposit/depositDropOffPage.fxml"));
        cntrPane.getChildren().setAll(drop);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
