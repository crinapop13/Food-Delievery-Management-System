package ro.tuc.tp.Presentation.View;

import ro.tuc.tp.BusinessLogic.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa LogIn defineste fereastra GUI pentru logarea in aplicatie
 * @author Pop Crina-Maria
 */
public class LogIn extends JFrame{
    User users;
    JButton loginButton = new JButton("Login");
    JButton signupButton = new JButton("Sign Up");
    JTextField usernameField = new JTextField();
    JTextField passwordField = new JPasswordField();
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    JComboBox c;

    public LogIn(User u) {
        users = u;

        usernameLabel.setBounds(50,100,75,25);
        passwordLabel.setBounds(50,150,75,25);
        messageLabel.setBounds(125,300,250,35);
        messageLabel.setFont(new Font(null, Font.ITALIC,18));
        messageLabel.setForeground(Color.green);

        usernameField.setBounds(125,100,200,25);
        passwordField.setBounds(125,150,200,25);

        loginButton.setBounds(125,250,100,25);
        loginButton.setFocusable(false);
        signupButton.setBounds(225,250,100,25);
        signupButton.setFocusable(false);

        String[] roles = {"administrator","client","employee"};
        c = new JComboBox(roles);
        c.setBounds(175,200,100,25);

        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(messageLabel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(signupButton);
        this.add(c);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setLayout(null);
        this.setTitle("LogIn");
        this.setVisible(true);
    }
    public String getUsername() {
        return usernameField.getText();
    }
    public String getPassword() {
        return String.valueOf(passwordField.getText());
    }
    public JComboBox getComboBox() {
        return c;
    }
    public void setSuccessMessageLabel(String text) {
        messageLabel.setForeground(Color.green);
        messageLabel.setText(text);
    }
    public void setFailMessageLabel(String text) {
        messageLabel.setForeground(Color.red);
        messageLabel.setText(text);
    }
    public void LogInListener(ActionListener a) {
        loginButton.addActionListener(a);
    }
    public void SignUpListener(ActionListener a) {
        signupButton.addActionListener(a);
    }

    /*public ArrayList<User> getLoginInfo() {
        return loginInfo;
    }*/
}
