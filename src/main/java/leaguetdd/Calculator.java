package leaguetdd;

public class Calculator {

    // Main method for running the application
    public static void main(String[] args) {
        // Example usage of the methods: Uncomment after the methods are created
        System.out.println("Addition of 2 and 3: " + add(2, 3));
        System.out.println("Subtraction of 3 from 2: " + subtract(2, 3));
        System.out.println("Factorial of 5: " + factorial(5));
    }

    // For all 3 methods you will create they should be pulic and static and return an integer
    
    // Create a Method to add two integers called add
    public static int add(int a, int b){
        return (a+b);
    }

    // Create a Method to subtract the second integer from the first integer called subtract
    public static int subtract(int a, int b){
        return (a-b);
    }

    // Create a Method to calculate the factorial of a non-negative integer called factorial
    public static int factorial(int b){
        int c = 1;
        if(b >= 0){
         for(int i= 1; i < b+1; i++){
            c *= i;
         }        
        }
        else{
            c =-1;
        }
        return c;
    }

}
