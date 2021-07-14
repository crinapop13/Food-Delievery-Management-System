package ro.tuc.tp.Presentation.View;

import ro.tuc.tp.BusinessLogic.DeliveryService;
import ro.tuc.tp.BusinessLogic.MenuItem;
import ro.tuc.tp.BusinessLogic.Order;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Clasa Employee defineste fereastra GUI pentru angajati
 * @author Pop Crina-Maria
 */
public class Employee extends JFrame implements Observer {
    JLabel titlu = new JLabel("Orders");
    JLabel existOrder = new JLabel("There are no orders placed");
    JTextArea ordersPlaced = new JTextArea();

    public Employee() {
        titlu.setFont(new Font("Serif", Font.BOLD,30));
        titlu.setBounds(150,0,250,35);
        existOrder.setFont(new Font("Serif", Font.BOLD,20));
        existOrder.setBounds(0,50,300,25);
        ordersPlaced.setBounds(0,100,400,500);
        ordersPlaced.setEditable(false);

        this.add(titlu);
        this.add(existOrder);
        this.add(ordersPlaced);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,600);
        this.setLayout(null);
        this.setBackground(new Color(255,160,122));
        this.setTitle("Employee");
        this.setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        existOrder.setText("New order placed!");
        existOrder.setFont(new Font("Serif", Font.BOLD,20));
        DeliveryService delivery = (DeliveryService) o;
        Order order = (Order) arg;
        List<MenuItem> products = delivery.getOrderInformation().get(order);
        String text = "Order:" + "\n";
        text += order.toString();
        text += "\nOrdered products: " + "\n";
        for(MenuItem m : products) {
            text = text + m.getTitle() + "   " + m.computePrice() + "\n";
        }
        text += "Total: " + order.getPrice() + " lei";
        ordersPlaced.setText(text);
    }
}
