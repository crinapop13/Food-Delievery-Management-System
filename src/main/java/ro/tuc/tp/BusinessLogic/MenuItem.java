package ro.tuc.tp.BusinessLogic;

import java.io.Serializable;

/**
 * Clasa MenuItem este o clasa abstracta care defineste proprietatile unui produs din meniu, care poate fi de baza sau compus
 * @author Pop Crina-Maria
 */
public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    /**
     * Constructor care seteaza valoarea varuabilelei instanta titlu
     * @param name numele pe care il va lua produsul
     */
    public MenuItem(String name) {
        this.title = name;
    }

    /**
     * Constructor care seteaza proprietatile tuturor variabilelor instanta
     */
    public MenuItem(String name, double rating, int calories, int protein, int fat, int sodium, int price) {
        title = name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Metoda abstracta pe care o vor implementa claele care mostenesc aceasta clasa
     * @return pretul produsului
     */
    public abstract int computePrice();

}
