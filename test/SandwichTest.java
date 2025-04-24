package test;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Sandwich class.
 */
public class SandwichTest {

    /**
     * Test case 1: Roast beef sandwich with no add-ons.
     * Expected price: 10.99
     */
    @Test
    public void testRoastBeefSandwichNoAddOns() {
        Sandwich sandwich = new Sandwich(Bread.BRIOCHE, Protein.ROAST_BEEF);
        assertEquals(10.99, sandwich.price(), 0.001);
    }

    /**
     * Test case 2: Chicken sandwich with lettuce and tomatoes.
     * Base price: 8.99 + Lettuce: 0.30 + Tomatoes: 0.30 = 9.59
     */
    @Test
    public void testChickenSandwichWithLettuceAndTomatoes() {
        Sandwich sandwich = new Sandwich(Bread.WHEAT_BREAD, Protein.CHICKEN);
        sandwich.addAddOn(AddOns.LETTUCE);
        sandwich.addAddOn(AddOns.TOMATOES);
        assertEquals(9.59, sandwich.price(), 0.001);
    }

    /**
     * Test case 3: Salmon sandwich with all add-ons and quantity 2.
     * Base price: 9.99 + (Lettuce: 0.30, Tomatoes: 0.30, Onions: 0.30, Avocado: 0.50, Cheese: 1.00) = 12.39
     * For quantity 2: 12.39 * 2 = 24.78
     */
    @Test
    public void testSalmonSandwichWithAllAddOnsQuantityTwo() {
        Sandwich sandwich = new Sandwich(Bread.SOURDOUGH, Protein.SALMON);
        sandwich.addAddOn(AddOns.LETTUCE);
        sandwich.addAddOn(AddOns.TOMATOES);
        sandwich.addAddOn(AddOns.ONIONS);
        sandwich.addAddOn(AddOns.AVOCADO);
        sandwich.addAddOn(AddOns.CHEESE);
        sandwich.setQuantity(2);
        assertEquals(24.78, sandwich.price(), 0.001);
    }
}