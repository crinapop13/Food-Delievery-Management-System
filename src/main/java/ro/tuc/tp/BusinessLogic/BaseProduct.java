package ro.tuc.tp.BusinessLogic;

/**
 * Clasa BaseProduct modeleaza un produs de baza din meniu si mosteneste clasa MenuItem
 * @author Pop Crina-Maria
 */
public class BaseProduct extends MenuItem  {

    public BaseProduct(String name, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(name,rating,calories,protein,fat,sodium,price);
    }

    @Override
    public int computePrice() {
        return super.getPrice();
    }

    public String toString() {
        return "Base Product: " + super.getTitle() +
                " Rating: "+ super.getRating() + " Calories: " + super.getCalories() +
                " Protein: " + super.getProtein() + " Fats: " + super.getFat() +
                " Sodium: " + super.getSodium() + " Pret: " + super.getPrice() + "\n";
    }
}
