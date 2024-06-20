package Shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShopCompleteController implements Initializable {
    SaveLoad saveload;
    private String file = "ShopCart.xml";
    @FXML
    private AnchorPane cntrPane;

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shopPage.fxml"));
        cntrPane.getChildren().setAll(shop);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
        saveload.saveData(saveload.loadData(file).getUsername(),
                0, 0, 0, 0, 0, "", saveload.loadData(file).getAddress(), 
                false, false);
    }

}
