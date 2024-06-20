package Management;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable{
    Data data;
    SaveLoad saveload;
    private int pie60 = 0;
    private int pie100 = 0;
    private String sleman = "SlemanDepositTable.xml";
    private String jogja = "JogjaDepositTable.xml";
    private String bantul = "BantulDepositTable.xml";
    private String st = "";
    private int b60 = 0;
    private int b100 = 0;
    @FXML
    private AnchorPane home;
    @FXML
    private ChoiceBox<String> warehouse = new ChoiceBox<>();
    @FXML
    private PieChart chart;
    ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();

    @FXML
    private void statisticsButton(ActionEvent event) throws IOException {
        System.out.println("Statistics Button is clicked!");
        if(warehouse.getValue() != null && warehouse.getValue() != "") {
            if(warehouse.getValue() == "Sleman EcoMart") {
                AnchorPane sleman = FXMLLoader.load(getClass().getResource("/Management/statisticsSleman.fxml"));
                home.getChildren().setAll(sleman);
            } else if (warehouse.getValue() == "Kota Yogyakarta EcoMart") {
                AnchorPane jogja = FXMLLoader.load(getClass().getResource("/Management/statisticsYogyakarta.fxml"));
                home.getChildren().setAll(jogja);
            } else if (warehouse.getValue() == "Bantul EcoMart") {
                AnchorPane bantul = FXMLLoader.load(getClass().getResource("/Management/statisticsBantul.fxml"));
                home.getChildren().setAll(bantul);
            }
        }
    }

    @FXML
    private void pickupButton(ActionEvent event) throws IOException {
        System.out.println("Pick Up Button is clicked!");
        AnchorPane pickup = FXMLLoader.load(getClass().getResource("/Management/deliveryPickUp.fxml"));
        home.getChildren().setAll(pickup);
    }

    @FXML
    private void shopButton(ActionEvent event) throws IOException {
        System.out.println("Shop Button is clicked!");
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Management/deliveryShop.fxml"));
        home.getChildren().setAll(shop);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
        data = new Data();
        warehouse.getItems().addAll("Sleman EcoMart", "Kota Yogyakarta EcoMart", "Bantul EcoMart");
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(sleman);
        for (int i = 0; i < loadedDataArray.size(); i++) {
            data = loadedDataArray.get(i);
            st = data.getStatus();
            b60 = data.getBricksAmount600();
            b100 = data.getBricksAmount1000();
            if(st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                pie60 += b60;
                pie100 += b100;
            } else if (st.equals("Output (buy)")) {
                pie60 -= b60;
                pie100 -= b100; 
            }
        }
        loadedDataArray = saveload.loadArrayData(jogja);
        for (int i = 0; i < loadedDataArray.size(); i++) {
            data = loadedDataArray.get(i);
            st = data.getStatus();
            b60 = data.getBricksAmount600();
            b100 = data.getBricksAmount1000();
            if(st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                pie60 += b60;
                pie100 += b100;
            } else if (st.equals("Output (buy)")) {
                pie60 -= b60;
                pie100 -= b100; 
            }
        }
        loadedDataArray = saveload.loadArrayData(bantul);
        for (int i = 0; i < loadedDataArray.size(); i++) {
            data = loadedDataArray.get(i);
            st = data.getStatus();
            b60 = data.getBricksAmount600();
            b100 = data.getBricksAmount1000();
            if(st.equals("Income (PickUp)") || st.equals("Income (DropOff)")) {
                pie60 += b60;
                pie100 += b100;
            } else if (st.equals("Output (buy)")) {
                pie60 -= b60;
                pie100 -= b100; 
            }
        }
        pie.add(new PieChart.Data(pie60+" pcs 600ml bottle", pie60));
        pie.add(new PieChart.Data(pie100+" pcs 1000ml bottle", pie100));
        chart.setData(pie);
    }
    
}
