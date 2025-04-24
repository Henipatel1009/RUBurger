package model;

/**
 * Represents a combo meal with a main item, side, and drink.
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
     * Constructs a new Combo.
     *
     * @param mainItem the main item (e.g., "Burger" or "Sandwich")
     * @param side     the side item (e.g., "Chips" or "Apple Slices")
     * @param drink    the drink flavor
     */
    public Combo(String mainItem, String side, String drink) {
        this.mainItem = mainItem;
        this.side = side;
        this.drink = drink;
        this.quantity = 1;
    }

    /**
     * Gets the main item.
     *
     * @return the main item.
     */
    public String getMainItem() {
        return mainItem;
    }

    /**
     * Gets the side item.
     *
     * @return the side item.
     */
    public String getSide() {
        return side;
    }

    /**
     * Gets the drink.
     *
     * @return the drink.
     */
    public String getDrink() {
        return drink;
    }

    /**
     * Calculates the price of the combo.
     * <p>
     * The price is calculated as follows:
     * combo price = (mainItemPrice + sidePrice + DRINK_PRICE - COMBO_DISCOUNT) * quantity
     * </p>
     *
     * @return the price of the combo.
     */
    @Override
    public double price() {
        double mainItemPrice = calculateMainItemPrice();
        double sidePrice = "Chips".equalsIgnoreCase(side) ? CHIPS_PRICE : APPLE_PRICE;
        return (mainItemPrice + sidePrice + DRINK_PRICE - COMBO_DISCOUNT) * quantity;
    }

    /**
     * Calculates the base price of the main item.
     * <p>
     * This is a placeholder implementation. In a complete application, you might look up
     * the price from more detailed item data.
     * </p>
     *
     * @return the price of the main item.
     */
    private double calculateMainItemPrice() {
        if (mainItem.toLowerCase().contains("burger")) {
            return 8.99;
        } else {
            return 7.99;
        }
    }

    /**
     * Returns a string representation of the combo.
     * <p>
     * Example output:
     * "Burger Combo with Chips and Cola - $12.47" or with quantity > 1,
     * "Burger Combo with Chips and Cola - $12.47 (x2)"
     * </p>
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("%s Combo with %s and %s - $%.2f%s",
                mainItem,
                side,
                drink,
                price(),
                (quantity > 1 ? " (x" + quantity + ")" : ""));
    }
}