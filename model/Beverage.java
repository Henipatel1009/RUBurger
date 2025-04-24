package model;

/**
 * Represents a beverage with a size and flavor.
 */
public class Beverage extends MenuItem {
    private Size size;
    private Flavor flavor;

    public Beverage(Size size, Flavor flavor) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = 1;
    }

    @Override
    public double price() {
        double basePrice;
        switch (size) {
            case SMALL:
                basePrice = 1.99;
                break;
            case MEDIUM:
                basePrice = 2.49;
                break;
            case LARGE:
                basePrice = 2.99;
                break;
            default:
                basePrice = 1.99;
        }
        return basePrice * quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return size + " " + flavor + (quantity > 1 ? " (" + quantity + ")" : "");
    }
}