package ro.tuc.tp.Data;

import ro.tuc.tp.BusinessLogic.DeliveryService;
import ro.tuc.tp.BusinessLogic.User;
import java.io.*;

/**
 * Clasa Serializator defineste metodele pentru serializarea si deserializarea datelor la pornirea si oprirea aplicatiei
 * @author Pop Crina-Maria
 */
public class Serializator {
    private static String deliverySer = "delivery.ser";
    private static String userSer = "user.ser";

    public static void deliverySerialization(DeliveryService delivery){
        try {
            FileOutputStream file = new FileOutputStream(deliverySer);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(delivery);
            out.close();
            file.close();
            System.out.printf("Data serialized\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void userSerialization(User users){
        try {
            FileOutputStream file = new FileOutputStream(userSer);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(users);
            out.close();
            file.close();
            System.out.printf("Data serialized\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DeliveryService deliveryDeserialization(){
        DeliveryService ds = new DeliveryService();
        try {
            FileInputStream file = new FileInputStream(deliverySer);
            ObjectInputStream in = new ObjectInputStream(file);

            ds = (DeliveryService) in.readObject();
            System.out.printf("Data deserialized\n");
            in.close();
            file.close();
            return ds;
        } catch (IOException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return ds;
        } catch (ClassNotFoundException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return ds;
        }
    }

    public static User userDeserialization(){
        User u = new User();
        try {
            FileInputStream file = new FileInputStream(userSer);
            ObjectInputStream in = new ObjectInputStream(file);

            u = (User) in.readObject();
            System.out.printf("Data deserialized\n");
            in.close();
            file.close();
            return u;
        } catch (IOException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return u;
        } catch (ClassNotFoundException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return u;
        }
    }
}
