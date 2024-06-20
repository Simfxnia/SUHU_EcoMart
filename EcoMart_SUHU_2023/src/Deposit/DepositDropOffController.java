package Deposit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;

public class DepositDropOffController implements Initializable {
    SaveLoad save;
    Data data;
    private String file = "PersonalData.xml";
    private String bricks600 = "";
    private String bricks1000 = "";
    private int br6;
    private int br10;
    private Pane pain;
    private Button notifybt;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Button sub6;
    @FXML
    private Button sub10;
    @FXML
    private Button confirm;
    @FXML
    private Label amount600;
    @FXML
    private Label amount1000;
    @FXML
    private Label warning;
    @FXML
    private ChoiceBox<String> warehouse = new ChoiceBox<>();

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
    private void notifyWarehousButton(ActionEvent event) throws IOException {
        String place = warehouse.getValue();
        if (amount600.getText().equals("0") && amount1000.getText().equals("0")) {
            warning.setVisible(true);
            warning.setText("You still have not deposit anything!");
        } else {
            if (place == null) {
                warning.setVisible(true);
                warning.setText("Please select a warehouse!");
            } else {
                if (amount600.getText().equals("0")) {
                    br6 = 0;
                    br10 = Integer.parseInt(bricks1000);
                } else if (amount1000.getText().equals("0")) {
                    br6 = Integer.parseInt(bricks600);
                    br10 = 0;
                } else {
                    System.out.println(amount600.getText() + "here");
                    br6 = Integer.parseInt(bricks600);
                    br10 = Integer.parseInt(bricks1000);
                }
                save.saveData(place, save.loadData("PersonalData.xml").getUsername(), "Income (DropOff)", br6, br10);
                int poin = save.loadData(file).getPoints();
                data.setPoints(br6, br10);
                save.saveData(save.loadData(file).getUsername(),
                save.loadData(file).getEmail(), save.loadData(file).getPassword(), data.getPoints()+poin,
                save.loadData(file).getAddress(), save.loadData(file).getBricksAmount600()+br6,
                save.loadData(file).getBricksAmount1000()+br10, save.loadData(file).getCard(), 
                save.loadData(file).getFullNameCreDebit(), save.loadData(file).getAccountDebitNum(),
                save.loadData(file).getEWallet(), save.loadData(file).getPhoneNumEWallet(), 0);
                warning.setVisible(false);
                warehouse.setDisable(true);
                sub6.setDisable(true);
                sub10.setDisable(true);
                confirm.setDisable(true);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("depositDropOffPopup.fxml"));
                Parent newroot = loader.load();
                pain = new Pane();
                pain.setStyle("-fx-background-color: #ffffff00; -fx-background-radius: 15;");
                pain.getChildren().add(newroot);
                pain.setPrefSize(600, 400);
                pain.setLayoutX(250);
                pain.setLayoutY(160);
                notifybt = new Button();
                notifybt.setText("Home");
                notifybt.setStyle(
                        "-fx-text-fill: #049372; -fx-background-color: #ffffff; -fx-background-radius: 15; -fx-font-weight: bold; -fx-font-size: 18px;");
                notifybt.setPrefSize(175, 55);
                notifybt.setLayoutX(312);
                notifybt.setLayoutY(384);
                pain.getChildren().add(notifybt);
                cntrPane.getChildren().add(pain);
                notifybt.setOnAction(event1 -> {
                    try {
                        homeButtonAction(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("Notify Warehouse Button is clicked!");
            }
        }
    }

    private void homeButtonAction(ActionEvent event) throws IOException {
        System.out.println("Home Button is clicked!");
        cntrPane = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();
        AnchorPane homemenu = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml"));
        cntrPane.getChildren().setAll(homemenu);
    }

    @FXML
    private void goBackAction(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane pick = FXMLLoader.load(getClass().getResource("/Deposit/depositPage.fxml"));
        cntrPane.getChildren().setAll(pick);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        save = new SaveLoad();
        data = new Data();
        warning.setVisible(false);
        warehouse.getItems().addAll("Sleman EcoMart", "Kota Yogyakarta EcoMart", "Bantul EcoMart");
    }

}