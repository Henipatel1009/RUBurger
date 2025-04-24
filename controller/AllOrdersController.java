package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Order;
import model.MenuItem;
import model.OrderManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the all orders page.
 */
public class AllOrdersController {

    @FXML
    private ListView<Order> ordersListView;

    @FXML
    private ListView<MenuItem> orderDetailsListView;

    @FXML
    private Label orderTotalLabel;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Button exportButton;

    private OrderManager orderManager;
    private ObservableList<Order> orders;
    private ObservableList<MenuItem> orderItems;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        orders = FXCollections.observableArrayList();
        orderItems = FXCollections.observableArrayList();

        ordersListView.setItems(orders);
        orderDetailsListView.setItems(orderItems);

        // Set cell factory for orders list
        ordersListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) {
                    setText(null);
                } else {
                    // Using String.format to format total to 2 decimals
                    setText("Order #" + order.getNumber() + " - $" + String.format("%.2f", order.getTotal()));
                }
            }
        });

        // Set cell factory for order details list to display full item description.
        orderDetailsListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Uses the overridden toString() of the MenuItem (or Combo) to display details.
                    setText(item.toString());
                }
            }
        });

        // Update order details when an order is selected.
        ordersListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        loadOrderDetails(newVal);
                        cancelOrderButton.setDisable(false);
                    } else {
                        orderItems.clear();
                        orderTotalLabel.setText("$0.00");
                        cancelOrderButton.setDisable(true);
                    }
                }
        );
    }

    /**
     * Sets the order manager.
     * @param orderManager the order manager to set
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Loads all orders from the order manager.
     */
    public void loadOrders() {
        List<Order> allOrders = orderManager.getOrders();
        orders.clear();
        orders.addAll(allOrders);
        exportButton.setDisable(allOrders.isEmpty());
        cancelOrderButton.setDisable(true);
    }

    /**
     * Loads the details of a selected order.
     * @param order the selected order.
     */
    private void loadOrderDetails(Order order) {
        orderItems.clear();
        orderItems.addAll(order.getItems());
        orderTotalLabel.setText("$" + String.format("%.2f", order.getTotal()));
    }

    /**
     * Cancels the selected order.
     * @param event the action event.
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        Order selectedOrder = ordersListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Cancel Order");
            confirm.setHeaderText("Cancel Order #" + selectedOrder.getNumber());
            confirm.setContentText("Are you sure you want to cancel this order?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                orderManager.cancelOrder(selectedOrder);
                loadOrders();
                orderItems.clear();
                orderTotalLabel.setText("$0.00");
            }
        }
    }

    /**
     * Exports all orders to a text file.
     * @param event the action event.
     */
    @FXML
    void exportOrders(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("orders.txt");

        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                for (Order order : orderManager.getOrders()) {
                    writer.write("Order #" + order.getNumber() + "\n");
                    writer.write("Items:\n");

                    for (MenuItem item : order.getItems()) {
                        writer.write("- " + item.toString() + "\n");
                    }
                    writer.write("\nTotal: $" + String.format("%.2f", order.getTotal()) + "\n\n");
                }

                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Export Successful");
                success.setHeaderText("Orders Exported");
                success.setContentText("All orders have been exported to " + file.getName());
                success.showAndWait();
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Export Error");
                error.setHeaderText("Error Exporting Orders");
                error.setContentText("An error occurred while exporting orders: " + e.getMessage());
                error.showAndWait();
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the window.
     * @param event the action event.
     */
    @FXML
    void close(ActionEvent event) {
        ((Stage) exportButton.getScene().getWindow()).close();
    }
}