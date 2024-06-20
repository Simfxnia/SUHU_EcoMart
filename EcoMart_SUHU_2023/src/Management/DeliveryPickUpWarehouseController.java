package Management;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DeliveryPickUpWarehouseController implements Initializable {
    Data data;
    SaveLoad save;
    LinkedList<Data> loadedDataArray;
    private String file1 = "SelectedPickUp.xml";
    private String file2 = "PickUpTable.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Label username;
    @FXML
    private Label amount;
    @FXML
    private Label address;
    @FXML
    private Label date;
    @FXML
    private Label clock;
    @FXML
    private Label warning;
    @FXML
    private ChoiceBox<String> warehouse = new ChoiceBox<>();

    @FXML
    private void ScheduleDeliveryButton(ActionEvent event) throws IOException {
        if (warehouse.getValue() == null) {
            warning.setVisible(true);
            warning.setText("Please select the warehouse to transfer the bottles!");
        } else {
            System.out.println("start");
            warning.setVisible(false);
            String wh = warehouse.getValue();
            data.setPoints(save.loadData(file1).getBricksAmount600(),
                save.loadData(file1).getBricksAmount1000());
            save.saveData(data.getPoints(), date.getText(), clock.getText(), amount.getText(), false);
            save.deleteData(username.getText(), save.loadData(file1).getBricksAmount600(),
                save.loadData(file1).getBricksAmount1000(), address.getText(), file2);
            save.saveData(wh, username.getText(), "Income (PickUp)", save.loadData(file1).getBricksAmount600(),
                save.loadData(file1).getBricksAmount1000());
            save.saveData("", 0, 0, "", "", "", false, true);
            System.out.println("Confirm Schedule Button is clicked!");
            AnchorPane tableshop = FXMLLoader.load(getClass().getResource("/Management/deliveryPickUp.fxml"));
            cntrPane.getChildren().setAll(tableshop);
        }
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane tablepickup = FXMLLoader.load(getClass().getResource("/Management/deliveryPickUp.fxml"));
        cntrPane.getChildren().setAll(tablepickup);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save = new SaveLoad();
        data = new Data();
        warning.setVisible(false);
        warehouse.getItems().addAll("Sleman EcoMart", "Kota Yogyakarta EcoMart", "Bantul EcoMart");
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
        date.setText(save.loadData(file1).getDate());
        clock.setText(save.loadData(file1).getTime());
    }

}
