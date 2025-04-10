package test;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Burger class
 */
public class BurgerTest {

    /**
     * Test case 1: Single patty burger with cheese
     */
    @Test
    public void testSinglePattyBurgerWithCheese() {
        Burger burger = new Burger(Bread.BRIOCHE, false);
        burger.addAddOn(AddOns.CHEESE);

        // Base price: 6.99 + Cheese: 1.00 = 7.99
        assertEquals(7.99, burger.price(), 0.001);
    }

    /**
     * Test case 2: Double patty burger with multiple add-ons
     */
    @Test
    public void testDoublePattyBurgerWithMultipleAddOns() {
        Burger burger = new Burger(Bread.PRETZEL, true);
        burger.addAddOn(AddOns.LETTUCE);
        burger.addAddOn(AddOns.TOMATOES);
        burger.addAddOn(AddOns.ONIONS);
        burger.addAddOn(AddOns.CHEESE);

        // Base price: 6.99 + Double: 2.50 + Add-ons: 1.90 = 11.39
        assertEquals(11.39, burger.price(), 0.001);
    }
}
