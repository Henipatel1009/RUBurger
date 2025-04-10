package model;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Class representing an order
 */
public class Order {
    private int number;
    private ArrayList<MenuItem> items;
    private static int nextOrderNumber = 1000;

    /**
     * Constructs a new order
     */
    public Order() {
        this.number = nextOrderNumber++;
        this.items = new ArrayList<>();
    }

    /**
     * Gets the order number
     * @return the order number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the list of items in the order
     * @return the list of items
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds an item to the order
     * @param item the item to add
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Removes an item from the order
     * @param item the item to remove
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Clears all items from the order
     */
    public void clearItems() {
        items.clear();
    }

    /**
     * Calculates the subtotal of the order
     * @return the subtotal
     */
    public double getSubtotal() {
        double subtotal = 0.0;
        for (MenuItem item : items) {
            subtotal += item.price();
        }
        return subtotal;
    }

    /**
     * Calculates the tax of the order
     * @return the tax
     */
    public double getTax() {
        return getSubtotal() * 0.06625; // NJ tax rate: 6.625%
    }

    /**
     * Calculates the total of the order
     * @return the total
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Returns a string representation of the order
     * @return the string representation
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder();

        sb.append("Order #").append(number).append("\n");
        sb.append("Items:\n");

        for (MenuItem item : items) {
            sb.append("- ").append(item.toString()).append(": $").append(df.format(item.price())).append("\n");
        }

        sb.append("\nSubtotal: $").append(df.format(getSubtotal()));
        sb.append("\nTax: $").append(df.format(getTax()));
        sb.append("\nTotal: $").append(df.format(getTotal()));

        return sb.toString();
    }
}
