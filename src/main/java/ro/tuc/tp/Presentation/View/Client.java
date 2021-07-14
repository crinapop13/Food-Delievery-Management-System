package ro.tuc.tp.Presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa Client defineste fereastra GUI pentru clienti
 * @author Pop Crina-Maria
 */
public class Client extends JFrame{
    JTextArea textArea = new JTextArea();
    JLabel search = new JLabel("Search section");
    JLabel order = new JLabel("Create order section");
    JLabel product = new JLabel("Product name:");
    JTextArea info = new JTextArea();
    JTextField nameT = new JTextField();
    JTextField ratingT = new JTextField();
    JTextField caloriesT = new JTextField();
    JTextField proteinsT = new JTextField();
    JTextField fatsT = new JTextField();
    JTextField sodiumT = new JTextField();
    JTextField priceT = new JTextField();
    JTextField nameProduct = new JTextField();
    JButton searchByName = new JButton("SearchByName");
    JButton searchByRating = new JButton("SearchByRating");
    JButton searchByCalories = new JButton("SearchByCalories");
    JButton searchByProteins = new JButton("SearchByProteins");
    JButton searchByFats = new JButton("SearchByFats");
    JButton searchBySodium = new JButton("SearchBySodium");
    JButton searchByPrice = new JButton("SearchByPrice");
    JButton seeMenu = new JButton("Menu");
    JButton createOrder = new JButton("Create order");
    JButton ok = new JButton("OK");

    public Client() {
        search.setBounds(10,10,300,25);
        search.setFont(new Font("Serif",Font.BOLD,20));
        order.setBounds(10,375,300,25);
        order.setFont(new Font("Serif",Font.BOLD,20));
        product.setBounds(50,425,150,25);
        info.setBounds(20,475,620,60);
        info.setFont(new Font("Serif",Font.ITALIC,20));
        info.setText("Introduceti numele produsului dorit, apasati butonul 'OK', iar la \n" +
                "final cand ati ales tot ce doreati, plasati comanda apasand 'Create order'");
        info.setBackground(new Color(200,190,255));
        info.setEditable(false);
        //textFields
        nameT.setBounds(50,50,75,25);
        ratingT.setBounds(50,75,75,25);
        caloriesT.setBounds(50,100,75,25);
        proteinsT.setBounds(50,125,75,25);
        fatsT.setBounds(50,150,75,25);
        sodiumT.setBounds(50,175,75,25);
        priceT.setBounds(50,200,75,25);
        nameProduct.setBounds(150,425,150,25);
        //buttons
        searchByName.setBounds(125,50,150,25);
        searchByRating.setBounds(125,75,150,25);
        searchByCalories.setBounds(125,100,150,25);
        searchByProteins.setBounds(125,125,150,25);
        searchByFats.setBounds(125,150,150,25);
        searchBySodium.setBounds(125,175,150,25);
        searchByPrice.setBounds(125,200,150,25);
        seeMenu.setBounds(550,350,100,25);
        ok.setBounds(315,425,75,25);
        createOrder.setBounds(400,425,150,25);
        //textArea
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(300,50,350,300);
        scroll.setViewportView(textArea);
        getContentPane().add(scroll);

        this.add(nameT);
        this.add(ratingT);
        this.add(caloriesT);
        this.add(proteinsT);
        this.add(fatsT);
        this.add(sodiumT);
        this.add(priceT);
        this.add(searchByName);
        this.add(searchByRating);
        this.add(searchByCalories);
        this.add(searchByProteins);
        this.add(searchByFats);
        this.add(searchBySodium);
        this.add(searchByPrice);
        this.add(search);
        this.add(order);
        this.add(product);
        this.add(nameProduct);
        this.add(ok);
        this.add(createOrder);
        this.add(seeMenu);
        this.add(info);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,600);
        this.setLayout(null);
        this.setTitle("Client");
        this.setVisible(false);
    }
    public String getSearchName() {
        return nameT.getText();
    }
    public double getSearchRating(){
        return Double.parseDouble(ratingT.getText());
    }
    public int getSearchCalories() {
        return Integer.parseInt(caloriesT.getText());
    }
    public int getSearchProtiens(){
        return Integer.parseInt(proteinsT.getText());
    }
    public int getSearchFats(){
        return Integer.parseInt(fatsT.getText());
    }
    public int getSearchSodium(){
        return Integer.parseInt(sodiumT.getText());
    }
    public int getSearchPrice(){
        return Integer.parseInt(priceT.getText());
    }
    public String getProductName() {
        return nameProduct.getText();
    }
    public void setTextArea(String t) {
        textArea.setText(t);
    }
    public void SearchNameListener(ActionListener a) {
        searchByName.addActionListener(a);
    }
    public void SearchRatingListener(ActionListener a) {
        searchByRating.addActionListener(a);
    }
    public void SearchCaloriesListener(ActionListener a) {
        searchByCalories.addActionListener(a);
    }
    public void SearchProteinsListener(ActionListener a) {
        searchByProteins.addActionListener(a);
    }
    public void SearchFatsListener(ActionListener a) {
        searchByFats.addActionListener(a);
    }
    public void SearchSodiumListener(ActionListener a) {
        searchBySodium.addActionListener(a);
    }
    public void SearchPriceListener(ActionListener a) {
        searchByPrice.addActionListener(a);
    }
    public void SeeMenuListener(ActionListener a) {
        seeMenu.addActionListener(a);
    }
    public void CreateOrderListener(ActionListener a) {
        createOrder.addActionListener(a);
    }
    public void OkClientListener(ActionListener a) { ok.addActionListener(a); }
}
