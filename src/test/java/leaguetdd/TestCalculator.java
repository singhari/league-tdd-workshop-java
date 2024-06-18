package leaguetdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculator {

    // Test for the add method
    @Test
    public void testAdd() {
        assertEquals(5, Calculator.add(2, 3));
    }

    // Test for the factorial method
    @Test
    public void testFactorial() {
        assertEquals(120, Calculator.factorial(5));
        assertEquals(1, Calculator.factorial(0));
    }
}
