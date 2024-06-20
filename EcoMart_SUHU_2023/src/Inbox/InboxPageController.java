package Inbox;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import data.Data;
import data.SaveLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class InboxPageController implements Initializable {
    SaveLoad load;
    Data data;
    @FXML
    private AnchorPane ctrlpane;
    @FXML
    private AnchorPane one;
    @FXML
    private AnchorPane two;
    @FXML
    private AnchorPane three;
    @FXML
    private AnchorPane four;
    @FXML
    private AnchorPane five;
    @FXML
    private Label empty;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private Button delete;

    @FXML
    private void deleteInbox(ActionEvent event) {
        load.clearArrayxml("Inbox.xml");
        delete.setVisible(false);
        empty.setVisible(true);
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        four.setVisible(false);
        five.setVisible(false);
        System.out.println("Delete Inbox is clicked!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load = new SaveLoad();
        LinkedList<Data> loadeddata = load.loadArrayData("Inbox.xml");
        switch (loadeddata.size()) {
            case 0:
                empty.setVisible(true);
                delete.setVisible(false);
                one.setVisible(false);
                two.setVisible(false);
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                break;
            case 1:
                empty.setVisible(false);
                one.setVisible(true);
                two.setVisible(false);
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                data = loadeddata.pollLast();
                lb1.setText(data.getInbox());
                break;
            case 2:
                empty.setVisible(false);
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                data = loadeddata.pollLast();
                lb1.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb2.setText(data.getInbox());
                break;
            case 3:
                empty.setVisible(false);
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(false);
                five.setVisible(false);
                data = loadeddata.pollLast();
                lb1.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb2.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb3.setText(data.getInbox());
                break;
            case 4:
                empty.setVisible(false);
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(true);
                five.setVisible(false);
                data = loadeddata.pollLast();
                lb1.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb2.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb3.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb4.setText(data.getInbox());
                break;
            default:
                empty.setVisible(false);
                one.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                four.setVisible(true);
                five.setVisible(true);
                data = loadeddata.pollLast();
                lb1.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb2.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb3.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb4.setText(data.getInbox());
                data = loadeddata.pollLast();
                lb5.setText(data.getInbox());
                break;
        }
    }

}
