package model;

/**
 * Class representing a side
 */
public class Side extends MenuItem {
    private SideType type;
    private Size size;

    /**
     * Constructs a new side
     * @param type the type of side
     * @param size the size of the side
     */
    public Side(SideType type, Size size) {
        this.type = type;
        this.size = size;
        this.quantity = 1;
    }

    /**
     * Calculates the price of the side
     * @return the price
     */
    @Override
    public double price() {
        double basePrice = type.getBasePrice();

        // Add price based on size
        if (size == Size.MEDIUM) {
            basePrice += 0.50;
        } else if (size == Size.LARGE) {
            basePrice += 1.00;
        }

        return basePrice * quantity;
    }

    /**
     * Gets the type of side
     * @return the type
     */
    public SideType getType() {
        return type;
    }

    /**
     * Sets the type of side
     * @param type the type to set
     */
    public void setType(SideType type) {
        this.type = type;
    }

    /**
     * Gets the size of the side
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the side
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns a string representation of the side
     * @return the string representation
     */
    @Override
    public String toString() {
        // Example output: "FRIES [LARGE]"
        return type.name() + " [" + size.name() + "]";
    }
}