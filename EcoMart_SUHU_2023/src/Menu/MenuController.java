package Menu;

import java.net.URL;
import java.util.ResourceBundle;

import Run.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MenuController implements Initializable {
    OpenScene openScene = new OpenScene();
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane deposit;
    @FXML
    private AnchorPane shop;
    @FXML
    private AnchorPane profile;
    @FXML
    private AnchorPane inbox;
    @FXML
    private ImageView homeImage;
    @FXML
    private ImageView depositImage;
    @FXML
    private ImageView shopImage;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView inboxImage;

    @FXML
    private void homeButtonAction(ActionEvent event) {
        System.out.println("Home Button is clicked!");
        Pane page = openScene.getPane("/Home/homePage");
        home.getStyleClass().clear();
        deposit.getStyleClass().clear();
        shop.getStyleClass().clear();
        profile.getStyleClass().clear();
        inbox.getStyleClass().clear();
        home.getStyleClass().add("greenColor");
        deposit.getStyleClass().add("transparantColor");
        shop.getStyleClass().add("transparantColor");
        profile.getStyleClass().add("transparantColor");
        inbox.getStyleClass().add("transparantColor");
        homeImage.setVisible(true);
        depositImage.setVisible(false);
        shopImage.setVisible(false);
        profileImage.setVisible(false);
        inboxImage.setVisible(false);
        mainPane.setCenter(page);
    }

    @FXML
    private void depositButtonAction(ActionEvent event) {
        System.out.println("Deposit Button is clicked!");
        Pane page = openScene.getPane("/Deposit/depositPage");
        home.getStyleClass().clear();
        deposit.getStyleClass().clear();
        shop.getStyleClass().clear();
        profile.getStyleClass().clear();
        inbox.getStyleClass().clear();
        home.getStyleClass().add("transparantColor");
        deposit.getStyleClass().add("greenColor");
        shop.getStyleClass().add("transparantColor");
        profile.getStyleClass().add("transparantColor");
        inbox.getStyleClass().add("transparantColor");
        homeImage.setVisible(false);
        depositImage.setVisible(true);
        shopImage.setVisible(false);
        profileImage.setVisible(false);
        inboxImage.setVisible(false);
        mainPane.setCenter(page);

    }

    @FXML
    private void shopButtonAction(ActionEvent event) {
        System.out.println("Shop Page Button is clicked!");
        Pane page = openScene.getPane("/Shop/shopPage");
        home.getStyleClass().clear();
        deposit.getStyleClass().clear();
        shop.getStyleClass().clear();
        profile.getStyleClass().clear();
        inbox.getStyleClass().clear();
        home.getStyleClass().add("transparantColor");
        deposit.getStyleClass().add("transparantColor");
        shop.getStyleClass().add("greenColor");
        profile.getStyleClass().add("transparantColor");
        inbox.getStyleClass().add("transparantColor");
        homeImage.setVisible(false);
        depositImage.setVisible(false);
        shopImage.setVisible(true);
        profileImage.setVisible(false);
        inboxImage.setVisible(false);
        mainPane.setCenter(page);
    }

    @FXML
    private void profileButtonAction(ActionEvent event) {
        System.out.println("Profile Page Button is clicked!");
        Pane page = openScene.getPane("/UserProfile/profilePage");
        home.getStyleClass().clear();
        deposit.getStyleClass().clear();
        shop.getStyleClass().clear();
        profile.getStyleClass().clear();
        inbox.getStyleClass().clear();
        home.getStyleClass().add("transparantColor");
        deposit.getStyleClass().add("transparantColor");
        shop.getStyleClass().add("transparantColor");
        profile.getStyleClass().add("greenColor");
        inbox.getStyleClass().add("transparantColor");
        homeImage.setVisible(false);
        depositImage.setVisible(false);
        shopImage.setVisible(false);
        profileImage.setVisible(true);
        inboxImage.setVisible(false);
        mainPane.setCenter(page);
    }

    @FXML
    private void inboxButtonAction(ActionEvent event) {
        System.out.println("Inbox Page Button is clicked!");
        Pane page = openScene.getPane("/Inbox/inboxPage");
        home.getStyleClass().clear();
        deposit.getStyleClass().clear();
        shop.getStyleClass().clear();
        profile.getStyleClass().clear();
        inbox.getStyleClass().clear();
        home.getStyleClass().add("transparantColor");
        deposit.getStyleClass().add("transparantColor");
        shop.getStyleClass().add("transparantColor");
        profile.getStyleClass().add("transparantColor");
        inbox.getStyleClass().add("greenColor");
        homeImage.setVisible(false);
        depositImage.setVisible(false);
        shopImage.setVisible(false);
        profileImage.setVisible(false);
        inboxImage.setVisible(true);
        mainPane.setCenter(page);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane page = openScene.getPane("/Home/homePage");
        mainPane.setCenter(page);
        home.getStyleClass().add("greenColor");
        depositImage.setVisible(false);
        shopImage.setVisible(false);
        profileImage.setVisible(false);
        inboxImage.setVisible(false);
    }

}
