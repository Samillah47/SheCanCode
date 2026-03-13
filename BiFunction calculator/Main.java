import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> a / b;

        int num1 = 20;
        int num2 = 2;

        System.out.println("Addition: " + num1 + " + " + num2 + " = " + FunctionalCalc.calculator(num1, num2, add));
        System.out.println("Subtraction: " + num1 + " - " + num2 + " = " + FunctionalCalc.calculator(num1, num2, subtract));
        System.out.println("Multiplication: " + num1 + " * " + num2 + " = " + FunctionalCalc.calculator(num1, num2, multiply));
        System.out.println("Division: " + num1 + " / " + num2 + " = " + FunctionalCalc.calculator(num1, num2, divide));
    }
    
}
