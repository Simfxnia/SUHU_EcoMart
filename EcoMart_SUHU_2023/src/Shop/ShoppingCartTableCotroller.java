package Shop;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShoppingCartTableCotroller implements Initializable {
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
    private String personalPath = "PersonalData.xml";
    private String arrayloadedPath = "SavetoCart.xml";
    private String managementPath = "OnGoingDelivery.xml";
    private String bricks;
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
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private Label amount600;
    @FXML
    private Label amount1000;
    @FXML
    private ChoiceBox<String> paymet = new ChoiceBox<>();

    @FXML
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
        saveload.saveData("", 0, 0, 0, 0, 0, "", "", false, false);
        tvdelivery.setItems(tablelist.getData());
        tvdelivery.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectionModel = tvdelivery.getSelectionModel();
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(arrayloadedPath);
        for (int i = loadedDataArray.size(); i > 0; i--) {
            data = loadedDataArray.pollLast();
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

    @FXML
    private void editButton(ActionEvent event) {
        selectedData = selectionModel.getSelectedItem();
        if (selectedData != null) {
            paymet.getItems().addAll("Cash", saveload.loadData(personalPath).getCard(),
                    saveload.loadData(personalPath).getEWallet());
            price = String.valueOf(selectedData.getPrice600());
            pricenum = Double.parseDouble(price.substring(3, price.length() - 1));
            data.setBricksAmount600(selectedData.getAmount600());
            data.setPrice600(pricenum);
            price = String.valueOf(selectedData.getPrice1000());
            pricenum = Double.parseDouble(price.substring(3, price.length() - 1));
            data.setBricksAmount1000(selectedData.getAmount1000());
            data.setPrice1000(pricenum);
            name.setText(selectedData.getName());
            paymet.setValue(data.getPayMet());
            data.setTotalPrice(data.getPrice600(), data.getPrice1000());
            address.setText(selectedData.getAddress());
            amount600.setText(String.valueOf(selectedData.getAmount600()));
            amount1000.setText(String.valueOf(selectedData.getAmount1000()));
            System.out.println("Edit Button is clicked!");
        }
    }

    @FXML
    private void updateButton(ActionEvent event) {
        if (selectedData != null) {
            nm = selectedData.getName();
            b60 = selectedData.getAmount600();
            b100 = selectedData.getAmount1000();
            pm = selectedData.getPaymet();
            ad = selectedData.getAddress();
            int index = getEditedIndex(nm, b60, b100, pm, ad);
            nm = name.getText();
            b60 = data.getBricksAmount600();
            double p600 = data.getPrice600();
            p60 = String.valueOf(p600);
            b100 = data.getBricksAmount1000();
            double p1000 = data.getPrice1000();
            p100 = String.valueOf(p1000);
            data.setTotalPrice(p600, p1000);
            double total = data.getTotalPrice();
            tot = String.valueOf(total);
            pm = paymet.getValue();
            ad = address.getText();
            saveload.editData(nm, b60, p600, b100, p1000, total, pm, ad, index, arrayloadedPath);
            tablelist.setData(nm, b60, "Rp " + p60, b100, "Rp " + p100, "Rp " + tot, pm, ad, index);
            selectedData = null;
            System.out.println("Update Button is clicked!");
        }
    }

    @FXML
    private void deleteButton(ActionEvent event) { 
        selectedData = selectionModel.getSelectedItem();
        if (selectedData != null) {
            saveload.deleteData(selectedData.getName(), selectedData.getAmount600(), selectedData.getAmount1000(),
                    selectedData.getPaymet(), selectedData.getAddress(), arrayloadedPath);
            tablelist.getData().remove(selectedData);
            System.out.println("Delete Button is clicked!");
        }
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        saveload.saveData(saveload.loadData(personalPath).getUsername(), 0, 0, 0,
                0, 0, "", saveload.loadData(personalPath).getAddress(),
                false, false);
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shopPage.fxml"));
        cntrPane.getChildren().setAll(shop);
    }

    @FXML
    private void checkoutAllButton(ActionEvent event) throws IOException {
        System.out.println("Checkout All Button is clicked!");
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(arrayloadedPath);
        LinkedList<Data> managementarray = saveload.loadArrayData(managementPath);
        managementarray.addAll(loadedDataArray);
        saveload.saveData(managementarray, managementPath);
        saveload.clearArrayxml(arrayloadedPath);
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shopComplete.fxml"));
        cntrPane.getChildren().setAll(shop);
        
    }

    @FXML
    private void add600Button(ActionEvent event) {
        selectedData = selectionModel.getSelectedItem();
        if (selectedData != null) {
            amount600.setText(String.valueOf(selectedData.getAmount600()));
            bricks = String.valueOf(data.addBricksAmount600());
            data.addPrice600();
            amount600.setText(bricks);
            System.out.println("Add Button is clicked!");
        }
    }

    @FXML
    private void substract600Button(ActionEvent event) {
        if (selectedData != null) {
            amount600.setText(String.valueOf(selectedData.getAmount600()));
            bricks = String.valueOf(data.substractBricksAmount600());
            data.substractPrice600();
            System.out.println(bricks);
            amount600.setText(bricks);
            System.out.println("Substract Button is clicked!");
        }
    }

    @FXML
    private void add1000Button(ActionEvent event) {
        if (selectedData != null) {
            amount1000.setText(String.valueOf(selectedData.getAmount1000()));
            bricks = String.valueOf(data.addBricksAmount1000());
            data.addPrice1000();
            amount1000.setText(bricks);
            System.out.println("Add Button is clicked!");
        }
    }

    @FXML
    private void substract1000Button(ActionEvent event) {
        if (selectedData != null) {
            amount1000.setText(String.valueOf(selectedData.getAmount1000()));
            bricks = String.valueOf(data.substractBricksAmount1000());
            data.substractPrice1000();
            amount1000.setText(bricks);
            System.out.println("Substract Button is clicked!");
        }
    }

    public int getEditedIndex(String name, int bricks600, int bricks1000, String paymet, String address) {
        int index = -1;
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(arrayloadedPath);
        for (int i = 0; i < loadedDataArray.size(); i++) {
            if (loadedDataArray.get(i).getUsername().equals(name)
                    && loadedDataArray.get(i).getBricksAmount600() == bricks600 &&
                    loadedDataArray.get(i).getBricksAmount1000() == bricks1000
                    && loadedDataArray.get(i).getPayMet().equals(paymet) &&
                    loadedDataArray.get(i).getAddress().equals(address)) {
                index = i;
                return index;
            }
        }
        return index;
    }
}
