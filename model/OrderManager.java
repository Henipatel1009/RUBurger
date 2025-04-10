package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class for managing orders
 */
public class OrderManager {
    private ArrayList<Order> orders;
    private Order currentOrder;

    /**
     * Constructs a new order manager
     */
    public OrderManager() {
        this.orders = new ArrayList<>();
        this.currentOrder = new Order();
    }

    /**
     * Gets the list of orders
     * @return the list of orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Gets the current order
     * @return the current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Sets the current order
     * @param order the order to set as current
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    /**
     * Places the current order
     */
    public void placeOrder() {
        if (!currentOrder.getItems().isEmpty()) {
            orders.add(currentOrder);
            currentOrder = new Order();
        }
    }

    /**
     * Cancels an order
     * @param order the order to cancel
     */
    public void cancelOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Exports all orders to a text file
     * @param filePath the path of the file to export to
     * @throws IOException if an I/O error occurs
     */
    public void exportOrders(String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Order order : orders) {
                writer.println(order.toString());
                writer.println("----------------------------------------");
            }
        } catch (IOException e) {
            throw new IOException("Error exporting orders: " + e.getMessage());
        }
    }
}
