package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Controller for the burger ordering page
 */
public class BurgerController {

    @FXML
    private ComboBox<Bread> breadComboBox;

    @FXML
    private CheckBox doublePattyCheckBox;

    @FXML
    private CheckBox lettuceCheckBox;

    @FXML
    private CheckBox tomatoesCheckBox;

    @FXML
    private CheckBox onionsCheckBox;

    @FXML
    private CheckBox avocadoCheckBox;

    @FXML
    private CheckBox cheeseCheckBox;

    @FXML
    private CheckBox comboCheckBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Label priceLabel;

    @FXML
    private Button addToOrderButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView burgerImageView;

    private OrderManager orderManager;
    private Burger burger;
    private boolean isCombo;

    /**
     * Initializes the controller
     */
    public void initialize() {
        // Initialize bread options
        breadComboBox.getItems().addAll(Bread.BRIOCHE, Bread.WHEAT_BREAD, Bread.PRETZEL);
        breadComboBox.setValue(Bread.BRIOCHE);

        // Initialize quantity spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(valueFactory);

        // Set default burger image
        try {
            burgerImageView.setImage(new Image(getClass().getResourceAsStream("/images/burger.png")));
        } catch (Exception e) {
            // Image not found, set a placeholder or leave empty
            System.err.println("Warning: Burger image not found");
        }

        // Create initial burger
        burger = new Burger(Bread.BRIOCHE, false);
        isCombo = false;

        // Add listeners to update price
        breadComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        doublePattyCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        lettuceCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        tomatoesCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        onionsCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        avocadoCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        cheeseCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        comboCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateBurger());
        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateBurger());

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
     * Updates the burger based on selections
     */
    private void updateBurger() {
        burger.setBread(breadComboBox.getValue());
        burger.setDoublePatty(doublePattyCheckBox.isSelected());

        // Clear existing add-ons
        for (AddOns addOn : burger.getAddOns().toArray(new AddOns[0])) {
            burger.removeAddOn(addOn);
        }

        // Add selected add-ons
        if (lettuceCheckBox.isSelected()) burger.addAddOn(AddOns.LETTUCE);
        if (tomatoesCheckBox.isSelected()) burger.addAddOn(AddOns.TOMATOES);
        if (onionsCheckBox.isSelected()) burger.addAddOn(AddOns.ONIONS);
        if (avocadoCheckBox.isSelected()) burger.addAddOn(AddOns.AVOCADO);
        if (cheeseCheckBox.isSelected()) burger.addAddOn(AddOns.CHEESE);

        burger.setQuantity(quantitySpinner.getValue());
        isCombo = comboCheckBox.isSelected();

        updatePrice();
    }

    /**
     * Updates the displayed price
     */
    private void updatePrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        double price = burger.price();

        if (isCombo) {
            price += 2.0 * burger.getQuantity();
        }

        priceLabel.setText("$" + df.format(price));
    }

    /**
     * Adds the burger to the order
     * @param event the action event
     */
    @FXML
    void addToOrder(ActionEvent event) {
        try {
            if (isCombo) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ComboController.fxml"));
                Parent root = loader.load();

                ComboController controller = loader.getController();
                controller.setOrderManager(orderManager);
                controller.setBurger(burger);

                Stage stage = new Stage();
                stage.setTitle("Complete Combo");
                stage.setScene(new Scene(root));
                stage.show();

                // Close current window
                ((Stage) addToOrderButton.getScene().getWindow()).close();
            } else {
                orderManager.getCurrentOrder().addItem(burger);

                // Close window
                ((Stage) addToOrderButton.getScene().getWindow()).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels the burger order
     * @param event the action event
     */
    @FXML
    void cancel(ActionEvent event) {
        // Close window
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}