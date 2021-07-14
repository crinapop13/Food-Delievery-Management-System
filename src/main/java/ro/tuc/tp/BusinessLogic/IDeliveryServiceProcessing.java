package ro.tuc.tp.BusinessLogic;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfata IDeliveryService care declara metodele pe care DeliveryService trebuie sa le implementeze
 * @author Pop Crina-Maria
 */
public interface IDeliveryServiceProcessing {
    //administrator

    /**
     * Aceasta metoda immporta din fisierul productts.csv setul initial de produse
     *
     * @post !products.isEmpty()
     */
    public void importProducts();

    /**
     * Aceasta metoda adauga la meniu produsul primit ca si parametru
     *
     * @param menu produsul care urmeaza sa fie adaugat la meniu
     * @pre menu != null
     * @post products.size() == products.size()@pre + 1
     */
    public void addProduct(MenuItem menu);
    /**
     * Aceasta metoda sterge din meniu produsul
     * al carui nume coincide cu numele primit ca si parametru
     * @param name numele produsului pe care vrem sa il stergem
     * @pre name != null
     * @post products.size == products.size()@pre - 1
     */
    public void deleteProduct(String name);

    /**
     * Aceasta metoda modifica un produs din meniu
     * @param m este produsul pe care vrem sa il modificam
     * @pre m != null
     * @post products.size == products.size()@pre
     */
    public void updateProduct(MenuItem m);

    /**
     * Aceasta metoda permite crearea unui produs compus format  din mai multe produse de baza
     * @param base lista de produse de baza din care se formeaza noul produs
     * @param name numele produsului compus
     * @return obiectul creat de tipul CompositeProduct
     * @pre base != null && name != null
     * @post composed != null
     */
    public CompositeProduct createCompositeProduct(ArrayList<MenuItem> base, String name);

    /**
     * Aceasta metoda gaseste produsele care au fost comandate intr-un anumit interval de timp
     * @param start ora de inecput a intervalului
     * @param end ora de sfarsit a intervalului
     * @pre start >= 0 && end <= 24 && start < end
     */
    public void findOrderByTime(int start, int end);

    /**
     * Aceasta metoda gaseste produsele care au fost comandate mai mult decat un numar specificat de ori
     * @param times specifica numarul de ori
     * @pre times > 0
     */
    public void findProductsMoreThan(int times);

    /**
     * Aceasta metoda gaseste clientii care au comandat de un anumit numar de ori, iar comanda sa fi fost peste suma specificata
     * @param times numarul de ori
     * @param amount suma minima a comenzii
     * @pre times > 0 && amount >= 0
     */
    public void findClientsWhoOrder(int times, int amount);

    /**
     * Aceasta metoda gaseste produsele care au fost comandate intr-o anumita zi de un anumit numar de ori
     * @param d ziua in care au fost comandate produsele
     * @param nbOfTimes numarul de ori cat au fost comandate
     */
    public void findProductsByDay(DayOfWeek d, int nbOfTimes);

    //client

    /**
     * Aceasta metoda creeaza o noua comanda
     * @param idOrder numarul comenzii
     * @param idClient numele clientului
     * @param date ziua in care a fost facuta comanda
     * @param products produsele pe care vrem sa le comandam
     * @pre dOrder > 0 && idClient != null && products != null
     */
    public void createOrder(int idOrder, String idClient, LocalDateTime date, ArrayList<MenuItem> products);

    /**
     * Aceasta  metoda calculeaza pretul unei comenzi
     * @param o comanda pentru care se calculeaza pretul
     * @return un intreg care reprezinta pretul
     */
    public int computePriceForOrder(Order o);

    /**
     * Aceasta metoda apeleaza metoda din FileWrite care va genera factura pentru o comanda
     * @param o comanda pentru care generam factura
     * @param products o lista cu produsele care au fost comadate
     */
    public void generateBill(Order o, ArrayList<MenuItem> products);

    /**
     * Aceasta metoda cauta un produs din meniu dupa nume
     * @param name numele produusului pe care il cautam
     * @return un obiect de tipul MenuItem reprezentand produsul cautat
     */
    public MenuItem searchMenuItem(String name);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa un keyword
     * @param name keyword-ul dupa care cautam produsul
     * @return o lista cu toate produsele care contin keyword-ul respectiv
     * @pre name != null
     */
    public List<MenuItem> searchByKeyWord(String name);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa un anumit rating
     * @param rating rating-ul dupa care cautam
     * @return o lista cu toate produsele care au rating-ul respectiv
     * @pre rating >= 0
     */
    public List<MenuItem> searchByRating(double rating);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa calorii
     * @param calories caloriile dupa care cautam
     * @return o lista cu toate produsele care au caloriile respective
     * @pre calories >= 0
     */
    public List<MenuItem> searchByCalories(int calories);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa proteine
     * @param proteins cantitatea de proteine dupa care cautam
     * @return o lista cu toate produsele care au aceasta cantitate de proteine
     * @pre proteins >= 0
     */
    public List<MenuItem> searchByProteins(int proteins);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa grasimi
     * @param fat cantitatea de grasimi
     * @return o lista cu toate produsele care au aceasta cantitate de grasimi
     * @pre fat >= 0
     */
    public List<MenuItem> searchByFats(int fat);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa sodiu
     * @param sodium cantitatea de sodiu
     * @return o lista cu toate produsele care au cantitatea de sodiu respectiva
     */
    public List<MenuItem> searchBySodium(int sodium);

    /**
     * Aceasta metoda cauta folosind stream-uri un produs din meniu dupa pret
     * @param price pretul dupa care cautam
     * @return o lista cu toate produsele care au pretul respectiv
     */
    public List<MenuItem> searchByPrice(int price);
}
