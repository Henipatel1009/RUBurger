package model;

public enum Flavor {
    COLA("Cola"),
    TEA("Tea"),
    JUICE("Juice"),
    LEMONADE("Lemonade"),
    ORANGE("Orange"),
    SPRITE("Sprite"),
    ROOT_BEER("Root Beer"),
    GINGER_ALE("Ginger Ale"),
    DIET_COLA("Diet Cola"),
    CHERRY_COLA("Cherry Cola"),
    VANILLA_COLA("Vanilla Cola"),
    GRAPE("Grape"),
    STRAWBERRY("Strawberry"),
    PEACH("Peach"),
    RASPBERRY("Raspberry"),
    ORANGE_SODA("Orange Soda"),
    SPARKLING_WATER("Sparkling Water"),
    ICED_COFFEE("Iced Coffee"),
    FRUIT_PUNCH("Fruit Punch"),
    GRAPE_SODA("Grape Soda"),
    CRANBERRY_JUICE("Cranberry Juice"),
    APPLE_JUICE("Apple Juice"),
    CHOCOLATE_MILK("Chocolate Milk"),
    STRAWBERRY_MILK("Strawberry Milk");

    private final String name;

    Flavor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}