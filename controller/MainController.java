package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.MenuItem;
import model.Order;
import model.OrderManager;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Main controller for the application
 * Handles navigation between different views
 */
public class MainController {

    @FXML
    private Button burgerButton;

    @FXML
    private Button sandwichButton;

    @FXML
    private Button beverageButton;

    @FXML
    private Button sideButton;

    @FXML
    private Button viewOrderButton;

    @FXML
    private Button allOrdersButton;

    private OrderManager orderManager;

    /**
     * Initializes the controller
     */
    public void initialize() {
        // Create order manager
        orderManager = new OrderManager();
    }

    /**
     * Opens the burger ordering view
     * @param event the action event
     */
    @FXML
    void orderBurger(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/BurgerView.fxml"));
            Parent root = loader.load();

            BurgerController controller = loader.getController();
            controller.setOrderManager(orderManager);

            Stage stage = new Stage();
            stage.setTitle("Order Burger");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load burger view: " + e.getMessage());
        }
    }

    /**
     * Opens the sandwich ordering view
     * @param event the action event
     */
    @FXML
    void orderSandwich(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/SandwichView.fxml"));
            Parent root = loader.load();

            SandwichController controller = loader.getController();
            controller.setOrderManager(orderManager);

            Stage stage = new Stage();
            stage.setTitle("Order Sandwich");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load sandwich view: " + e.getMessage());
        }
    }

    /**
     * Opens the beverage ordering view
     * @param event the action event
     */
    @FXML
    void orderBeverage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/BeverageView.fxml"));
            Parent root = loader.load();

            BeverageController controller = loader.getController();
            controller.setOrderManager(orderManager);

            Stage stage = new Stage();
            stage.setTitle("Order Beverage");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load beverage view: " + e.getMessage());
        }
    }

    /**
     * Opens the side ordering view
     * @param event the action event
     */
    @FXML
    void orderSide(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/SideView.fxml"));
            Parent root = loader.load();

            SideController controller = loader.getController();
            controller.setOrderManager(orderManager);

            Stage stage = new Stage();
            stage.setTitle("Order Side");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load side view: " + e.getMessage());
        }
    }

    /**
     * Opens the current order view
     * @param event the action event
     */
    @FXML
    void viewCurrentOrder(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/CurrentOrderView.fxml"));
            Parent root = loader.load();

            CurrentOrderController controller = loader.getController();
            controller.setOrderManager(orderManager);
            controller.loadOrderItems();

            Stage stage = new Stage();
            stage.setTitle("Current Order");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load current order view: " + e.getMessage());
        }
    }

    /**
     * Opens the all orders view
     * @param event the action event
     */
    @FXML
    void viewAllOrders(ActionEvent event) {
        try {
            // Create the main layout
            BorderPane root = new BorderPane();

            // Create the top section with title
            Label titleLabel = new Label("All Orders");
            titleLabel.setFont(new Font("System Bold", 24));
            BorderPane.setAlignment(titleLabel, Pos.CENTER);
            root.setTop(titleLabel);

            // Create the center section with two ListViews
            HBox centerBox = new HBox(20); // 20px spacing
            centerBox.setPadding(new Insets(10, 20, 10, 20));

            ListView<Order> ordersListView = new ListView<>();
            ListView<MenuItem> orderDetailsListView = new ListView<>();

            // Set preferred sizes
            ordersListView.setPrefSize(200, 300);
            orderDetailsListView.setPrefSize(350, 300);

            centerBox.getChildren().addAll(ordersListView, orderDetailsListView);
            root.setCenter(centerBox);

            // Create the bottom section with total and buttons
            VBox bottomBox = new VBox(10);
            bottomBox.setPadding(new Insets(10, 20, 20, 20));

            HBox totalBox = new HBox();
            totalBox.setAlignment(Pos.CENTER_RIGHT);
            Label totalLabel = new Label("Order Total: ");
            totalLabel.setFont(new Font("System Bold", 14));
            Label orderTotalLabel = new Label("$0.00");
            orderTotalLabel.setFont(new Font("System Bold", 14));
            totalBox.getChildren().addAll(totalLabel, orderTotalLabel);

            HBox buttonBox = new HBox(15);
            buttonBox.setAlignment(Pos.CENTER);
            Button cancelOrderButton = new Button("Cancel Order");
            Button exportButton = new Button("Export Orders");
            Button closeButton = new Button("Close");
            buttonBox.getChildren().addAll(cancelOrderButton, exportButton, closeButton);

            bottomBox.getChildren().addAll(totalBox, buttonBox);
            root.setBottom(bottomBox);

            // Create scene and stage
            Scene scene = new Scene(root, 600, 450);
            Stage stage = new Stage();
            stage.setTitle("All Orders");
            stage.setScene(scene);

            // Create and set up the controller
            AllOrdersController controller = new AllOrdersController();

            // Set the fields in the controller using reflection (since they are private)
            setField(controller, "ordersListView", ordersListView);
            setField(controller, "orderDetailsListView", orderDetailsListView);
            setField(controller, "orderTotalLabel", orderTotalLabel);
            setField(controller, "cancelOrderButton", cancelOrderButton);
            setField(controller, "exportButton", exportButton);

            // Set order manager
            controller.setOrderManager(orderManager);

            // Set up button actions
            cancelOrderButton.setOnAction(e -> controller.cancelOrder(e));
            exportButton.setOnAction(e -> controller.exportOrders(e));
            closeButton.setOnAction(e -> stage.close());

            // Initialize the controller manually
            controller.initialize();

            // Load orders
            controller.loadOrders();

            // Show the stage
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading All Orders view", e.getMessage());
        }
    }

    /**
     * Helper method to set private fields using reflection
     */
    private void setField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to show alert dialogs
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}