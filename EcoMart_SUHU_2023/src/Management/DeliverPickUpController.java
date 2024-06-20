package Management;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import data.TableData;
import data.TableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DeliverPickUpController implements Initializable{
    Data data;
    TableData selectedData;
    TableList tablelist;
    SaveLoad saveload;
    private TableViewSelectionModel<TableData> selectionModel;
    private String nm = "";
    private int b60 = 0;
    private int b100 = 0;
    private String ad = "";
    private String dt = "";
    private String tm = "";
    private String arrayloadedPath = "PickUpTable.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TableView<TableData> tvdelivery;
    @FXML
    private TableColumn<TableData, String> tcName;
    @FXML
    private TableColumn<TableData, Integer> tcbricks600;
    @FXML
    private TableColumn<TableData, Integer> tcbricks1000;
    @FXML
    private TableColumn<TableData, String> tcAddress;
    @FXML
    private TableColumn<TableData, String> tcDate;
    @FXML
    private TableColumn<TableData, String> tcTime;

    @FXML
    private void confirmButton(ActionEvent event) throws IOException {
        selectedData = selectionModel.getSelectedItem();
        if (selectedData != null) {
            nm = selectedData.getName();
            b60 = selectedData.getAmount600();
            b100 = selectedData.getAmount1000();
            ad = selectedData.getAddress();
            dt = selectedData.getDate();
            tm = selectedData.getTime();
            saveload.saveData(nm, b60, b100, ad, dt, tm, false, true);
            AnchorPane schedule = FXMLLoader.load(getClass().getResource("/Management/deliveryPickUpWarehouse.fxml"));
            cntrPane.getChildren().setAll(schedule);
            System.out.println("Schedule Button is clicked!");
        }
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane home = FXMLLoader.load(getClass().getResource("/Management/home.fxml"));
        cntrPane.getChildren().setAll(home);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcbricks600.setCellValueFactory(new PropertyValueFactory<>("amount600"));
        tcbricks1000.setCellValueFactory(new PropertyValueFactory<>("amount1000"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tablelist = new TableList();
        saveload = new SaveLoad();
        data = new Data();
        tvdelivery.setItems(tablelist.getData());
        tvdelivery.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectionModel = tvdelivery.getSelectionModel();
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(arrayloadedPath);
        for (int i = loadedDataArray.size(); i > 0; i--) {
            data = loadedDataArray.pollLast();
            nm = data.getUsername();
            b60 = data.getBricksAmount600();
            b100 = data.getBricksAmount1000();
            ad = data.getAddress();
            dt = data.getDate();
            tm = data.getTime();
            tablelist.setData(nm, b60, b100, ad, dt, tm);
            tvdelivery.setItems(tablelist.getData());
        }
    }
    
}
