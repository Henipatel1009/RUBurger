package model;

/**
 * Represents a combo meal with a main item, side, and drink
 */
public class Combo extends MenuItem {
    private String mainItem;
    private String side;
    private String drink;

    // Price constants
    private static final double CHIPS_PRICE = 1.99;
    private static final double APPLE_PRICE = 2.49;
    private static final double DRINK_PRICE = 1.99;
    private static final double COMBO_DISCOUNT = 1.00; // $1.00 discount for combo

    /**
     * Constructs a new combo
     * @param mainItem the main item (burger or sandwich)
     * @param side the side (Chips or Apple Slices)
     * @param drink the drink flavor
     */
    public Combo(String mainItem, String side, String drink) {
        this.mainItem = mainItem;
        this.side = side;
        this.drink = drink;
    }

    /**
     * Gets the main item
     * @return the main item
     */
    public String getMainItem() {
        return mainItem;
    }

    /**
     * Gets the side
     * @return the side
     */
    public String getSide() {
        return side;
    }

    /**
     * Gets the drink
     * @return the drink
     */
    public String getDrink() {
        return drink;
    }

    /**
     * Calculates the price of the combo
     * Using a placeholder for main item price calculation
     * @return the price of the combo
     */
    @Override
    public double price() {
        double mainItemPrice = calculateMainItemPrice();
        double sidePrice = "Chips".equals(side) ? CHIPS_PRICE : APPLE_PRICE;

        // Total price with combo discount
        return mainItemPrice + sidePrice + DRINK_PRICE - COMBO_DISCOUNT;
    }

    /**
     * Calculates the price of the main item
     * This is a placeholder method - in a real app, this would
     * look up the price from the appropriate model class
     * @return the price of the main item
     */
    private double calculateMainItemPrice() {
        // Placeholder logic - replace with actual implementation
        if (mainItem.contains("Burger")) {
            return 8.99; // Base burger price
        } else {
            return 7.99; // Base sandwich price
        }
    }

    @Override
    public String toString() {
        return mainItem + " Combo with " + side + " and " + drink;
    }
}