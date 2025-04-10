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
 * Controller for the side ordering page
 */
public class SideController {

    @FXML
    private ComboBox<SideType> typeComboBox;

    @FXML
    private ComboBox<Size> sizeComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView sideImageView;

    private OrderManager orderManager;
    private Side side;

    /**
     * Initializes the controller
     */
    public void initialize() {
        // Initialize side type options
        typeComboBox.getItems().addAll(SideType.CHIPS, SideType.FRIES, SideType.ONION_RINGS, SideType.APPLE_SLICES);
        typeComboBox.setValue(SideType.CHIPS);

        // Initialize size options
        sizeComboBox.getItems().addAll(Size.SMALL, Size.MEDIUM, Size.LARGE);
        sizeComboBox.setValue(Size.SMALL);

        // Initialize quantity spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(valueFactory);

        // Set default side image
        try {
            sideImageView.setImage(new Image(getClass().getResourceAsStream("/images/sides.png")));
        } catch (Exception e) {
            System.err.println("Warning: Side image not found");
        }

        // Create initial side
        side = new Side(SideType.CHIPS, Size.SMALL);

        // Add listeners to update price
        typeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateSide();
            updateSideImage(newVal);
        });
        sizeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSide());
        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateSide());

        // Initialize price
        updatePrice();
    }

    /**
     * Updates the side image based on the selected type
     * @param sideType the selected side type
     */
    private void updateSideImage(SideType sideType) {
        try {
            String imagePath = "/resources/image/";

            switch (sideType) {
                case CHIPS:
                    imagePath += "chips.png";
                    break;
                case FRIES:
                    imagePath += "fries.png";
                    break;
                case ONION_RINGS:
                    imagePath += "onion_rings.png";
                    break;
                case APPLE_SLICES:
                    imagePath += "apple_slices.png";
                    break;
            }

            sideImageView.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        } catch (Exception e) {
            System.err.println("Warning: Side image not found");
        }
    }

    /**
     * Sets the order manager
     * @param orderManager the order manager to set
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Updates the side based on selections
     */
    private void updateSide() {
        side.setType(typeComboBox.getValue());
        side.setSize(sizeComboBox.getValue());
        side.setQuantity(quantitySpinner.getValue());

        updatePrice();
    }

    /**
     * Updates the displayed price
     */
    private void updatePrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        priceLabel.setText("$" + df.format(side.price()));
    }

    /**
     * Adds the side to the order
     * @param event the action event
     */
    @FXML
    void addToOrder(ActionEvent event) {
        orderManager.getCurrentOrder().addItem(side);

        // Close window
        ((Stage) priceLabel.getScene().getWindow()).close();
    }

    /**
     * Cancels the side order
     * @param event the action event
     */
    @FXML
    void cancel(ActionEvent event) {
        // Close window
        ((Stage) priceLabel.getScene().getWindow()).close();
    }
}