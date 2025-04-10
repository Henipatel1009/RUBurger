// model/Beverage.java
package model;

/**
 * Class representing a beverage
 */
public class Beverage extends MenuItem {
    private Size size;
    private Flavor flavor;

    /**
     * Constructs a new beverage
     * @param size the size of the beverage
     * @param flavor the flavor of the beverage
     */
    public Beverage(Size size, Flavor flavor) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = 1;
    }

    /**
     * Calculates the price of the beverage
     * @return the price
     */
    @Override
    public double price() {
        double basePrice = 1.99; // Small drink price

        // Add price based on size
        if (size == Size.MEDIUM) {
            basePrice = 2.49;
        } else if (size == Size.LARGE) {
            basePrice = 2.99;
        }

        return basePrice * quantity;
    }

    /**
     * Gets the size of the beverage
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the beverage
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets the flavor of the beverage
     * @return the flavor
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of the beverage
     * @param flavor the flavor to set
     */
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    /**
     * Returns a string representation of the beverage
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" ").append(flavor);

        if (quantity > 1) {
            sb.append(" (").append(quantity).append(")");
        }

        return sb.toString();
    }
}
