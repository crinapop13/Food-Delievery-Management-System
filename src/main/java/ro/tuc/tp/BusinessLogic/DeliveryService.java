package ro.tuc.tp.BusinessLogic;

import ro.tuc.tp.Data.FileWrite;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa DeliveryService
 * @author Pop Crina-Maria
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private HashMap<Order, ArrayList<MenuItem>> orderInformation;
    private List<MenuItem> products;
    private FileWrite fileWrite = new FileWrite();

    /**
     * Constructor fara parametrii
     */
    public DeliveryService(){
        orderInformation = new HashMap<Order,ArrayList<MenuItem>>();
        products = new ArrayList<MenuItem>();
    }

    /**
     * @invariant Invariantul clasei
     * @return true daca produsele respecta criteriul, altfel false
     */
    private boolean isWellFormed(){
        Set<MenuItem> set = new HashSet<MenuItem>(products);
        if(set.size() < products.size()) {
            return false;
        }
        return true;
    }

    @Override
    public void importProducts() {
        List<BaseProduct> menu = new ArrayList<>();
        try (Stream<String> file = Files.lines(Paths.get("products.csv"))){
            menu = file.skip(1)
                    .map(line -> line.split(","))
                    .map(line -> new BaseProduct(line[0],Double.parseDouble(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line[5]),Integer.parseInt(line[6])))
                    .filter(distinctByKey(BaseProduct::getTitle))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        products.addAll(menu);
        assert !products.isEmpty();
        assert isWellFormed();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> set = ConcurrentHashMap.newKeySet();
        return t -> set.add(keyExtractor.apply(t));
    }

    @Override
    public CompositeProduct createCompositeProduct(ArrayList<MenuItem> products, String name) {
        assert products != null && name != null;
        assert isWellFormed();
        CompositeProduct composed = new CompositeProduct(name,products);
        addProduct(composed);
        assert composed != null;
        assert isWellFormed();
        return composed;
    }

    @Override
    public void addProduct(MenuItem item) {
        assert item != null;
        assert isWellFormed();
        int s = products.size();
        products.add(item);
        products = products.stream()
                .filter(distinctByKey(MenuItem::getTitle))
                .collect(Collectors.toList());
        assert products.size() == s + 1;
        assert isWellFormed();
    }

    @Override
    public void deleteProduct(String name) {
        assert name != null;
        assert isWellFormed();
        int s = products.size();
        for(MenuItem item: products){
            if(name.equals(item.getTitle())){
                products.remove(item);
                break;
            }
        }
        assert products.size() == s - 1;
        assert isWellFormed();
    }

    @Override
    public void updateProduct(MenuItem m) {
        assert m != null;
        assert isWellFormed();
        int s = products.size();
        for(MenuItem item: products) {
            if(item.getTitle().equals(m.getTitle())) {
                deleteProduct(item.getTitle());
                addProduct(m);
            }
        }
        assert products.size() == s;
        assert isWellFormed();
    }

    @Override
    public void findOrderByTime(int start, int end) {
        assert start >= 0 && end >= 0 && end <= 24 && start < end;
        List<Order> orders = orderInformation.keySet().stream()
                .filter(order -> order.getOrderDate().getHour() >= start && order.getOrderDate().getHour() <= end)
                .collect(Collectors.toList());
        fileWrite.generateReport1((ArrayList<Order>) orders);
    }

    @Override
    public void findProductsMoreThan(int times) {
        assert times > 0;
        HashMap<MenuItem, Integer> products = new HashMap<MenuItem, Integer>();
        for(Map.Entry<Order,ArrayList<MenuItem>> set: orderInformation.entrySet()) {
            for(MenuItem m: set.getValue()) {
                if(products.containsKey(m)) {
                    products.replace(m, products.get(m) + 1);
                } else {
                    products.put(m, 1);
                }
            }
        }
        List<MenuItem> prod = products.keySet().stream()
                .filter(p -> products.get(p) >= times)
                .collect(Collectors.toList());
        fileWrite.generateReport2((ArrayList<MenuItem>)prod);
    }

    @Override
    public void findClientsWhoOrder(int times, int amount) {
        assert times > 0 && amount >= 0;
        List<Order> orders = orderInformation.keySet().stream()
                .filter(o -> o.getPrice() > amount)
                .collect(Collectors.toList());
        HashMap<String, Integer> clients = new HashMap<>();
        for(Order order : orders) {
            if(clients.containsKey(order.getClientId())) {
                clients.replace(order.getClientId(),clients.get(order.getClientId() + 1));
            } else {
                clients.put(order.getClientId(), 1);
            }
        }
        List<String> clientsId = clients.keySet().stream()
                .filter(c -> clients.get(c) >= times)
                .collect(Collectors.toList());
        fileWrite.generateReport3((ArrayList<String>) clientsId);
    }

    @Override
    public void findProductsByDay(DayOfWeek day, int nbOfTimes) {
        assert nbOfTimes > 0;
        HashMap<MenuItem, Integer> products = new HashMap<>();
        List<Order> orders = orderInformation.keySet().stream()
                .filter(date -> date.getOrderDate().getDayOfWeek().equals(day))
                .collect(Collectors.toList());
        for(Order o : orders) {
            for(MenuItem m: orderInformation.get(o)) {
                if(products.containsKey(m)) {
                    products.replace(m, products.get(m) + 1);
                } else {
                    products.put(m, 1);
                }
            }
        }
        fileWrite.generateReport4(products);
    }
    @Override
    public List<MenuItem> searchByKeyWord(String name) {
        assert name != null;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getTitle().contains(name))
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchByRating(double rating) {
        assert rating >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getRating() == rating)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchByCalories(int calories) {
        assert calories >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getCalories() == calories)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchByProteins(int proteins) {
        assert proteins >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getProtein() == proteins)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchByFats(int fat) {
        assert fat >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getFat() == fat)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchBySodium(int sodium) {
        assert sodium >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getSodium() == sodium)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchByPrice(int price) {
        assert price >= 0;
        assert isWellFormed();
        return products.stream()
                .filter(prod -> prod.getPrice() == price)
                .collect(Collectors.toList());
    }
    @Override
    public void createOrder(int idOrder, String idClient, LocalDateTime date, ArrayList<MenuItem> products) {
        assert idOrder > 0 && idClient != null && products != null;
        assert isWellFormed();
        int price = 0;
        Order order = new Order(idOrder, idClient, date, price);
        orderInformation.put(order, products);
        price = computePriceForOrder(order);
        order.setPrice(price);
        generateBill(order,products);
        setChanged();
        notifyObservers(order);
        assert isWellFormed();
    }

    @Override
    public int computePriceForOrder(Order o) {
        int price = 0;
        ArrayList<MenuItem> products = orderInformation.get(o);
        for(MenuItem m: products) {
            price += m.getPrice();
        }
        return price;
    }

    @Override
    public void generateBill(Order o, ArrayList<MenuItem> list) {
        fileWrite.generateBill(o, list);
    }

    @Override
    public MenuItem searchMenuItem(String name) {
        for(MenuItem m: products) {
            if(m.getTitle().contains(name)){
                return m;
            }
        }
        return null;
    }

    /**
     * Aceasta metoda ne returneaza produsele din meniu
     * @return o lista cu produsele din meniu
     */

    public List<MenuItem> getProducts() {
        return products;
    }

    /**
     * Aceasta metoda ne returneaza o structura de date care tine toate comenzile si produsele comandate
     * @return un HashMap care contine comenzile asociate produelor comandate
     */
    public HashMap<Order, ArrayList<MenuItem>> getOrderInformation() {
        return orderInformation;
    }
}
