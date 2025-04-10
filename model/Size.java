package model;

public enum Size {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 0.5),
    LARGE("Large", 1.0);

    private final String name;
    private final double priceAdjustment;

    Size(String name, double priceAdjustment) {
        this.name = name;
        this.priceAdjustment = priceAdjustment;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }

    @Override
    public String toString() {
        return name;
    }
}
