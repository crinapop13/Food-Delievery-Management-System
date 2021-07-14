package ro.tuc.tp.Presentation;

import ro.tuc.tp.BusinessLogic.*;
import ro.tuc.tp.BusinessLogic.MenuItem;
import ro.tuc.tp.Data.Serializator;
import ro.tuc.tp.Presentation.View.Administrator;
import ro.tuc.tp.Presentation.View.Client;
import ro.tuc.tp.Presentation.View.Employee;
import ro.tuc.tp.Presentation.View.LogIn;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clasa Controller trateaza toate evenimentele din interfata definind ascultatori pentru fiecare buton
 * @author Pop Crina-Maria
 */
public class Controller {
    private User user;
    private DeliveryService delivery;
    private LogIn loginFrame;
    private Administrator adminFrame;
    private Client clientFrame;
    private Employee employeeFrame;
    private ArrayList<MenuItem> list;
    private ArrayList<MenuItem> order;
    private String clientName;

    public Controller() {
        this.user = Serializator.userDeserialization();
        this.delivery = Serializator.deliveryDeserialization();
        //this.user = new User();
        //this.delivery = new DeliveryService();
        list = new ArrayList<>();
        order = new ArrayList<>();
        delivery = new DeliveryService();
        loginFrame = new LogIn(user);
        adminFrame = new Administrator();
        clientFrame = new Client();
        employeeFrame = new Employee();
        this.loginFrame.LogInListener(new LogInListener());
        this.loginFrame.SignUpListener(new SignUpListener());
        this.adminFrame.ImportListener(new ImportListener());
        this.adminFrame.AddListener(new AddListener());
        this.adminFrame.DeleteListener(new DeleteListener());
        this.adminFrame.UpdateListener(new UpdateListener());
        this.adminFrame.CreateProductListener(new CreateProductListener());
        this.adminFrame.OkAdminListener(new OkAdminListener());
        this.adminFrame.Report1Listener(new Report1Listener());
        this.adminFrame.Report2Listener(new Report2Listener());
        this.adminFrame.Report3Listener(new Report3Listener());
        this.adminFrame.Report4Listener(new Report4Listener());
        this.clientFrame.SearchNameListener(new SearchNameListener());
        this.clientFrame.SearchRatingListener(new SearchRatingListener());
        this.clientFrame.SearchCaloriesListener(new SearchCaloriesListener());
        this.clientFrame.SearchProteinsListener(new SearchProteinsListener());
        this.clientFrame.SearchFatsListener(new SearchFatsListener());
        this.clientFrame.SearchSodiumListener(new SearchSodiumListener());
        this.clientFrame.SearchPriceListener(new SearchPriceListener());
        this.clientFrame.SeeMenuListener(new SeeMenuListener());
        this.clientFrame.OkClientListener(new OkClientListener());
        this.clientFrame.CreateOrderListener(new CreateOrderListener());
    }

