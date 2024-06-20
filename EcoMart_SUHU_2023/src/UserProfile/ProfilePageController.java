package UserProfile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProfilePageController implements Initializable {
    SaveLoad saveload;
    private String file = "PersonalData.xml";
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Label name;
    @FXML
    private Label mainAddress;
    @FXML
    private Label email;
    @FXML
    private Label card;
    @FXML
    private Label wallet;
    @FXML
    private Label cardinfo;
    @FXML
    private Label walletinfo;
    @FXML
    private Label addcard;
    @FXML
    private Label addwallet;
    @FXML
    private AnchorPane deletecard;
    @FXML
    private AnchorPane deletewallet;
    @FXML
    private AnchorPane cardpane;
    @FXML
    private AnchorPane walletpane;
    @FXML
    private ImageView profileImage;

    @FXML
    private void editProfAction(ActionEvent event) throws IOException {
        System.out.println("Edit Profile Button is clicked!");
        AnchorPane profile = FXMLLoader.load(getClass().getResource("/UserProfile/accountEditProfile.fxml"));
        cntrPane.getChildren().setAll(profile);
    }

    @FXML
    private void editAddressAction(ActionEvent event) throws IOException {
        System.out.println("Edit Main Address Button is clicked!");
        AnchorPane address = FXMLLoader.load(getClass().getResource("/UserProfile/accountAddress.fxml"));
        cntrPane.getChildren().setAll(address);
    }

    @FXML
    private void editEmailAction(ActionEvent event) throws IOException {
        System.out.println("Edit Email Button is clicked!");
        AnchorPane email = FXMLLoader.load(getClass().getResource("/UserProfile/accountEmail.fxml"));
        cntrPane.getChildren().setAll(email);
    }

    @FXML
    private void connectCardAction(ActionEvent event) throws IOException {
        System.out.println("Connect Card Button is clicked!");
        AnchorPane paymet = FXMLLoader.load(getClass().getResource("/UserProfile/accountCard.fxml"));
        cntrPane.getChildren().setAll(paymet);
    }

    @FXML
    private void connecteWalletAction(ActionEvent event) throws IOException {
        System.out.println("Connect E-Wallet Button is clicked!");
        AnchorPane paymet = FXMLLoader.load(getClass().getResource("/UserProfile/accountEWallet.fxml"));
        cntrPane.getChildren().setAll(paymet);
    }

    @FXML
    private void deleteCardButton(ActionEvent event) {
        System.out.println("Delete Card Button is clicked!");
        card.setText("No Debit/Credit card connected");
        cardinfo.setText("");
        cardinfo.getStyleClass().clear();
        addcard.setVisible(true);
        deletecard.setVisible(false);
        cardpane.getStyleClass().clear();
        cardpane.getStyleClass().add("greybuttonColor");
        card.getStyleClass().clear();
        saveload.saveData(saveload.loadData(file).getUsername(), saveload.loadData(file).getEmail(),
                saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                saveload.loadData(file).getAddress(), saveload.loadData(file).getBricksAmount600(),
                saveload.loadData(file).getBricksAmount1000(), "",
                "", "",
                saveload.loadData(file).getEWallet(), saveload.loadData(file).getPhoneNumEWallet(), 0);
    }

    @FXML
    private void deleteWalletButton(ActionEvent event) {
        System.out.println("Delete E-Wallet Button is clicked!");
        wallet.setText("No e-Wallet connected");
        walletinfo.setText("");
        walletinfo.getStyleClass().clear();
        addwallet.setVisible(true);
        deletewallet.setVisible(false);
        walletpane.getStyleClass().clear();
        walletpane.getStyleClass().add("greybuttonColor");
        wallet.getStyleClass().clear();
        saveload.saveData(saveload.loadData(file).getUsername(), saveload.loadData(file).getEmail(),
                saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                saveload.loadData(file).getAddress(), saveload.loadData(file).getBricksAmount600(),
                saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(),
                saveload.loadData(file).getFullNameCreDebit(), saveload.loadData(file).getAccountDebitNum(),
                "", "", 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveload = new SaveLoad();
        name.setText(saveload.loadData(file).getUsername());
        mainAddress.setText(saveload.loadData(file).getAddress());
        email.setText(saveload.loadData(file).getEmail());
        deletecard.setVisible(false);
        deletewallet.setVisible(false);
        String credebit = saveload.loadData(file).getCard();
        String ewallet = saveload.loadData(file).getEWallet();
        if (!credebit.equals("") && !credebit.equals(null)) {
            cardpane.getStyleClass().clear();
            cardpane.getStyleClass().add("tealColor");
            addcard.setVisible(false);
            String numcard = saveload.loadData(file).getAccountDebitNum();
            card.setText(credebit);
            card.getStyleClass().add("textWhite");
            StringBuilder builder = new StringBuilder(numcard);
            for (int i = 1; i < numcard.length() - 1; i++) {
                builder.setCharAt(i, '*');
            }
            String done = builder.toString();
            cardinfo.setText(saveload.loadData(file).getFullNameCreDebit() + " " + done);
            cardinfo.getStyleClass().add("textWhite");
            deletecard.setVisible(true);
        }
        if (!ewallet.equals(null) && !ewallet.equals("")) {
            walletpane.getStyleClass().clear();
            walletpane.getStyleClass().add("tealColor");
            addwallet.setVisible(false);
            wallet.setText(ewallet);
            wallet.getStyleClass().add("textWhite");
            walletinfo.setText(saveload.loadData(file).getPhoneNumEWallet());
            walletinfo.getStyleClass().add("textWhite");
            deletewallet.setVisible(true);
        }
        saveload.saveData(saveload.loadData(file).getUsername(), saveload.loadData(file).getEmail(),
                saveload.loadData(file).getPassword(), saveload.loadData(file).getPoints(),
                saveload.loadData(file).getAddress(), saveload.loadData(file).getBricksAmount600(),
                saveload.loadData(file).getBricksAmount1000(), saveload.loadData(file).getCard(),
                saveload.loadData(file).getFullNameCreDebit(), saveload.loadData(file).getAccountDebitNum(),
                saveload.loadData(file).getEWallet(), saveload.loadData(file).getPhoneNumEWallet(), 0);
    }

}
