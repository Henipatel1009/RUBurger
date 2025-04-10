package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the combo completion page
 */
public class ComboController implements Initializable {

    @FXML
    private ComboBox<SideType> sideComboBox;

    @FXML
    private ComboBox<Flavor> drinkComboBox;

    @FXML
    private ImageView sideImageView;

    @FXML
    private ImageView drinkImageView;

    @FXML
    private Button addToOrderButton;

    @FXML
    private Button cancelButton;

    private OrderManager orderManager;
    private Sandwich sandwich;
    private Burger burger;
    private Combo combo;

    /**
     * Initializes the controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Debug statements
        System.out.println("ComboController initialize called");
        System.out.println("sideComboBox: " + (sideComboBox == null ? "null" : "not null"));
        System.out.println("drinkComboBox: " + (drinkComboBox == null ? "null" : "not null"));

        // Initialize side options for combo
        if (sideComboBox != null) {
            // Clear any existing items
            sideComboBox.getItems().clear();

            // Add the side options
            sideComboBox.getItems().addAll(SideType.CHIPS, SideType.APPLE_SLICES);

            // Set default selection
            sideComboBox.setValue(SideType.CHIPS);

            // Add listener to update side image when selection changes
            sideComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    updateSideImage(newVal);
                }
            });

            // Use a custom cell factory to display proper names
            sideComboBox.setCellFactory(c -> new ListCell<SideType>() {
                @Override
                protected void updateItem(SideType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item == SideType.CHIPS ? "Chips" : "Apple Slices");
                    }
                }
            });

            sideComboBox.setButtonCell(new ListCell<SideType>() {
                @Override
                protected void updateItem(SideType item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item == SideType.CHIPS ? "Chips" : "Apple Slices");
                    }
                }
            });
        }

        // Initialize drink options for combo
        if (drinkComboBox != null) {
            drinkComboBox.getItems().clear();
            drinkComboBox.getItems().addAll(Flavor.COLA, Flavor.TEA, Flavor.JUICE);
            drinkComboBox.setValue(Flavor.COLA);

            // Add listener to update drink image when selection changes
            drinkComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    updateDrinkImage(newVal);
                }
            });

            // Use a custom cell factory to display proper names
            drinkComboBox.setCellFactory(c -> new ListCell<Flavor>() {
                @Override
                protected void updateItem(Flavor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        switch (item) {
                            case COLA:
                                setText("Cola");
                                break;
                            case TEA:
                                setText("Tea");
                                break;
                            case JUICE:
                                setText("Juice");
                                break;
                            default:
                                setText(item.name());
                        }
                    }
                }
            });

            drinkComboBox.setButtonCell(new ListCell<Flavor>() {
                @Override
                protected void updateItem(Flavor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        switch (item) {
                            case COLA:
                                setText("Cola");
                                break;
                            case TEA:
                                setText("Tea");
                                break;
                            case JUICE:
                                setText("Juice");
                                break;
                            default:
                                setText(item.name());
                        }
                    }
                }
            });
        }

        // Set default images
        try {
            if (sideImageView != null) {
                // Try different paths to find the image
                Image sideImage = tryLoadImage("/resources/image/chips.png",
                        "/image/chips.png",
                        "/resources/images/chips.png",
                        "/images/chips.png");
                if (sideImage != null) {
                    sideImageView.setImage(sideImage);
                }
            }

            if (drinkImageView != null) {
                // Try different paths to find the image
                Image drinkImage = tryLoadImage("/resources/image/cola.png",
                        "/image/cola.png",
                        "/resources/images/cola.png",
                        "/images/cola.png");
                if (drinkImage != null) {
                    drinkImageView.setImage(drinkImage);
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: Combo images not found: " + e.getMessage());
        }
    }

    /**
     * Helper method to try loading an image from different potential paths
     */
    private Image tryLoadImage(String... paths) {
        for (String path : paths) {
            try {
                Image image = new Image(getClass().getResourceAsStream(path));
                if (image != null && !image.isError()) {
                    return image;
                }
            } catch (Exception e) {
                // Try next path
            }
        }
        System.err.println("Warning: Could not find image in any of the provided paths");
        return null;
    }

