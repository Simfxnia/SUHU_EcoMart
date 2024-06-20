package Shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShopPageController implements Initializable {
    @FXML
    private AnchorPane cntrPane;

    @FXML
    private void sixmlButton(ActionEvent event) throws IOException {
        System.out.println("600 ml bottle Button is clicked!");
        AnchorPane sixml = FXMLLoader.load(getClass().getResource("/Shop/shop600ml.fxml"));
        cntrPane.getChildren().setAll(sixml);
    }

    @FXML
    private void oneLiterButton(ActionEvent event) throws IOException {
        System.out.println("1000 ml bottle Button is clicked!");
        AnchorPane oneliter = FXMLLoader.load(getClass().getResource("/Shop/shop1000ml.fxml"));
        cntrPane.getChildren().setAll(oneliter);
    }

    @FXML
    private void myCartButton(ActionEvent event) throws IOException {
        System.out.println("My Cart Button is clicked!");
        AnchorPane mycart = FXMLLoader.load(getClass().getResource("/Shop/shoppingCartTable.fxml"));
        cntrPane.getChildren().setAll(mycart);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
