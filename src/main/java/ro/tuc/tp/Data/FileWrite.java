package ro.tuc.tp.Data;

import ro.tuc.tp.BusinessLogic.MenuItem;
import ro.tuc.tp.BusinessLogic.Order;
import java.io.FileWriter;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa FileWriter contine metodele pentru generarea facturii si a tuturor rapoartelor
 * @author Pop Crina-Maria
 */
public class FileWrite implements Serializable {
    private String fileName;

    public void generateBill(Order o, ArrayList<MenuItem> list) {
        FileWriter fileWriter = null;
        File file;
        fileName = "order" + o.getOrderId() + ".txt";

        try {
            file = new File(fileName);
            if(file.createNewFile()) {
                fileWriter = new FileWriter(file);
            } else {
                System.out.println("File aready exist!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            fileWriter.write("Order no. " + o.getOrderId() + "\r\nDate " + o.getOrderDate() + "\r\n" + "\r\n");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        ArrayList<MenuItem> ordered = list;
        for(MenuItem m: ordered) {
            try {
                fileWriter.write(m.toString());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        try {
            fileWriter.write("\r\nTotal: " + o.getPrice() + " lei" + "\r\n");
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void generateReport1(ArrayList<Order> orders) {
        File file = null;
        FileWriter fileWriter = null;
        try {
            file = new File("report1.txt");
            fileWriter = new FileWriter(file);
            fileWriter.write("Report with orders performed between a specified time interval" + "\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(Order o: orders) {
            try {
                fileWriter.write(o.toString());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void generateReport2(ArrayList<MenuItem> products) {
        File file = null;
        FileWriter fileWriter = null;
        try {
            file = new File("report2.txt");
            fileWriter = new FileWriter(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            fileWriter.write("Report with products ordered more than a specified number of times" + "\r\n");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            for(MenuItem m: products) {
                fileWriter.write(m.toString());
            }
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void generateReport3(ArrayList<String> clients) {
        File file = null;
        FileWriter fileWriter = null;
        try {
            file = new File("report3.txt");
            fileWriter = new FileWriter(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(String s: clients) {
            try {
                fileWriter.write("Report with clients that have ordered more than a specific number of times" + "\r\n"
                        + "and the value of order is higher than a specified amount" + "\r\n");
                fileWriter.write(s);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void generateReport4(HashMap<MenuItem,Integer> products) {
        File file = null;
        FileWriter fileWriter = null;
        try {
            file = new File("report4.txt");
            fileWriter = new FileWriter(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            fileWriter.write("Report with the product ordered within a specified day " +
                    "with the number of times they have been ordered" + "\r\n" + "\r\n");
        } catch(Exception e){ System.out.println(e.getMessage()); }

        try {
            for(Map.Entry<MenuItem,Integer> set: products.entrySet()) {
                fileWriter.write(set.getKey().toString() + "Number of times: " + set.getValue() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}