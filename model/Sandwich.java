// model/Sandwich.java
package model;

import java.util.ArrayList;

/**
 * Class representing a sandwich
 */
public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    /**
     * Constructs a new sandwich
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
     * Calculates the price of the sandwich
     * @return the price
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

    /**
     * Gets the bread type
     * @return the bread
     */
    public Bread getBread() {
        return bread;
    }

    /**
     * Sets the bread type
     * @param bread the bread to set
     */
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /**
     * Gets the protein type
     * @return the protein
     */
    public Protein getProtein() {
        return protein;
    }

    /**
     * Sets the protein type
     * @param protein the protein to set
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    /**
     * Gets the add-ons
     * @return the list of add-ons
     */
    public ArrayList<AddOns> getAddOns() {
        return addOns;
    }

    /**
     * Adds an add-on
     * @param addOn the add-on to add
     */
    public void addAddOn(AddOns addOn) {
        if (!addOns.contains(addOn)) {
            addOns.add(addOn);
        }
    }

    /**
     * Removes an add-on
     * @param addOn the add-on to remove
     */
    public void removeAddOn(AddOns addOn) {
        addOns.remove(addOn);
    }

    /**
     * Returns a string representation of the sandwich
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(protein).append(" Sandwich on ").append(bread);

        if (!addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i));
                if (i < addOns.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        if (quantity > 1) {
            sb.append(" (").append(quantity).append(")");
        }

        return sb.toString();
    }

}