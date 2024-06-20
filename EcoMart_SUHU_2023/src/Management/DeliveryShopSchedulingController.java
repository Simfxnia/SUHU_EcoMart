package Management;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DeliveryShopSchedulingController implements Initializable {
    Data data;
    SaveLoad save;
    LinkedList<Data> loadedDataArray;
    private String file1 = "SelectedCart.xml";
    private String file2 = "OnGoingDelivery.xml";
    private String st = "";
    private int b60 = 0;
    private int b100 = 0;
    private int pie60 = 0;
    private int pie100 = 0;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private DatePicker date;
    @FXML
    private Label username;
    @FXML
    private Label amount;
    @FXML
    private Label address;
    @FXML
    private Label warning;
    @FXML
    private ChoiceBox<String> warehouse = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> clock = new ChoiceBox<>();

    @FXML
    private void ScheduleDeliveryButton(ActionEvent event) throws IOException {
        LocalDate selectedDate = date.getValue();
        if (warehouse.getValue() == null) {
            warning.setVisible(true);
            warning.setText("Please select the warehouse to get the bottles from!");
        } else if (selectedDate == null) {
            warning.setVisible(true);
            warning.setText("Please select your date to pick up");
        } else if (clock.getValue() == null) {
            warning.setVisible(true);
            warning.setText("Please select your time to pick up");
        } else {
            if (warehouse.getValue() == "Sleman EcoMart") {
                loadedDataArray = save.loadArrayData("SlemanDepositTable.xml");
                for (int i = 0; i < loadedDataArray.size(); i++) {
                    data = loadedDataArray.get(i);
                    st = data.getStatus();
                    b60 = data.getBricksAmount600();
                    b100 = data.getBricksAmount1000();
                    if (st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                        pie60 += b60;
                        pie100 += b100;
                    } else if (st.equals("Output (buy)")) {
                        pie60 -= b60;
                        pie100 -= b100;
                    }
                }
            } else if (warehouse.getValue() == "Kota Yogyakarta EcoMart") {
                loadedDataArray = save.loadArrayData("JogjaDepositTable.xml");
                for (int i = 0; i < loadedDataArray.size(); i++) {
                    data = loadedDataArray.get(i);
                    st = data.getStatus();
                    System.out.println(st);
                    b60 = data.getBricksAmount600();
                    b100 = data.getBricksAmount1000();
                    if (st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                        pie60 += b60;
                        pie100 += b100;
                    } else if (st.equals("Output (buy)")) {
                        pie60 -= b60;
                        pie100 -= b100;
                    }
                }
            } else if (warehouse.getValue() == "Bantul EcoMart") {
                loadedDataArray = save.loadArrayData("BantulDepositTable.xml");
                for (int i = 0; i < loadedDataArray.size(); i++) {
                    data = loadedDataArray.get(i);
                    st = data.getStatus();
                    b60 = data.getBricksAmount600();
                    b100 = data.getBricksAmount1000();
                    if (st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                        pie60 += b60;
                        pie100 += b100;
                    } else if (st.equals("Output (buy)")) {
                        pie60 -= b60;
                        pie100 -= b100;
                    }
                }
            }
            System.out.println(pie60);
            System.out.println(pie100);
            if (pie60 <= save.loadData(file1).getBricksAmount600() || pie100 <= save.loadData(file1).getBricksAmount1000()) {
                warning.setVisible(true);
                warning.setText("Not enough stock in this warehouse!");
            } else {
                warning.setVisible(false);
                String wr = warehouse.getValue();
                String day = String.valueOf(selectedDate.getDayOfMonth());
                String month = selectedDate.getMonth().toString();
                String year = String.valueOf(selectedDate.getYear());
                data.setDate(day, month, year);
                String time = clock.getValue();
                data.setPoints(save.loadData(file1).getBricksAmount600(), save.loadData(file1).getBricksAmount1000());
                save.saveData(data.getPoints(), data.getDate(), time, amount.getText(), true);
                save.saveData(wr, save.loadData(file1).getUsername(), "Output (buy)",
                        save.loadData(file1).getBricksAmount600(),
                        save.loadData(file1).getBricksAmount1000());
                save.deleteData(username.getText(), save.loadData(file1).getBricksAmount600(),
                        save.loadData(file1).getBricksAmount1000(), save.loadData(file1).getPayMet(), address.getText(),
                        file2);
                save.saveData("", 0, 0, 0, 0, 0, "", "", false, true);
                System.out.println("Confirm Schedule Button is clicked!");
                AnchorPane tableshop = FXMLLoader.load(getClass().getResource("/Management/deliveryShop.fxml"));
                cntrPane.getChildren().setAll(tableshop);
            }
        }
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane tableshop = FXMLLoader.load(getClass().getResource("/Management/deliveryShop.fxml"));
        cntrPane.getChildren().setAll(tableshop);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save = new SaveLoad();
        data = new Data();
        warning.setVisible(false);
        String bottle600 = save.loadData(file1).getBricksAmount600() + "pcs 600ml Bottles";
        String bottle1000 = save.loadData(file1).getBricksAmount1000() + "pcs 1000ml Bottles";
        if (save.loadData(file1).getBricksAmount600() == 0) {
            amount.setText(bottle1000);
        } else if (save.loadData(file1).getBricksAmount1000() == 0) {
            amount.setText(bottle600);
        } else {
            amount.setText(bottle600 + " & " + bottle1000);
        }
        username.setText(save.loadData(file1).getUsername());
        address.setText(save.loadData(file1).getAddress());
        warehouse.getItems().addAll("Sleman EcoMart", "Kota Yogyakarta EcoMart", "Bantul EcoMart");
        clock.getItems().addAll("10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00",
                "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30");
    }

}
