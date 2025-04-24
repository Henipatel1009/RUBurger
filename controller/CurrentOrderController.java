package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.MenuItem;
import model.Order;
import model.OrderManager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * Controller for the current order page
 */
public class CurrentOrderController {

    @FXML
    private ListView<MenuItem> orderItemsListView;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Button removeButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button placeOrderButton;

    private OrderManager orderManager;
    private ObservableList<MenuItem> orderItems;

    /**
     * Initializes the controller
     */
    public void initialize() {
        orderItems = FXCollections.observableArrayList();
        orderItemsListView.setItems(orderItems);

        // Set cell factory to display item details
        orderItemsListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    DecimalFormat df = new DecimalFormat("0.00");
                    setText(item.getClass().getSimpleName() + " - $" + df.format(item.price()));
                }
            }
        });

        // Update buttons based on selection
        orderItemsListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> removeButton.setDisable(newVal == null)
        );
    }

    /**
     * Sets the order manager
     * @param orderManager the order manager to set
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Loads the items from the current order
     */
    public void loadOrderItems() {
        Order currentOrder = orderManager.getCurrentOrder();
        orderItems.clear();
        orderItems.addAll(currentOrder.getItems());
        updateOrderSummary();
    }

    /**
     * Updates the order summary (subtotal, tax, total)
     */
    private void updateOrderSummary() {
        DecimalFormat df = new DecimalFormat("0.00");
        Order currentOrder = orderManager.getCurrentOrder();

        double subtotal = currentOrder.getSubtotal();
        double tax = currentOrder.getTax();
        double total = currentOrder.getTotal();

        subtotalLabel.setText("$" + df.format(subtotal));
        taxLabel.setText("$" + df.format(tax));
        totalLabel.setText("$" + df.format(total));
    }

    /**
     * Removes the selected item from the order
     * @param event the action event
     */
    @FXML
    void removeItem(ActionEvent event) {
        MenuItem selectedItem = orderItemsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            orderManager.getCurrentOrder().removeItem(selectedItem);
            loadOrderItems();
        }
    }

    /**
     * Clears all items from the order
     * @param event the action event
     */
    @FXML
    void clearOrder(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Clear Order");
        confirm.setHeaderText("Clear All Items");
        confirm.setContentText("Are you sure you want to clear all items from the order?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            orderManager.getCurrentOrder().clearItems();
            loadOrderItems();
        }
    }

    /**
     * Places the current order
     * @param event the action event
     */
    @FXML
    void placeOrder(ActionEvent event) {
        Order currentOrder = orderManager.getCurrentOrder();

        if (currentOrder.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Order");
            alert.setHeaderText("Cannot Place Empty Order");
            alert.setContentText("Please add items to your order before placing it.");
            alert.showAndWait();
            return;
        }

        orderManager.placeOrder();

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Order Placed");
        success.setHeaderText("Order Placed Successfully");
        success.setContentText("Your order has been placed. Order #" + (orderManager.getOrders().size()));
        success.showAndWait();

        // Close window after placing order
        ((Stage) placeOrderButton.getScene().getWindow()).close();
    }

    /**
     * Handles the back button action.
     * Closes (hides) the current window and opens the home screen in a new window.
     * @param event the action event
     */
    @FXML
    private void handleBack(ActionEvent event) {
        // Get the current stage (the current order window) and close it completely.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Closes the window.
     * @param event the action event
     */
    @FXML
    void close(ActionEvent event) {
        ((Stage) placeOrderButton.getScene().getWindow()).close();
    }

    public void cancel(ActionEvent actionEvent) {
    }
}