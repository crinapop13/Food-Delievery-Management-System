package ro.tuc.tp.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa CompositeProduct modeleaza un produs compus din mai multe produse de baza si mosteneste clasa MenuItem
 * author Pop Crina-Maria
 */
public class CompositeProduct extends MenuItem {
    List<MenuItem> composite;

    /**
     * Constructor care creeaza un produs compus cu numele primit ca si parametru si o lista de produse de baza,
     * iar celelalte proprietati sunt calculate
     * @param name numele produsului compus
     * @param list lista de produse de baza
     */
    public CompositeProduct(String name, List<MenuItem> list) {
        super(name);
        composite = list;
        this.computeRaiting();
        this.computeCalories();
        this.computeProtein();
        this.computeFat();
        this.computeSodium();
        this.computePrice();
    }

    public int computePrice() {
        int price = 0;
        for(MenuItem item: composite) {
            price += item.getPrice();
        }
        super.setPrice(price);
        return price;
    }
    
    public double computeRaiting(){
        double rating = 0;
        for(MenuItem item: composite) {
            rating += item.getRating();
        }
        rating = rating / composite.size() * 1.0;
        super.setRating(rating);
        return rating;
    }

    public int computeCalories(){
        int calories = 0;
        for(MenuItem item: composite) {
            calories += item.getCalories();
        }
        super.setCalories(calories);
        return calories;
    }
    public int computeProtein() {
        int protein = 0;
        for(MenuItem item: composite) {
            protein += item.getProtein();
        }
        super.setProtein(protein);
        return protein;
    }
    public int computeFat() {
        int fat = 0;
        for(MenuItem item: composite) {
            fat += item.getFat();
        }
        super.setFat(fat);
        return fat;
    }
    public int computeSodium() {
        int sodium = 0;
        for(MenuItem item: composite) {
            sodium += item.getSodium();
        }
        super.setSodium(sodium);
        return sodium;
    }

    public String toString() {
        return "Composite Product: " + super.getTitle() +
                " Rating: "+ super.getRating() + " Calories: " + super.getCalories() +
                " Protein: " + super.getProtein() + " Fats: " + super.getFat() +
                " Sodium: " + super.getSodium() + " Pret: " + super.getPrice() + "\n";
    }
}
