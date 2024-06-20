package data;

public class Data {
    private String username;
    private String password;
    private String fullNameCreDebit;
    private String phoneNumEWallet;
    private int amount600;
    private double price600;
    private int amount1000;
    private double price1000;
    private double totalPrice;
    private int points;
    private String address;
    private String email;
    private String card;
    private String eWallet;
    private String accountDebitNum;
    private String paymet;
    private String date;
    private String time;
    private String inbox;
    private String status;

    public Data() {
    }

    public Data(String username, String email, String password, int points, String address,
            int bricks600, int bricks1000, String card, String fullNameCreDebit, String accountDebitNum, String eWallet,
            String phoneNumEWallet,
            double totalPrice) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.points = points;
        this.address = address;
        this.amount600 = bricks600;
        this.amount1000 = bricks1000;
        this.card = card;
        this.fullNameCreDebit = fullNameCreDebit;
        this.accountDebitNum = accountDebitNum;
        this.eWallet = eWallet;
        this.phoneNumEWallet = phoneNumEWallet;
        this.totalPrice = totalPrice;
    }

    public Data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Data(String username, int bricks600, double price600, int bricks1000, double price1000, double totalPrice,
            String paymet, String address) {
        this.username = username;
        this.amount600 = bricks600;
        this.price600 = price600;
        this.amount1000 = bricks1000;
        this.price1000 = price1000;
        this.totalPrice = totalPrice;
        this.paymet = paymet;
        this.address = address;
    }

    public Data(String username, String status, int bricks600, int bricks1000) {
        this.username = username;
        this.status = status;
        this.amount600 = bricks600;
        this.amount1000 = bricks1000;
    }

    public Data(String username, int bricks600, int bricks1000, String address, String date, String time) {
        this.username = username;
        this.amount600 = bricks600;
        this.amount1000 = bricks1000;
        this.address = address;
        this.date = date;
        this.time = time;
    }

    public Data(String inbox) {
        this.inbox = inbox;
    }

    public Data(int points) {
        this.points = points;
    }

    public void setUsername(String username) { // username
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public int getBricksAmount600() { // brick 600
        return this.amount600;
    }

    public void setBricksAmount600(int amount600) {
        this.amount600 = amount600;
    }

    public int addBricksAmount600() {
        this.amount600 += 1;
        return this.amount600;
    }

    public int substractBricksAmount600() {
        if (this.amount600 > 0) {
            this.amount600 -= 1;
        }
        return this.amount600;
    }

    public double getPrice600() { // price 600
        return this.price600;
    }

    public void setPrice600(double price600) {
        this.price600 = price600;
    }

    public double addPrice600() {
        this.price600 += 8000;
        return this.price600;
    }

    public double substractPrice600() {
        if (this.price600 > 0) {
            this.price600 -= 8000;
        }
        return this.price600;
    }

    public int getBricksAmount1000() { // brick 1000
        return this.amount1000;
    }

    public void setBricksAmount1000(int amount1000) {
        this.amount1000 = amount1000;
    }

    public int addBricksAmount1000() {
        this.amount1000 += 1;
        return this.amount1000;
    }

    public int substractBricksAmount1000() {
        if (this.amount1000 > 0) {
            this.amount1000 -= 1;
        }
        return this.amount1000;
    }

    public double getPrice1000() { // price 1000
        return this.price1000;
    }

    public void setPrice1000(double price1000) {
        this.price1000 = price1000;
    }

    public double addPrice1000() {
        this.price1000 += 10000;
        return this.price1000;
    }

    public double substractPrice1000() {
        if (this.price1000 > 0) {
            this.price1000 -= 10000;
        }
        return this.price1000;
    }

    public double getTotalPrice() { // total price
        return this.totalPrice;
    }

    public void setTotalPrice(double price600, double price1000) {
        this.totalPrice = price600 + price1000;
    }

    public void setPayMet(String paymet) { // email
        this.paymet = paymet;
    }

    public String getPayMet() {
        return this.paymet;
    }

    public void setAddress(String road, String city, String province, String postalCode) { // address
        this.address = road + ", " + city + ", " + province + " (" + postalCode + ")";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setEmail(String email) { // email
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) { // password
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCard(String card) { // credit/debit
        this.card = card;
    }

    public String getCard() {
        return this.card;
    }

    public void setEWallet(String eWallet) { // e-Wallet
        this.eWallet = eWallet;
    }

    public String getEWallet() {
        return this.eWallet;
    }

    public void setFullNameCreDebit(String namecredebit) { // fullname credit/debit
        this.fullNameCreDebit = namecredebit;
    }

    public String getFullNameCreDebit() {
        return this.fullNameCreDebit;
    }

    public void setPhoneNumEWallet(String phoneNumEWallet) { // phone e-Wallet
        this.phoneNumEWallet = phoneNumEWallet;
    }

    public String getPhoneNumEWallet() {
        return this.phoneNumEWallet;
    }

    public void setAccountDebitNum(String debitnum) { // credit/debit number e-Wallet
        this.accountDebitNum = debitnum;
    }

    public String getAccountDebitNum() {
        return this.accountDebitNum;
    }

    public void setPoints(int amount600, int amount1000) { // points
        this.points = (amount600 * 200) + (amount1000 * 350);
    }

    public int getPoints() {
        return this.points;
    }

    public void setDate(String day, String month, String year) { // date
        this.date = day + " " + month + " " + year;
    }

    public String getDate() {
        return this.date;
    }

    public void setTime(String time) { // time
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public String getInbox() { // inbox
        return this.inbox;
    }

    public void setStatus(String status) { // status
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
