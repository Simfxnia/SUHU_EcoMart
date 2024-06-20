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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ShopMyCartController implements Initializable {
    SaveLoad saveload;
    Data data;
    private String file1 = "PersonalData.xml";
    private String file2 = "ShopCart.xml";
    
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Label amount600;
    @FXML
    private Label amount1000;
    @FXML
    private Label pri600;
    @FXML
    private Label pri1000;
    @FXML
    private Label total;
    @FXML
    private Label address;
    @FXML
    private Label warning;
    @FXML
    private Button profadd;
    @FXML
    private ImageView arrow;
    @FXML
    private ImageView del60;
    @FXML
    private ImageView del100;
    @FXML
    private ChoiceBox<String> paymet = new ChoiceBox<>();

    @FXML
    private void checkOutButton(ActionEvent event) throws IOException {
        if (data.getTotalPrice() == 0) {
            warning.setVisible(true);
            warning.setText("Your cart is empty!");
        } else if (paymet.getValue() == null || paymet.getValue() == "") {
            warning.setVisible(true);
            warning.setText("Please enter your payment method!");
        } else if (address.getText() == "") {
            warning.setVisible(true);
            profadd.setVisible(true);
            arrow.setVisible(true);
            saveload.saveData(saveload.loadData(file1).getUsername(),
                    saveload.loadData(file1).getEmail(), saveload.loadData(file1).getPassword(),
                    saveload.loadData(file1).getPoints(), saveload.loadData(file1).getAddress(), 
                    saveload.loadData(file1).getBricksAmount600(), saveload.loadData(file1).getBricksAmount1000(),
                    saveload.loadData(file1).getCard(), saveload.loadData(file1).getFullNameCreDebit(), 
                    saveload.loadData(file1).getAccountDebitNum(), saveload.loadData(file1).getEWallet(), 
                    saveload.loadData(file1).getPhoneNumEWallet(), data.getTotalPrice());
            warning.setText("Please fill in your address first!");
        } else {
            warning.setVisible(false);
            saveload.saveData(saveload.loadData(file2).getUsername(),
                    saveload.loadData(file2).getBricksAmount600(),
                    saveload.loadData(file2).getPrice600(), saveload.loadData(file2).getBricksAmount1000(),
                    saveload.loadData(file2).getPrice1000(), data.getTotalPrice(), paymet.getValue(),
                    address.getText(), true, false);
            AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shoppingCartTable.fxml"));
            cntrPane.getChildren().setAll(shop);
            System.out.println("continue Button is clicked!");
        }
    }

    @FXML
    public void delete600(ActionEvent event) {
        data.setTotalPrice(0, saveload.loadData(file2).getPrice1000());
        saveload.saveData(saveload.loadData(file2).getUsername(),
                0, 0, saveload.loadData(file2).getBricksAmount1000(),
                saveload.loadData(file2).getPrice1000(), data.getTotalPrice(), paymet.getValue(),
                saveload.loadData(file2).getAddress(), false, false);
        amount600.setText("0");
        pri600.setText("Rp 0.0");
        total.setText("Rp " + String.valueOf(data.getTotalPrice()));
        del60.setVisible(false);
        System.out.println("Delete Button is clicked!");
    }

    @FXML
    public void delete1000(ActionEvent event) {
        data.setTotalPrice(saveload.loadData(file2).getPrice600(), 0);
        saveload.saveData(saveload.loadData(file2).getUsername(),
                saveload.loadData(file2).getBricksAmount600(), saveload.loadData(file2).getPrice600(),
                0, 0, data.getTotalPrice(), paymet.getValue(),
                saveload.loadData(file2).getAddress(), false, false);
        amount1000.setText("0");
        pri1000.setText("Rp 0.0");
        total.setText("Rp " + String.valueOf(data.getTotalPrice()));
        del100.setVisible(false);
        System.out.println("Delete Button is clicked!");
    }

    @FXML
    private void editAddressAction(ActionEvent event) throws IOException {
        System.out.println("Edit Main Address Button is clicked!");
        AnchorPane address = FXMLLoader.load(getClass().getResource("/UserProfile/accountAddress.fxml"));
        cntrPane.getChildren().setAll(address);
    }

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane shop = FXMLLoader.load(getClass().getResource("/Shop/shopPage.fxml"));
        cntrPane.getChildren().setAll(shop);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profadd.setVisible(false);
        arrow.setVisible(false);
        saveload = new SaveLoad();
        data = new Data();
        if (saveload.loadData(file2).getBricksAmount600() == 0) {
            del60.setVisible(false);
        }
        if (saveload.loadData(file2).getBricksAmount1000() == 0) {
            del100.setVisible(false);
        }
        
        paymet.getItems().addAll("Cash", saveload.loadData(file1).getCard(), saveload.loadData(file1).getEWallet());
        amount600.setText(String.valueOf(saveload.loadData(file2).getBricksAmount600()));
        amount1000.setText(String.valueOf(saveload.loadData(file2).getBricksAmount1000()));
        pri600.setText("Rp " + String.valueOf(saveload.loadData(file2).getPrice600()));
        pri1000.setText("Rp " + String.valueOf(saveload.loadData(file2).getPrice1000()));
        data.setTotalPrice(saveload.loadData(file2).getPrice600(), saveload.loadData(file2).getPrice1000());
        total.setText("Rp " + data.getTotalPrice());
        address.setText(saveload.loadData(file1).getAddress());
    }
}