    class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox c = loginFrame.getComboBox();
            for(User user : user.getUsers()) {
                if(user.getUsername().equals(loginFrame.getUsername()) && c.getSelectedIndex()==0 && user.getRole() == Role.ADMINISTRATOR){
                    if(user.getPassword().equals(loginFrame.getPassword())){
                        loginFrame.setSuccessMessageLabel("Login successful!");
                        adminFrame.setVisible(true);
                        break;
                    } else {
                        loginFrame.setFailMessageLabel("Wrong password");
                    }
                } else if(user.getUsername().equals(loginFrame.getUsername()) && c.getSelectedIndex()==1 && user.getRole() == Role.CLIENT){
                    if(user.getPassword().equals(loginFrame.getPassword())) {
                        loginFrame.setSuccessMessageLabel("Login successful!");
                        clientName = user.getUsername();
                        clientFrame.setVisible(true);
                        break;
                    } else {
                        loginFrame.setFailMessageLabel("Wrong password");
                    }
                } else if(user.getUsername().equals(loginFrame.getUsername()) && c.getSelectedIndex()==2 && user.getRole() == Role.EMPLOYEE){
                    if(user.getPassword().equals(loginFrame.getPassword())) {
                        loginFrame.setSuccessMessageLabel("Login successful!");
                        delivery.addObserver(employeeFrame);
                        employeeFrame.setVisible(true);
                        break;
                    } else {
                        loginFrame.setFailMessageLabel("Wrong password");
                    }
                } else {
                    loginFrame.setFailMessageLabel("User doesn't exist!");
                }
            }
            Serializator.userSerialization(user);
        }
    }
    class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox c = loginFrame.getComboBox();
            User user = null;
            if(c.getSelectedIndex() == 0) {
                user = new User(loginFrame.getUsername(),loginFrame.getPassword(),Role.ADMINISTRATOR);
            }
            if (c.getSelectedIndex() == 1){
                user = new User(loginFrame.getUsername(),loginFrame.getPassword(),Role.CLIENT);
            }
            if (c.getSelectedIndex() == 2){
                user = new User(loginFrame.getUsername(),loginFrame.getPassword(),Role.EMPLOYEE);
            }
            List<User> users = user.getUsers();
            for(int i = 0; i < user.getUsers().size(); i++) {
                if(user.getUsers().get(i).addUser(user) == 0) {
                    user.getUsers().add(user);
                    loginFrame.setSuccessMessageLabel("User add!");
                    break;
                } else {
                    loginFrame.setFailMessageLabel("User already exist!");
                }
            }
            Serializator.userSerialization(user);
        }
    }
    class ImportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            delivery.importProducts();
            String text = "";
            List<MenuItem> list = delivery.getProducts();
            for(MenuItem m : list) {
                text += m.toString();
            }
            adminFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminFrame.getName();
            double rating = adminFrame.getRating();
            int calories = adminFrame.getCalories();
            int proteins = adminFrame.getProtiens();
            int fats = adminFrame.getFats();
            int sodium = adminFrame.getSodium();
            int price = adminFrame.getPrice();
            BaseProduct b = new BaseProduct(name,rating,calories,proteins,fats,sodium,price);
            delivery.addProduct(b);
            JOptionPane.showMessageDialog(new JFrame(), "Produs adaugat!");
            Serializator.deliverySerialization(delivery);
            //System.out.println("Produs adaugat");
        }
    }
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminFrame.getName();
            MenuItem m = delivery.searchMenuItem(name);
            if(m != null) {
                delivery.deleteProduct(name);
                JOptionPane.showMessageDialog(new JFrame(), "Produs sters!");
                //System.out.println("Produs sters");
            }
            Serializator.deliverySerialization(delivery);
        }
    }
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminFrame.getName();
            double rating = adminFrame.getRating();
            int calories = adminFrame.getCalories();
            int proteins = adminFrame.getProtiens();
            int fats = adminFrame.getFats();
            int sodium = adminFrame.getSodium();
            int price = adminFrame.getPrice();
            delivery.updateProduct(new BaseProduct(name,rating,calories,proteins,fats,sodium,price));
            JOptionPane.showMessageDialog(new JFrame(), "Produs modificat");
            //System.out.println("Produs modificat");
            Serializator.deliverySerialization(delivery);
        }
    }
    class CreateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            delivery.createCompositeProduct(list, adminFrame.getMenuName());
            Serializator.deliverySerialization(delivery);
            JOptionPane.showMessageDialog(new JFrame(), "Produs compus adaugat!");
        }
    }
    class OkAdminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminFrame.getName();
            List<MenuItem> m = delivery.searchByKeyWord(name);
            System.out.println(m.toString());
            list.addAll(m);
        }
    }
    class Report1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            delivery.findOrderByTime(adminFrame.getStartHour(), adminFrame.getEndHour());
            JOptionPane.showMessageDialog(new JFrame(), "Raport1 generat!");
            Serializator.deliverySerialization(delivery);
        }
    }
    class Report2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            delivery.findProductsMoreThan(adminFrame.getNbOfTimes());
            JOptionPane.showMessageDialog(new JFrame(), "Raport2 generat!");
            Serializator.deliverySerialization(delivery);
        }
    }
    class Report3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            delivery.findClientsWhoOrder(adminFrame.getNbOfTimes(), adminFrame.getAmount());
            JOptionPane.showMessageDialog(new JFrame(), "Raport3 generat!");
            Serializator.deliverySerialization(delivery);
        }
    }
    class Report4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String day = adminFrame.getDay();
            delivery.findProductsByDay(DayOfWeek.valueOf(day.toUpperCase()),adminFrame.getNbOfTimes());
            JOptionPane.showMessageDialog(new JFrame(), "Raport4 generat!");
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByKeyWord(clientFrame.getSearchName());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                   text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchRatingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByRating(clientFrame.getSearchRating());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchCaloriesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByCalories(clientFrame.getSearchCalories());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchProteinsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByProteins(clientFrame.getSearchProtiens());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchFatsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByFats(clientFrame.getSearchFats());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchSodiumListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchBySodium(clientFrame.getSearchSodium());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SearchPriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> list = delivery.searchByPrice(clientFrame.getSearchPrice());
            String text = "";
            if(list != null) {
                for(MenuItem m : list) {
                    text += m.toString();
                }
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class SeeMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = "";
            List<ro.tuc.tp.BusinessLogic.MenuItem> list = delivery.getProducts();
            for(MenuItem m : list) {
                text += m.toString();
            }
            clientFrame.setTextArea(text);
            Serializator.deliverySerialization(delivery);
        }
    }
    class CreateOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            delivery.createOrder(rand.nextInt(100),clientName, LocalDateTime.now(),order);
            Serializator.deliverySerialization(delivery);
            JOptionPane.showMessageDialog(new JFrame(), "Comanda plasata!");
        }
    }
    class OkClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = clientFrame.getProductName();
            List<MenuItem> m = delivery.searchByKeyWord(name);
            System.out.println(m.toString());
            order.addAll(m);
        }
    }
}
