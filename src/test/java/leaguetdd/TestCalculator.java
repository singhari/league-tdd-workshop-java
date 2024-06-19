package leaguetdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TestCalculator {

    // For all 3 test methods you will create remember to add @Test before the method 
    public static void main(String[] args) {
        testAdd();
        testSubtract();
        testFactorial();
    }
    // Create a Test for the add method called testAdd 
    @Test
    public static void testAdd(){
        assertEquals(5,Calculator.add(2,3));
    }
    // Create a Test for the subtract method called testSubtract
    public static void testSubtract(){
        assertEquals(-1,Calculator.subtract(2,3));
    }
    // Create a Test for the factorial method called testFactorial
    public static void testFactorial(){
        assertEquals(120,Calculator.factorial(5));
        assertEquals(-1, Calculator.factorial(-5));
        assertEquals(1,Calculator.factorial(0));

    }
}
