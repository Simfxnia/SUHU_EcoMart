package Deposit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DepositPickUpDelivery implements Initializable {
    Data data;
    SaveLoad save;
    private Pane pain;
    private Button pickupbt;
    private String file2 = "PickUpCart.xml";
    private String file1 = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private DatePicker date;
    @FXML
    private Button schedule;
    @FXML
    private Label username;
    @FXML
    private Label amount;
    @FXML
    private Label address;
    @FXML
    private Label warning;
    @FXML
    private ChoiceBox<String> clock = new ChoiceBox<>();

    @FXML
    private void ScheduleDeliveryButton(ActionEvent event) throws IOException {
        LocalDate selectedDate = date.getValue();
        if (selectedDate == null) {
            warning.setVisible(true);
            warning.setText("Please select your date to pick up");
        } else if (clock.getValue() == null) {
            warning.setVisible(true);
            warning.setText("Please select your time to pick up");
        } else {
            warning.setVisible(false);
            String day = String.valueOf(selectedDate.getDayOfMonth());
            String month = selectedDate.getMonth().toString();
            String year = String.valueOf(selectedDate.getYear());
            data.setDate(day, month, year);
            String time = clock.getValue();
            save.saveData(save.loadData(file2).getUsername(), save.loadData(file2).getBricksAmount600(),
                    save.loadData(file2).getBricksAmount1000(), save.loadData(file2).getAddress(),
                    data.getDate(), time, true, true);
            save.saveData(save.loadData(file1).getUsername(),
                save.loadData(file1).getEmail(), save.loadData(file1).getPassword(), save.loadData(file1).getPoints(),
                save.loadData(file1).getAddress(), save.loadData(file1).getBricksAmount600()+
                save.loadData(file2).getBricksAmount600(), save.loadData(file1).getBricksAmount1000()+
                save.loadData(file2).getBricksAmount1000(), save.loadData(file1).getCard(), 
                save.loadData(file1).getFullNameCreDebit(), save.loadData(file1).getAccountDebitNum(),
                save.loadData(file1).getEWallet(), save.loadData(file1).getPhoneNumEWallet(), 0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("depositPickUpPopup.fxml"));
            Parent newroot = loader.load();
            schedule.setDisable(true);
            pain = new Pane();
            pain.setStyle("-fx-background-color: #ffffff00; -fx-background-radius: 15;");
            pain.getChildren().add(newroot);
            pain.setPrefSize(600, 400);
            pain.setLayoutX(250);
            pain.setLayoutY(160);
            pickupbt = new Button();
            pickupbt.setText("Home");
            pickupbt.setStyle(
                    "-fx-text-fill: #049372; -fx-background-color: #ffffff; -fx-background-radius: 15; -fx-font-weight: bold; -fx-font-size: 18px;");
            pickupbt.setPrefSize(175, 55);
            pickupbt.setLayoutX(312);
            pickupbt.setLayoutY(384);
            pain.getChildren().add(pickupbt);
            cntrPane.getChildren().add(pain);
            pickupbt.setOnAction(event1 -> {
                try {
                    deliveryAction(event1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Schedule Delivery Button is clicked!");
        }
    }

    private void deliveryAction(ActionEvent event) throws IOException {
        System.out.println("Home Button is clicked!");
        cntrPane = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
        AnchorPane homemenu = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml"));
        cntrPane.getChildren().setAll(homemenu);
        save.saveData("", 0, 0, save.loadData(file1).getAddress(), "", "", false, false);
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane deposit = FXMLLoader.load(getClass().getResource("/Deposit/depositPickUpPage.fxml"));
        cntrPane.getChildren().setAll(deposit);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save = new SaveLoad();
        data = new Data();
        warning.setVisible(false);
        String bottle600 = save.loadData(file2).getBricksAmount600() + "pcs 600ml Bottles";
        String bottle1000 = save.loadData(file2).getBricksAmount1000() + "pcs 1000ml Bottles";
        if (save.loadData(file2).getBricksAmount600() == 0) {
            amount.setText(bottle1000);
        } else if (save.loadData(file2).getBricksAmount1000() == 0) {
            amount.setText(bottle600);
        } else {
            amount.setText(bottle600 + " & " + bottle1000);
        }
        username.setText(save.loadData(file2).getUsername());
        address.setText(save.loadData(file2).getAddress());
        clock.getItems().addAll("10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00",
                "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30");
    }

}
