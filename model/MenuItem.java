// model/MenuItem.java
package model;

/**
 * Abstract class representing a menu item
 */
public abstract class MenuItem {
    protected int quantity;

    /**
     * Calculates the price of the menu item
     * @return the price
     */
    public abstract double price();

    /**
     * Gets the quantity of the menu item
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the menu item
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
