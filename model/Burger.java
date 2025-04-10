// model/Burger.java
package model;

/**
 * Class representing a burger
 */
public class Burger extends Sandwich {
    private boolean doublePatty;

    /**
     * Constructs a new burger
     * @param bread the type of bread
     * @param doublePatty whether it's a double patty
     */
    public Burger(Bread bread, boolean doublePatty) {
        super(bread, Protein.BEEF_PATTY);
        this.doublePatty = doublePatty;
    }

    /**
     * Calculates the price of the burger
     * @return the price
     */
    @Override
    public double price() {
        double basePrice = super.price();

        if (doublePatty) {
            basePrice += 2.50;
        }

        return basePrice;
    }

    /**
     * Checks if the burger has a double patty
     * @return true if double patty, false otherwise
     */
    public boolean isDoublePatty() {
        return doublePatty;
    }

    /**
     * Sets whether the burger has a double patty
     * @param doublePatty true for double patty, false otherwise
     */
    public void setDoublePatty(boolean doublePatty) {
        this.doublePatty = doublePatty;
    }

    /**
     * Returns a string representation of the burger
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(doublePatty ? "Double" : "Single").append(" Burger on ").append(bread);

        if (!addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i));
                if (i < addOns.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        if (quantity > 1) {
            sb.append(" (").append(quantity).append(")");
        }

        return sb.toString();
    }
}