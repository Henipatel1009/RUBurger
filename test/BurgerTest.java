package test;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Burger class.
 */
public class BurgerTest {

    /**
     * Test case 1: Single patty burger with no add-ons.
     * Expected price: 6.99
     */
    @Test
    public void testSinglePattyBurgerNoAddOns() {
        Burger burger = new Burger(Bread.BRIOCHE, false);
        assertEquals(6.99, burger.price(), 0.001);
    }

    /**
     * Test case 2: Double patty burger with cheese and onions.
     * Calculation:
     *   Base price (single patty) = 6.99;
     *   Double patty extra = +2.50, so base becomes 9.49;
     *   Add-ons: Cheese (1.00) and Onions (0.30);
     *   Total = 9.49 + 1.00 + 0.30 = 10.79.
     */
    @Test
    public void testDoublePattyBurgerWithAddOns() {
        Burger burger = new Burger(Bread.WHEAT_BREAD, true);
        burger.addAddOn(AddOns.CHEESE);
        burger.addAddOn(AddOns.ONIONS);
        assertEquals(10.79, burger.price(), 0.001);
    }
}