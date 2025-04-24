package model;

public abstract class MenuItem {
    protected int quantity;

    public abstract double price();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}