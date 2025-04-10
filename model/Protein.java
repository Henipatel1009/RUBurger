package model;

public enum Protein {
    ROAST_BEEF("Roast Beef", 10.99),
    SALMON("Salmon", 9.99),
    CHICKEN("Chicken", 8.99),
    BEEF_PATTY("Beef Patty", 6.99);

    private final String name;
    private final double price;

    Protein(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}