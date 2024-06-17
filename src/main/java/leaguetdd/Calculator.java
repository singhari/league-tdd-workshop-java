package leaguetdd;

//src/Calculator.java
public class Calculator {



 // Main method for running the application
// public static void main(String[] args) {
//     // Example usage of the methods
//     System.out.println("Addition of 2 and 3: " + add(2, 3));
//     System.out.println("Subtraction of 3 from 2: " + subtract(2, 3));
//     System.out.println("Factorial of 5: " + factorial(5));
// }
 // Method to add two integers
 public static int add(int a, int b) {
     return a + b;
 }

 // Method to subtract the second integer from the first
 public static int subtract(int a, int b) {
     return a - b;
 }

 // Method to calculate the factorial of a non-negative integer
 public static int factorial(int n) {
     if (n == 0) {
         return 1;
     } else {
         int result = 1;
         for (int i = 1; i <= n; i++) {
             result *= i;
         }
         return result;
     }
 }
 
 
}

