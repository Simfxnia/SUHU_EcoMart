package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableList {
    private ObservableList<TableData> datalist;

    public TableList() {
        datalist = FXCollections.observableArrayList();
    }

    public ObservableList<TableData> getData() {
        return this.datalist;
    }

    public void setData(String nama, int bricks600, String pri600, int bricks1000, String pri1000, String tot,
            String pm, String addr) {
        datalist.add(new TableData(nama, bricks600, pri600, bricks1000, pri1000, tot, pm, addr));
    }

    public void setData(String nama, int bricks600, String pri600, int bricks1000, String pri1000, String tot,
            String pm, String addr, int index) {
        TableData data = new TableData(nama, bricks600, pri600, bricks1000, pri1000, tot, pm, addr);
        int num = Math.abs(datalist.size()-1-index);
        datalist.set(num, data);
    }

    public void setData(String nama, int bricks600, int bricks1000, String addr, String date, String time) {
        datalist.add(new TableData(nama, bricks600, bricks1000, addr, date, time));
    }

    public void setData(String nama, String status, int bricks600, int bricks1000) {
        datalist.add(new TableData(nama, status, bricks600, bricks1000));
    }
}
