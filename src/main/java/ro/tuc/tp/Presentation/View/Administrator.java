package ro.tuc.tp.Presentation.View;

import ro.tuc.tp.BusinessLogic.DeliveryService;
import ro.tuc.tp.BusinessLogic.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa Administrator defineste fereastra GUI pentru administrator
 * @author Pop Crina-Maria
 */
public class Administrator extends JFrame {
    JLabel nameL = new JLabel("Name:");
    JLabel ratingL = new JLabel("Rating:");
    JLabel caloriesL = new JLabel("Calories:");
    JLabel proteinsL = new JLabel("Proteins:");
    JLabel fatsL = new JLabel("Fats:");
    JLabel sodiumL = new JLabel("Sodium:");
    JLabel priceL = new JLabel("Price:");
    JLabel manage = new JLabel("Manage products section");
    JLabel generate = new JLabel("Generate reports section");
    JTextField nameT = new JTextField();
    JTextField ratingT = new JTextField();
    JTextField caloriesT = new JTextField();
    JTextField protiensT = new JTextField();
    JTextField fatsT = new JTextField();
    JTextField sodiumT = new JTextField();
    JTextField priceT = new JTextField();
    JTextPane textArea = new JTextPane();
    JLabel menuL = new JLabel("Title:");
    JLabel startL = new JLabel("Insert start hour:");
    JLabel endL = new JLabel("Insert end hour:");
    JLabel nbTimesL = new JLabel("Number of times:");
    JLabel amountL = new JLabel("Insert amount:");
    JLabel dayL = new JLabel("Specify day:");
    JTextField startT = new JTextField();
    JTextField endT = new JTextField();
    JTextField nbTimesT = new JTextField();
    JTextField amountT = new JTextField();
    JTextField dayT = new JTextField();
    JTextField menuName = new JTextField();
    JButton report1 = new JButton("Report1");
    JButton report2 = new JButton("Report2");
    JButton report3 = new JButton("Report3");
    JButton report4 = new JButton("Report4");
    JButton importB = new JButton("Import");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton create = new JButton("Create");
    JButton validate = new JButton("OK");

    public Administrator() {
        //labels
        manage.setBounds(10,10,300,25);
        manage.setFont(new Font("Serif",Font.BOLD,20));
        nameL.setBounds(50,50,75,25);
        ratingL.setBounds(50,75,75,25);
        caloriesL.setBounds(50,100,75,25);
        proteinsL.setBounds(50,125,75,25);
        fatsL.setBounds(50,150,75,25);
        sodiumL.setBounds(50,175,75,25);
        priceL.setBounds(50,200,75,25);
        generate.setBounds(10,360,300,25);
        generate.setFont(new Font("Serif",Font.BOLD,20));
        menuL.setBounds(30,320,75,25);
        startL.setBounds(50,400,200,25);
        endL.setBounds(50,425,200,25);
        nbTimesL.setBounds(50,450,200,25);
        amountL.setBounds(50,475,200,25);
        dayL.setBounds(50,500,200,25);

        //textArea
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(300,50,350,300);
        scroll.setViewportView(textArea);
        getContentPane().add(scroll);
        //textFields
        nameT.setBounds(125,50,100,20);
        ratingT.setBounds(125,75,50,20);
        caloriesT.setBounds(125,100,50,20);
        protiensT.setBounds(125,125,50,20);
        fatsT.setBounds(125,150,50,20);
        sodiumT.setBounds(125,175,50,20);
        priceT.setBounds(125,200,50,20);
        menuName.setBounds(75,320,100,25);
        startT.setBounds(150,400,50,20);
        endT.setBounds(150,425,50,20);
        nbTimesT.setBounds(150,450,50,20);
        amountT.setBounds(150,475,50,20);
        dayT.setBounds(150,500,100,20);
        //buttons
        importB.setBounds(30,240,75,25);
        add.setBounds(105,240,75,25);
        delete.setBounds(30,265,75,25);
        update.setBounds(105,265,75,25);
        create.setBounds(30,290,75,25);
        validate.setBounds(105,290,75,25);
        report1.setBounds(50,525,100,25);
        report2.setBounds(150,525,100,25);
        report3.setBounds(250,525,100,25);
        report4.setBounds(350,525,100,25);

        this.add(manage);
        this.add(generate);
        this.add(nameL);
        this.add(ratingL);
        this.add(caloriesL);
        this.add(proteinsL);
        this.add(fatsL);
        this.add(sodiumL);
        this.add(priceL);
        this.add(startL);
        this.add(endL);
        this.add(nbTimesL);
        this.add(amountL);
        this.add(dayL);
        this.add(menuL);

        this.add(nameT);
        this.add(ratingT);
        this.add(caloriesT);
        this.add(protiensT);
        this.add(fatsT);
        this.add(sodiumT);
        this.add(priceT);
        this.add(startT);
        this.add(endT);
        this.add(nbTimesT);
        this.add(amountT);
        this.add(dayT);
        this.add(menuName);

        this.add(importB);
        this.add(add);
        this.add(delete);
        this.add(update);
        this.add(create);
        this.add(validate);
        this.add(report1);
        this.add(report2);
        this.add(report3);
        this.add(report4);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,600);
        this.setLayout(null);
        this.setTitle("Administrator");
        this.setVisible(false);
    }
    public String getName() {
        return nameT.getText();
    }
    public double getRating() {
        return Double.parseDouble(ratingT.getText());
    }
    public int getCalories() {
        return Integer.parseInt(caloriesT.getText());
    }
    public int getProtiens(){
        return Integer.parseInt(protiensT.getText());
    }
    public int getFats(){
        return Integer.parseInt(fatsT.getText());
    }
    public int getSodium(){
        return Integer.parseInt(sodiumT.getText());
    }
    public int getPrice(){
        return Integer.parseInt(priceT.getText());
    }
    public String getMenuName() {
        return menuName.getText();
    }
    public int getStartHour() {
        return Integer.parseInt(startT.getText());
    }
    public int getEndHour() {
        return Integer.parseInt(endT.getText());
    }
    public int getNbOfTimes() {
        return Integer.parseInt(nbTimesT.getText());
    }
    public int getAmount() {
        return Integer.parseInt(amountT.getText());
    }
    public String getDay() {
        return dayT.getText();
    }
    public void setTextArea(String s) {
        textArea.setText(s);
    }
    public void ImportListener(ActionListener a) {
        importB.addActionListener(a);
    }
    public void AddListener(ActionListener a) {
        add.addActionListener(a);
    }
    public void DeleteListener(ActionListener a) {
        delete.addActionListener(a);
    }
    public void UpdateListener(ActionListener a) {
        update.addActionListener(a);
    }
    public void CreateProductListener(ActionListener a) {
        create.addActionListener(a);
    }
    public void OkAdminListener(ActionListener a) { validate.addActionListener(a); }
    public void Report1Listener(ActionListener a) {
        report1.addActionListener(a);
    }
    public void Report2Listener(ActionListener a) {
        report2.addActionListener(a);
    }
    public void Report3Listener(ActionListener a) {
        report3.addActionListener(a);
    }
    public void Report4Listener(ActionListener a) {
        report4.addActionListener(a);
    }
}