    /**
     * Updates the side image based on the selected type
     * @param sideType the selected side type
     */
    private void updateSideImage(SideType sideType) {
        if (sideImageView == null) return;

        try {
            String imageName = sideType == SideType.CHIPS ? "chips.png" : "appleSlices.png";

            // Try different paths
            Image sideImage = tryLoadImage("/resources/image/" + imageName,
                    "/image/" + imageName,
                    "/resources/images/" + imageName,
                    "/images/" + imageName);

            if (sideImage != null) {
                sideImageView.setImage(sideImage);
            }
        } catch (Exception e) {
            System.err.println("Warning: Side image not found: " + e.getMessage());
        }
    }

    /**
     * Updates the drink image based on the selected flavor
     * @param flavor the selected drink flavor
     */
    private void updateDrinkImage(Flavor flavor) {
        if (drinkImageView == null) return;

        try {
            String imageName;
            if (flavor == Flavor.COLA) {
                imageName = "cola.png";
            } else if (flavor == Flavor.TEA) {
                imageName = "tea.png";
            } else {
                imageName = "juice.png";
            }

            // Try different paths
            Image drinkImage = tryLoadImage("/resources/image/" + imageName,
                    "/image/" + imageName,
                    "/resources/images/" + imageName,
                    "/images/" + imageName);

            if (drinkImage != null) {
                drinkImageView.setImage(drinkImage);
            }
        } catch (Exception e) {
            System.err.println("Warning: Drink image not found: " + e.getMessage());
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
     * Sets the sandwich for the combo
     * @param sandwich the sandwich to set
     */
    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
        this.burger = null;
    }

    /**
     * Sets the burger for the combo
     * @param burger the burger to set
     */
    public void setBurger(Burger burger) {
        this.burger = burger;
        this.sandwich = null;
    }

    /**
     * Adds the combo to the order
     * @param event the action event
     */
    @FXML
    void addToOrder(ActionEvent event) {
        try {
            // Get selected side type and flavor
            SideType selectedSideType = sideComboBox != null ?
                    sideComboBox.getValue() : SideType.CHIPS;

            Flavor selectedFlavor = drinkComboBox != null ?
                    drinkComboBox.getValue() : Flavor.COLA;

            // Create side and beverage
            Side side = new Side(selectedSideType, Size.SMALL);
            Beverage drink = new Beverage(Size.MEDIUM, selectedFlavor);

            // Create the combo
            if (sandwich != null) {
                combo = new Combo(sandwich.toString(), drink.toString(), side.toString());
                System.out.println("Created sandwich combo: " + combo);
            } else if (burger != null) {
                combo = new Combo(burger.toString(), drink.toString(), side.toString());
                System.out.println("Created burger combo: " + combo);
            } else {
                System.err.println("Error: No sandwich or burger selected for combo");
                return;
            }

            // Add to order
            if (combo != null && orderManager != null && orderManager.getCurrentOrder() != null) {
                orderManager.getCurrentOrder().addItem(combo);
                System.out.println("Added combo to order: " + combo);
            } else {
                System.err.println("Error: Could not add combo to order");
            }

            // Close window
            closeWindow();
        } catch (Exception e) {
            System.err.println("Error creating combo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cancels the combo creation
     * @param event the action event
     */
    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    /**
     * Helper method to close the window
     */
    private void closeWindow() {
        // Try different ways to get the stage
        if (cancelButton != null && cancelButton.getScene() != null) {
            ((Stage) cancelButton.getScene().getWindow()).close();
        } else if (drinkComboBox != null && drinkComboBox.getScene() != null) {
            ((Stage) drinkComboBox.getScene().getWindow()).close();
        } else if (sideComboBox != null && sideComboBox.getScene() != null) {
            ((Stage) sideComboBox.getScene().getWindow()).close();
        }
    }
}