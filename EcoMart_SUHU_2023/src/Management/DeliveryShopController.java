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

public class DeliveryShopController implements Initializable{
    Data data;
    TableData selectedData;
    TableList tablelist;
    SaveLoad saveload;
    private TableViewSelectionModel<TableData> selectionModel;
    private String nm = "";
    private String ad = "";
    private int b60 = 0;
    private int b100 = 0;
    private String p60 = "";
    private String p100 = "";
    private String tot = "";
    private String pm = "";
    private String path = "OnGoingDelivery.xml";
    private String price;
    private double pricenum;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TableView<TableData> tvdelivery;
    @FXML
    private TableColumn<TableData, String> tcName;
    @FXML
    private TableColumn<TableData, Integer> tcbricks600;
    @FXML
    private TableColumn<TableData, String> tcprice600;
    @FXML
    private TableColumn<TableData, Integer> tcbricks1000;
    @FXML
    private TableColumn<TableData, String> tcprice1000;
    @FXML
    private TableColumn<TableData, String> tctotalprice;
    @FXML
    private TableColumn<TableData, String> tcpaymet;
    @FXML
    private TableColumn<TableData, String> tcAddress;

    @FXML
    private void scheduleButton(ActionEvent event) throws IOException {
        selectedData = selectionModel.getSelectedItem();
        if (selectedData != null) {
            nm = selectedData.getName();
            b60 = selectedData.getAmount600();
            String price60 = String.valueOf(selectedData.getPrice600());
            double pricenum60 = Double.parseDouble(price60.substring(3, price60.length() - 1));
            b100 = selectedData.getAmount1000();
            price  = String.valueOf(selectedData.getPrice1000());
            pricenum = Double.parseDouble(price.substring(3, price.length() - 1));
            String pricetot = String.valueOf(selectedData.getTotalPrice());
            double pricenumtot = Double.parseDouble(pricetot.substring(3, pricetot.length() - 1));
            pm = selectedData.getPaymet();
            ad = selectedData.getAddress();
            saveload.saveData(nm, b60, pricenum60, b100, pricenum, pricenumtot, pm, ad, false, true);
            AnchorPane schedule = FXMLLoader.load(getClass().getResource("/Management/deliveryShopScheduling.fxml"));
            cntrPane.getChildren().setAll(schedule);
            System.out.println("Schedule Button is clicked!");
        }
    }

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane delivery = FXMLLoader.load(getClass().getResource("/Management/home.fxml"));
        cntrPane.getChildren().setAll(delivery);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcbricks600.setCellValueFactory(new PropertyValueFactory<>("amount600"));
        tcprice600.setCellValueFactory(new PropertyValueFactory<>("price600"));
        tcbricks1000.setCellValueFactory(new PropertyValueFactory<>("amount1000"));
        tcprice1000.setCellValueFactory(new PropertyValueFactory<>("price1000"));
        tctotalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tcpaymet.setCellValueFactory(new PropertyValueFactory<>("paymet"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tablelist = new TableList();
        saveload = new SaveLoad();
        data = new Data();
        tvdelivery.setItems(tablelist.getData());
        tvdelivery.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectionModel = tvdelivery.getSelectionModel();
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(path);
        for (int i = 0; i < loadedDataArray.size(); i++) {
            data = loadedDataArray.get(i);
            nm = data.getUsername();
            b60 = data.getBricksAmount600();
            p60 = String.valueOf(data.getPrice600());
            b100 = data.getBricksAmount1000();
            p100 = String.valueOf(data.getPrice1000());
            tot = String.valueOf(data.getTotalPrice());
            pm = data.getPayMet();
            ad = data.getAddress();
            tablelist.setData(nm, b60, "Rp " + p60, b100, "Rp " + p100, "Rp " + tot, pm, ad);
            tvdelivery.setItems(tablelist.getData());
        }
    }
}
