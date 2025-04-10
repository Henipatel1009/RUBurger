package model;

public enum SideType {
    CHIPS("Chips", 1.99),
    FRIES("Fries", 2.49),
    ONION_RINGS("Onion Rings", 3.29),
    APPLE_SLICES("Apple Slices", 1.29);

    private final String name;
    private final double basePrice;

    SideType(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
    @Override
    public String toString() {
        switch(this) {
            case CHIPS:
                return "Chips";
            case APPLE_SLICES:
                return "Apple Slices";
            default:
                return name();
        }
    }
}
