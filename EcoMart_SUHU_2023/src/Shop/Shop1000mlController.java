package Shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Shop1000mlController implements Initializable {
    Data data;
    SaveLoad saveload;
    private String file1 = "PersonalData.xml";
    private String file2 = "ShopCart.xml";
    private String bricks;
    private String total;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Label amount;
    @FXML
    private Label price;
    @FXML
    private Button checkout;

    @FXML
    private void addButton(ActionEvent event) {
        System.out.println("Add Button is clicked!");
        bricks = String.valueOf(data.addBricksAmount1000());
        total = String.valueOf(data.addPrice1000());
        amount.setText(bricks);
        price.setText("Rp " + total);
    }

    @FXML
    private void substractButton(ActionEvent event) {
        System.out.println("Substract Button is clicked!");
        bricks = String.valueOf(data.substractBricksAmount1000());
        total = String.valueOf(data.substractPrice1000());
        amount.setText(bricks);
        price.setText("Rp " + total);
    }

    @FXML
    private void addtoCartButton(ActionEvent event) {
        System.out.println("Add to Cart Button is clicked!");
        int br1000 = Integer.parseInt(bricks);
        double pr1000 = Double.parseDouble(total);
        saveload.saveData(saveload.loadData(file1).getUsername(), saveload.loadData(file2).getBricksAmount600(),
            saveload.loadData(file2).getPrice600(), br1000, pr1000, saveload.loadData(file2).getTotalPrice(), 
            saveload.loadData(file1).getPayMet(), saveload.loadData(file1).getAddress(), false, false);
        checkout.setVisible(true);
    }

    @FXML
    private void checkOutButton(ActionEvent event) throws IOException {
        System.out.println("Checkout Button is clicked!");
        AnchorPane checkout = FXMLLoader.load(getClass().getResource("/Shop/shopMyCart.fxml"));
        cntrPane.getChildren().setAll(checkout);
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shopPage.fxml"));
        cntrPane.getChildren().setAll(shop);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Data();
        saveload = new SaveLoad();
        if (saveload.loadData(file2).getPrice1000() != 0) {
            amount.setText(String.valueOf(saveload.loadData(file2).getBricksAmount1000()));
            price.setText("Rp "+String.valueOf(saveload.loadData(file2).getPrice1000()));
            checkout.setVisible(true);
        } else {
            checkout.setVisible(false);
        }
    }

}
