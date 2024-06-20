package Management;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import data.TableData;
import data.TableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class StatisticsBantulController implements Initializable{
    Data data;
    TableData selectedData;
    TableList tablelist;
    SaveLoad saveload;
    private String path = "BantulDepositTable.xml";
    private String nm = "";
    private String st = "";
    private int b60 = 0;
    private int b100 = 0;
    private int pie60 = 0;
    private int pie100 = 0;
    ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
    @FXML
    private PieChart chart;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private TableView<TableData> tvwarehouse;
    @FXML
    private TableColumn<TableData, String> tcName;
    @FXML
    private TableColumn<TableData, String> tcStatus;
    @FXML
    private TableColumn<TableData, Integer> tcbricks600;
    @FXML
    private TableColumn<TableData, Integer> tcbricks1000;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcbricks600.setCellValueFactory(new PropertyValueFactory<>("amount600"));
        tcbricks1000.setCellValueFactory(new PropertyValueFactory<>("amount1000"));
        tablelist = new TableList();
        saveload = new SaveLoad();
        data = new Data();
        tvwarehouse.setItems(tablelist.getData());
        LinkedList<Data> loadedDataArray = saveload.loadArrayData(path);
        for (int i = loadedDataArray.size()-1; i >= 0; i--) {
            data = loadedDataArray.get(i);
            nm = data.getUsername();
            st = data.getStatus();
            b60 = data.getBricksAmount600();
            b100 = data.getBricksAmount1000();
            tablelist.setData(nm, st, b60, b100);
            tvwarehouse.setItems(tablelist.getData());
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

    @FXML
    private void goBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane home = FXMLLoader.load(getClass().getResource("/Management/home.fxml"));
        cntrPane.getChildren().setAll(home);
    }


}
