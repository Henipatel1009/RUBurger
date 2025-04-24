package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing a sandwich.
 */
public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected List<AddOns> addOns;

    /**
     * Constructs a new Sandwich.
     * @param bread the type of bread
     * @param protein the type of protein
     */
    public Sandwich(Bread bread, Protein protein) {
        this.bread = bread;
        this.protein = protein;
        this.addOns = new ArrayList<>();
        this.quantity = 1;
    }

    /**
     * Calculates the price of the sandwich.
     * @return the total price.
     */
    @Override
    public double price() {
        double basePrice = protein.getPrice();
        double addOnsPrice = 0.0;
        for (AddOns addOn : addOns) {
            addOnsPrice += addOn.getPrice();
        }
        return (basePrice + addOnsPrice) * quantity;
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public List<AddOns> getAddOns() {
        return addOns;
    }

    /**
     * Adds an add-on if it isn't already included.
     * @param addOn the add-on to add.
     */
    public void addAddOn(AddOns addOn) {
        if (!addOns.contains(addOn)) {
            addOns.add(addOn);
        }
    }

    /**
     * Removes the specified add-on.
     * @param addOn the add-on to remove.
     */
    public void removeAddOn(AddOns addOn) {
        addOns.remove(addOn);
    }

    /**
     * Returns a descriptive string for this sandwich.
     * If add-ons are present, they are appended separated by commas.
     * If quantity is more than one, the quantity is appended as well.
     * @return the string representation of the sandwich.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(protein).append(" Sandwich on ").append(bread);

        if (!addOns.isEmpty()) {
            sb.append(" with ");
            String addOnsList = addOns.stream()
                    .map(AddOns::toString)
                    .collect(Collectors.joining(", "));
            sb.append(addOnsList);
        }

        if (quantity > 1) {
            sb.append(" (").append(quantity).append(")");
        }

        return sb.toString();
    }
}