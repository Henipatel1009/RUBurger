package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Controller for the sandwich ordering page
 */
public class SandwichController {

    @FXML
    private ComboBox<Bread> breadComboBox;

    @FXML
    private ComboBox<Protein> proteinComboBox;

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
    private ImageView sandwichImageView;

    private OrderManager orderManager;
    private Sandwich sandwich;
    private boolean isCombo;

    /**
     * Initializes the controller
     */
    public void initialize() {
        // Initialize bread options
        breadComboBox.getItems().addAll(Bread.BRIOCHE, Bread.WHEAT_BREAD, Bread.PRETZEL, Bread.BAGEL, Bread.SOURDOUGH);
        breadComboBox.setValue(Bread.BRIOCHE);

        // Initialize protein options
        proteinComboBox.getItems().addAll(Protein.ROAST_BEEF, Protein.CHICKEN, Protein.SALMON);
        proteinComboBox.setValue(Protein.ROAST_BEEF);

        // Initialize quantity spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(valueFactory);

        // Set default sandwich image
        try {
            sandwichImageView.setImage(new Image(getClass().getResourceAsStream("/images/sandwich.png")));
        } catch (Exception e) {
            System.err.println("Warning: Sandwich image not found");
        }

        // Create initial sandwich
        sandwich = new Sandwich(Bread.BRIOCHE, Protein.ROAST_BEEF);
        isCombo = false;

        // Add listeners to update price
        breadComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        proteinComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        lettuceCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        tomatoesCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        onionsCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        avocadoCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        cheeseCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        comboCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> updateSandwich());
        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateSandwich());

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
     * Updates the sandwich based on selections
     */
    private void updateSandwich() {
        sandwich.setBread(breadComboBox.getValue());
        sandwich.setProtein(proteinComboBox.getValue());

        // Clear existing add-ons
        for (AddOns addOn : sandwich.getAddOns().toArray(new AddOns[0])) {
            sandwich.removeAddOn(addOn);
        }

        // Add selected add-ons
        if (lettuceCheckBox.isSelected()) sandwich.addAddOn(AddOns.LETTUCE);
        if (tomatoesCheckBox.isSelected()) sandwich.addAddOn(AddOns.TOMATOES);
        if (onionsCheckBox.isSelected()) sandwich.addAddOn(AddOns.ONIONS);
        if (avocadoCheckBox.isSelected()) sandwich.addAddOn(AddOns.AVOCADO);
        if (cheeseCheckBox.isSelected()) sandwich.addAddOn(AddOns.CHEESE);

        sandwich.setQuantity(quantitySpinner.getValue());
        isCombo = comboCheckBox.isSelected();

        updatePrice();
    }

    /**
     * Updates the displayed price
     */
    private void updatePrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        double price = sandwich.price();

        if (isCombo) {
            price += 2.0 * sandwich.getQuantity();
        }

        priceLabel.setText("$" + df.format(price));
    }

    /**
     * Adds the sandwich to the order
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
                controller.setSandwich(sandwich);

                Stage stage = new Stage();
                stage.setTitle("Complete Combo");
                stage.setScene(new Scene(root));
                stage.show();

                // Close current window
                ((Stage) priceLabel.getScene().getWindow()).close();
            } else {
                orderManager.getCurrentOrder().addItem(sandwich);

                // Close window
                ((Stage) priceLabel.getScene().getWindow()).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels the sandwich order
     * @param event the action event
     */
    @FXML
    void cancel(ActionEvent event) {
        // Close window
        ((Stage) priceLabel.getScene().getWindow()).close();
    }
}