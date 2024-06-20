package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class SaveLoad {
    XStream xstream = new XStream(new StaxDriver());
    Data data;
    private static LinkedList<Data> array = new LinkedList<>();

    public void saveData(String name, String email, String password, int points,
            String address, int bricks600, int bricks1000, String debit, String fullNameCreDebit,
            String accountDebitNum, String eWallet,
            String phoneNumEWallet, double totalPrice) {
        data = new Data(name, email, password, points, address, bricks600, bricks1000, debit,
                fullNameCreDebit, accountDebitNum, eWallet, phoneNumEWallet, totalPrice);
        saveData(data, "PersonalData.xml");
    }

    public void saveData(String name, String password) {
        data = new Data(name, password);
        saveData(data, "ManagementData.xml");
    }

    public void saveData(Data daataa, String path) {
        String xml = xstream.toXML(daataa);
        FileOutputStream outputDoc;

        try {
            byte[] byteData = xml.getBytes("UTF-8");
            outputDoc = new FileOutputStream(path);
            outputDoc.write(byteData);
            outputDoc.close();
        } catch (Exception e) {
            System.err.println("An error occour: " + e.getMessage());
        }
    }

    public void saveData(String warehouse, String name, String status, int bricks600, int bricks1000) {
        data = new Data(name, status, bricks600, bricks1000);
        switch(warehouse) {
            case "Sleman EcoMart":
                array = loadArrayData("SlemanDepositTable.xml");
                array.add(data);
                saveData(array, "SlemanDepositTable.xml");  
                break;
            case "Kota Yogyakarta EcoMart":
                array = loadArrayData("JogjaDepositTable.xml");
                array.add(data);
                saveData(array, "JogjaDepositTable.xml"); 
                break;
            case "Bantul EcoMart":
                array = loadArrayData("BantulDepositTable.xml");
                array.add(data);
                saveData(array, "BantulDepositTable.xml"); 
                break;
        }
    }

    public void saveData(String username, int bricks600, int bricks1000, String address, String date, String time,
            boolean list, boolean management) {
        data = new Data(username, bricks600, bricks1000, address, date, time);
        if (list == true && management == true) {
            array = loadArrayData("PickUpTable.xml");
            array.add(data);
            saveData(array, "PickUpTable.xml");
        } else if (list == false && management == false) {
            saveData(data, "PickUpCart.xml");
        } else if (list == false && management == true) {
            saveData(data, "SelectedPickUp.xml");
        }
    }

    public void saveData(String name, int bricks600, double price600, int bricks1000, double price1000,
            double totalPrice, String paymet, String address, boolean list, boolean manage) {
        data = new Data(name, bricks600, price600, bricks1000, price1000, totalPrice, paymet, address);
        if (list == true && manage == false) {
            array = loadArrayData("SavetoCart.xml");
            array.add(data);
            saveData(array, "SavetoCart.xml");
        } else if (list == false && manage == false) {
            saveData(data, "ShopCart.xml");
        } else if (list == true && manage == true) { // for single selected data
            array = loadArrayData("OnGoingDelivery.xml");
            array.add(data);
            saveData(array, "OnGoingDelivery.xml");
        } else if (list == false && manage == true) {
            saveData(data, "SelectedCart.xml");
        }
    }

    public void saveData(int points, String date, String time, String bottle, boolean type) {
        String inbox = "";
        if (type == true) {
            inbox = "Good news! your ordered ecobrick has been scheduled! We will deliver your order: "+bottle+" on "
                    + date + " at " + time + " to your address. You gained "+points+" points!";
        } else if (type == false) {
            inbox = "We are excited to inform you that we have confirmed your ecobrick pick up schedule. We will pick up your ecobricks at your address on "
            +date+" at "+time+". You gained "+points+" points!";
        }
        array = loadArrayData("Inbox.xml");
        data = new Data(inbox);
        array.add(data);
        saveData(array, "Inbox.xml");
        array = loadArrayData("Points.xml");
        data = new Data(points);
        array.add(data);
        saveData(array, "Points.xml");
    }

    public void saveData(LinkedList<Data> array, String path) {
        String xml = xstream.toXML(array);
        FileOutputStream outputDoc;

        try {
            byte[] byteData = xml.getBytes("UTF-8");
            outputDoc = new FileOutputStream(path);
            outputDoc.write(byteData);
            outputDoc.close();
        } catch (Exception e) {
            System.err.println("An error occour: " + e.getMessage());
        }
    }

    public Data loadData(String path) {
        FileInputStream inputDoc;
        Data result = new Data();

        try {
            inputDoc = new FileInputStream(path);
            int content;
            char c;
            String s = "";
            while ((content = inputDoc.read()) != -1) {
                c = (char) content;
                s = s + c;
            }
            result = (Data) xstream.fromXML(s);
            inputDoc.close();
        } catch (Exception e) {
            System.out.println("An error occurs: " + e.getMessage());
        }
        return result;
    }

    public LinkedList<Data> loadArrayData(String path) {
        FileInputStream inputDoc;

        try {
            inputDoc = new FileInputStream(path);
            int content;
            char c;
            String s = "";
            while ((content = inputDoc.read()) != -1) {
                c = (char) content;
                s = s + c;
            }
            array = (LinkedList<Data>) xstream.fromXML(s);
            inputDoc.close();
        } catch (Exception e) {
            System.out.println("An error occurs: " + e.getMessage());
        }
        return array;
    }

    public void deleteData(String name, int amount600, int amount1000, String paymet, String add, String path) {
        int index = -1;
        array = loadArrayData(path);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getUsername().equals(name) && array.get(i).getBricksAmount600() == amount600 &&
                    array.get(i).getBricksAmount1000() == amount1000 && array.get(i).getPayMet().equals(paymet) &&
                    array.get(i).getAddress().equals(add)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            array.remove(index);
            saveData(array, path);
            System.out.println("Data is deleted");
        } else {
            System.out.println("Intendent deleted data not found");
        }
    }

    public void deleteData(String name, int amount600, int amount1000, String add, String path) {
        int index = -1;
        array = loadArrayData(path);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getUsername().equals(name) && array.get(i).getBricksAmount600() == amount600 &&
                    array.get(i).getBricksAmount1000() == amount1000 && array.get(i).getAddress().equals(add)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            array.remove(index);
            saveData(array, path);
            System.out.println("Data is deleted");
        } else {
            System.out.println("Intendent deleted data not found");
        }
    }

    public void editData(String name, int bricks600, double price600, int bricks1000, double price1000,
            double totalPrice, String paymet, String address, int index, String path) {
        array = loadArrayData(path);
        if (index != -1) {
            data = new Data(name, bricks600, price600, bricks1000, price1000, totalPrice, paymet,
                    address);
            array.set(index, data);
            saveData(array, path);
            System.out.println("Data is edited");
        } else {
            System.out.println("Intendent data not found");
        }
    }

    public void clearArrayxml(String path) {
        array = loadArrayData(path);
        array.clear();
        saveData(array, path);
    }
}