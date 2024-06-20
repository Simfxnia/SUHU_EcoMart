package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount600;
    private SimpleStringProperty price600;
    private SimpleIntegerProperty amount1000;
    private SimpleStringProperty price1000;
    private SimpleStringProperty totalPrice;
    private SimpleStringProperty paymet;
    private SimpleStringProperty address;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty status;

    public TableData() {}

    public TableData(String name, int amount600, String price600, int amount1000, String price1000, String totalPrice,
            String paymet, String address) {
        this.name = new SimpleStringProperty(name);
        this.amount600 = new SimpleIntegerProperty(amount600);
        this.price600 = new SimpleStringProperty(price600);
        this.amount1000 = new SimpleIntegerProperty(amount1000);
        this.price1000 = new SimpleStringProperty(price1000);
        this.totalPrice = new SimpleStringProperty(totalPrice);
        this.paymet = new SimpleStringProperty(paymet);
        this.address = new SimpleStringProperty(address);
    }

    public TableData(String name, int amount600, int amount1000, String address, String date, String time) {
        this.name = new SimpleStringProperty(name);
        this.amount600 = new SimpleIntegerProperty(amount600);
        this.amount1000 = new SimpleIntegerProperty(amount1000);
        this.address = new SimpleStringProperty(address);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
    }

    public TableData(String name, String status, int amount600, int amount1000) {
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
        this.amount600 = new SimpleIntegerProperty(amount600);
        this.amount1000 = new SimpleIntegerProperty(amount1000);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public void setAmount600(int amount600) {
        this.amount600.set(amount600);
    }

    public int getAmount600() {
        return amount600.get();
    }

    public void setPrice6000(String price600) {
        this.price600.set(price600);
    }

    public String getPrice600() {
        return price600.get();
    }

    public void setAmount1000(int amount1000) {
        this.amount1000.set(amount1000);
    }

    public int getAmount1000() {
        return amount1000.get();
    }

    public void setPrice1000(String price1000) {
        this.price1000.set(price1000);
    }

    public String getPrice1000() {
        return price1000.get();
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public String getTotalPrice() {
        return totalPrice.get();
    }

    public void setPaymet(String paymet) {
        this.paymet.set(paymet);
    }

    public String getPaymet() {
        return paymet.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAddress() {
        return address.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDate() {
        return date.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getTime() {
        return time.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.get();
    }
}
