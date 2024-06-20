package Home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class HomeController implements Initializable {
    SaveLoad load;
    XYChart.Series chartData = new XYChart.Series<>();
    int num = 0;
    private String file1 = "PersonalData.xml";
    private String file2 = "Points.xml";
    @FXML
    private BarChart chart;
    @FXML
    private Label welcome;
    @FXML
    private AnchorPane cntrPane;
    @FXML
    private Pane pain;
    @FXML
    private Button redeem;
    @FXML
    private Label warning;
    @FXML
    private Label empty;
    @FXML
    private Label points;
    @FXML
    private Label money;
    @FXML
    private Button done;
    @FXML
    private MediaView mediaView;
    @FXML
    private ChoiceBox<String> paymet = new ChoiceBox<>();

    @FXML
    private void goBackButton(ActionEvent event) throws IOException {
        System.out.println("Back Button is clicked!");
        AnchorPane home = FXMLLoader.load(getClass().getResource("/Home/homePage.fxml"));
        cntrPane.getChildren().setAll(home);
    }

    @FXML
    private void redeemButton(ActionEvent event) throws IOException {
        if (paymet.getValue() != null && paymet.getValue() != "") {
            empty.setText("");
            points.setText("0");
            money.setText("Rp 0");
            load.saveData(load.loadData(file1).getUsername(),
                    load.loadData(file1).getEmail(), load.loadData(file1).getPassword(), 0,
                    load.loadData(file1).getAddress(), load.loadData(file1).getBricksAmount600(),
                    load.loadData(file1).getBricksAmount1000(), load.loadData(file1).getCard(),
                    load.loadData(file1).getFullNameCreDebit(), load.loadData(file1).getAccountDebitNum(),
                    load.loadData(file1).getEWallet(), load.loadData(file1).getPhoneNumEWallet(), 0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homePointPopup.fxml"));
            Parent newroot = loader.load();
            pain = new Pane();
            pain.setStyle("-fx-background-color: #ffffff00; -fx-background-radius: 15;");
            pain.getChildren().add(newroot);
            pain.setPrefSize(600, 400);
            pain.setLayoutX(250);
            pain.setLayoutY(160);
            done = new Button();
            done.setText("Done");
            done.setStyle(
                    "-fx-text-fill: #049372; -fx-background-color: #ffffff; -fx-background-radius: 15; -fx-font-weight: bold; -fx-font-size: 18px;");
            done.setPrefSize(175, 55);
            done.setLayoutX(312);
            done.setLayoutY(384);
            pain.getChildren().add(done);
            cntrPane.getChildren().add(pain);
            done.setOnAction(event1 -> {
                try {
                    doneAction(event1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Redeem Point Button is clicked!");
        } else {
            empty.setText("Please choose your e-Wallet! You can set them in your profile page.");
        }
    }

    private void doneAction(ActionEvent event) throws IOException {
        System.out.println("Home Button is clicked!");
        cntrPane.getChildren().remove(pain);
        warning.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load = new SaveLoad();
        welcome.setText("Welcome, " + load.loadData(file1).getUsername() + "!");
        warning.setVisible(false);
        LinkedList<Data> loadedArray = load.loadArrayData(file2);
        for (int i = 0; i < loadedArray.size(); i++) {
            Data dat = loadedArray.get(i);
            num += dat.getPoints();
        }
        num += load.loadData(file1).getPoints();
        load.saveData(load.loadData(file1).getUsername(),
                load.loadData(file1).getEmail(), load.loadData(file1).getPassword(), num,
                load.loadData(file1).getAddress(), load.loadData(file1).getBricksAmount600(),
                load.loadData(file1).getBricksAmount1000(), load.loadData(file1).getCard(),
                load.loadData(file1).getFullNameCreDebit(), load.loadData(file1).getAccountDebitNum(),
                load.loadData(file1).getEWallet(), load.loadData(file1).getPhoneNumEWallet(), 0);
        String poin = String.valueOf(num);
        points.setText(poin);
        money.setText("Rp " + poin);
        load.clearArrayxml(file2);
        int b6 = load.loadData(file1).getBricksAmount600();
        int b10 = load.loadData(file1).getBricksAmount1000();
        chartData.getData().add(new XYChart.Data(b6+" pcs 600ml bottle", b6));
        chartData.getData().add(new XYChart.Data(b10+" pcs 1000ml bottle", b10));
        chart.getData().add(chartData);
        paymet.getItems().addAll(load.loadData(file1).getCard(), load.loadData(file1).getEWallet());
        if (num < 1000) {
            warning.setVisible(true);
            redeem.setVisible(false);
        }
        File videoPath = new File("src/Video/EcoBricks_education.mp4");
        Media media = new Media(videoPath.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

}
