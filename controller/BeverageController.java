package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.text.DecimalFormat;

/**
 * Controller for the beverage ordering page
 */
public class BeverageController {

    @FXML
    private ComboBox<Flavor> flavorComboBox;

    @FXML
    private ComboBox<Size> sizeComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView beverageImageView;

    private OrderManager orderManager;
    private Beverage beverage;

    /**
     * Initializes the controller
     */
    public void initialize() {
        // Initialize flavor options (15 required by project)
        flavorComboBox.getItems().addAll(
                Flavor.COLA, Flavor.TEA, Flavor.JUICE, Flavor.LEMONADE, Flavor.ORANGE_SODA,
                Flavor.ROOT_BEER, Flavor.GINGER_ALE, Flavor.SPARKLING_WATER, Flavor.ICED_COFFEE,
                Flavor.FRUIT_PUNCH, Flavor.GRAPE_SODA, Flavor.CRANBERRY_JUICE, Flavor.APPLE_JUICE,
                Flavor.CHOCOLATE_MILK, Flavor.STRAWBERRY_MILK
        );
        flavorComboBox.setValue(Flavor.COLA);

        // Initialize size options
        sizeComboBox.getItems().addAll(Size.SMALL, Size.MEDIUM, Size.LARGE);
        sizeComboBox.setValue(Size.SMALL);

        // Initialize quantity spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(valueFactory);

        // Set default beverage image
        try {
            beverageImageView.setImage(new Image(getClass().getResourceAsStream("/resources/images/beverage.png")));
        } catch (Exception e) {
            System.err.println("Warning: Beverage image not found");
        }

        // Create initial beverage
        beverage = new Beverage(Size.SMALL, Flavor.COLA);

        // Add listeners to update price
        flavorComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateBeverage());
        sizeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateBeverage());
        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateBeverage());

        // Initialize price
        updatePrice();
    }

    /**
     * Sets the order manager
     * @param orderManager the order manager to set
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Updates the beverage based on selections
     */
    private void updateBeverage() {
        beverage.setSize(sizeComboBox.getValue());
        beverage.setFlavor(flavorComboBox.getValue());
        beverage.setQuantity(quantitySpinner.getValue());

        updatePrice();
    }

    /**
     * Updates the displayed price
     */
    private void updatePrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        priceLabel.setText("$" + df.format(beverage.price()));
    }

    /**
     * Adds the beverage to the order
     * @param event the action event
     */
    @FXML
    void addToOrder(ActionEvent event) {
        orderManager.getCurrentOrder().addItem(beverage);

        // Close window
        ((Stage) priceLabel.getScene().getWindow()).close();
    }

    /**
     * Cancels the beverage order
     * @param event the action event
     */
    @FXML
    void cancel(ActionEvent event) {
        // Close window
        ((Stage) priceLabel.getScene().getWindow()).close();
    }
}