package ro.tuc.tp.BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa User defineste utilzatorii pe care ii poate avea aplicatia, fiecare avand un username, o parola si un rol
 * @author Pop Crina-Maria
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Role role;
    private static List<User> users;

    /**
     * Constructorul fara parametrii defineste 3 utilizatori care vor exista de la inceput
     */
    public User() {
        users = new ArrayList<User>();
        users.add(new User("Crina", "boss", Role.ADMINISTRATOR));
        users.add(new User("client", "client", Role.CLIENT));
        users.add(new User("employee", "employee", Role.EMPLOYEE));
    }

    /**
     * Constructorul cu parametrii seteaza valorile variabilelor instanta
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * Aceasta metoda cauta user-ul primit ca si parametru in lista de useri deja existenti
     * @return true daca acesta exista, altfel false
     */
    private boolean findUser(User u) {
        boolean ok = false;
        for(User user: users) {
            if(user.username.equals(u.username)) {
                ok = true;
                break;
            }
        }
        return ok;
    }

    /**
     * Aceasta metoda aduga user-ul primit ca ssi parametru si il aduga in lista daca nu exista deja cumva
     * @return 0 daca user-ul a fost adaugat, 1 in caz de eroare
     */
    public int addUser(User u) {
        if(!findUser(u)) {
            users.add(u);
            //System.out.println("User-ul a fost adaugat!\n");
            return 0;
        } else {
            //System.out.println("User-ul exista deja!\n");
            return 1;
        }
    }

    public  String toString() {
        return "Username: " + username + ", password: " + password;

    }
}
