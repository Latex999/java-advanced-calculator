package com.calculator.core;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * Core calculation engine for the calculator application.
 * Handles all mathematical operations and expression evaluation.
 */
public class CalculatorEngine {
    
    // Constants
    private static final double PI = Math.PI;
    private static final double E = Math.E;
    
    // Memory storage
    private double memoryValue = 0.0;
    private final Map<String, Double> variables = new HashMap<>();
    
    /**
     * Constructor initializes the calculator engine.
     */
    public CalculatorEngine() {
        // Initialize variables with constants
        variables.put("pi", PI);
        variables.put("e", E);
    }
    
    /**
     * Evaluates a mathematical expression and returns the result.
     *
     * @param expression The expression to evaluate
     * @return The result of the evaluation
     * @throws IllegalArgumentException if the expression is invalid
     */
    public double evaluate(String expression) {
        // Replace UI symbols with operators that exp4j understands
        expression = expression.replace("×", "*")
                              .replace("÷", "/")
                              .replace("π", "pi")
                              .replace("^2", "^2")
                              .replace("√", "sqrt");
        
        try {
            // Build and evaluate the expression
            Expression exp = new ExpressionBuilder(expression)
                    .variables(variables.keySet())
                    .build()
                    .setVariables(variables);
            
            return exp.evaluate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression: " + expression, e);
        }
    }
    
    /**
     * Calculates the factorial of a number.
     *
     * @param n The number to calculate factorial for
     * @return The factorial result
     * @throws IllegalArgumentException if n is negative
     */
    public double factorial(double n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        
        // Check if n is an integer
        if (n == Math.floor(n)) {
            long result = 1;
            for (int i = 2; i <= (int) n; i++) {
                result *= i;
            }
            return result;
        } else {
            // For non-integer values, use Gamma function approximation
            return Math.exp(logGamma(n + 1));
        }
    }
    
    /**
     * Calculates the natural logarithm of the Gamma function.
     * Used for factorial calculation of non-integer values.
     *
     * @param x The input value
     * @return The log-gamma result
     */
    private double logGamma(double x) {
        // Lanczos approximation coefficients
        double[] p = {
            676.5203681218851, -1259.1392167224028, 771.32342877765313,
            -176.61502916214059, 12.507343278686905, -0.13857109526572012,
            9.9843695780195716e-6, 1.5056327351493116e-7
        };
        
        double result = 0.99999999999980993;
        double t = x - 1;
        
        for (int i = 0; i < p.length; i++) {
            result += p[i] / (t + i + 1);
        }
        
        double c = 7.5;
        return Math.log(Math.sqrt(2 * Math.PI)) + Math.log(result) - (x + 0.5) * Math.log(t + c + 0.5) + (t + 0.5);
    }
    
    /**
     * Calculates the sine of an angle.
     *
     * @param angle The angle
     * @param inDegrees Whether the angle is in degrees (true) or radians (false)
     * @return The sine result
     */
    public double sin(double angle, boolean inDegrees) {
        if (inDegrees) {
            angle = Math.toRadians(angle);
        }
        return Math.sin(angle);
    }
    
    /**
     * Calculates the cosine of an angle.
     *
     * @param angle The angle
     * @param inDegrees Whether the angle is in degrees (true) or radians (false)
     * @return The cosine result
     */
    public double cos(double angle, boolean inDegrees) {
        if (inDegrees) {
            angle = Math.toRadians(angle);
        }
        return Math.cos(angle);
    }
    
    /**
     * Calculates the tangent of an angle.
     *
     * @param angle The angle
     * @param inDegrees Whether the angle is in degrees (true) or radians (false)
     * @return The tangent result
     */
    public double tan(double angle, boolean inDegrees) {
        if (inDegrees) {
            angle = Math.toRadians(angle);
        }
        return Math.tan(angle);
    }
    
    /**
     * Calculates the natural logarithm (base e) of a number.
     *
     * @param x The input value
     * @return The natural logarithm result
     */
    public double ln(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers");
        }
        return Math.log(x);
    }
    
    /**
     * Calculates the base-10 logarithm of a number.
     *
     * @param x The input value
     * @return The base-10 logarithm result
     */
    public double log10(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers");
        }
        return Math.log10(x);
    }
    
    /**
     * Calculates the square root of a number.
     *
     * @param x The input value
     * @return The square root result
     */
    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Square root is not defined for negative numbers");
        }
        return Math.sqrt(x);
    }
    
    /**
     * Calculates x raised to the power of y.
     *
     * @param x The base
     * @param y The exponent
     * @return The power result
     */
    public double power(double x, double y) {
        return Math.pow(x, y);
    }
    
    /**
     * Stores a value in memory.
     *
     * @param value The value to store
     */
    public void setMemory(double value) {
        memoryValue = value;
    }
    
    /**
     * Retrieves the value stored in memory.
     *
     * @return The memory value
     */
    public double getMemory() {
        return memoryValue;
    }
    
    /**
     * Adds a value to the memory.
     *
     * @param value The value to add
     */
    public void addToMemory(double value) {
        memoryValue += value;
    }
    
    /**
     * Subtracts a value from the memory.
     *
     * @param value The value to subtract
     */
    public void subtractFromMemory(double value) {
        memoryValue -= value;
    }
    
    /**
     * Clears the memory value.
     */
    public void clearMemory() {
        memoryValue = 0.0;
    }
    
    /**
     * Converts a value from one unit to another.
     *
     * @param value The value to convert
     * @param fromUnit The source unit
     * @param toUnit The target unit
     * @return The converted value
     */
    public double convert(double value, String fromUnit, String toUnit) {
        // Implementation for various unit conversions
        // This is a simplified version that handles a few common conversions
        
        // Length conversions
        if (fromUnit.equals("m") && toUnit.equals("cm")) {
            return value * 100;
        } else if (fromUnit.equals("cm") && toUnit.equals("m")) {
            return value / 100;
        } else if (fromUnit.equals("m") && toUnit.equals("ft")) {
            return value * 3.28084;
        } else if (fromUnit.equals("ft") && toUnit.equals("m")) {
            return value / 3.28084;
        }
        
        // Weight conversions
        else if (fromUnit.equals("kg") && toUnit.equals("lb")) {
            return value * 2.20462;
        } else if (fromUnit.equals("lb") && toUnit.equals("kg")) {
            return value / 2.20462;
        }
        
        // Temperature conversions
        else if (fromUnit.equals("C") && toUnit.equals("F")) {
            return (value * 9/5) + 32;
        } else if (fromUnit.equals("F") && toUnit.equals("C")) {
            return (value - 32) * 5/9;
        } else if (fromUnit.equals("C") && toUnit.equals("K")) {
            return value + 273.15;
        } else if (fromUnit.equals("K") && toUnit.equals("C")) {
            return value - 273.15;
        }
        
        // If units are the same or conversion not supported
        else if (fromUnit.equals(toUnit)) {
            return value;
        } else {
            throw new IllegalArgumentException("Conversion from " + fromUnit + " to " + toUnit + " is not supported");
        }
    }
    
    /**
     * Performs bitwise operations for programmer mode.
     *
     * @param a The first operand
     * @param b The second operand
     * @param operation The bitwise operation to perform
     * @return The result of the bitwise operation
     */
    public long bitwiseOperation(long a, long b, BitwiseOperation operation) {
        switch (operation) {
            case AND:
                return a & b;
            case OR:
                return a | b;
            case XOR:
                return a ^ b;
            case NOT:
                return ~a;
            case LEFT_SHIFT:
                return a << b;
            case RIGHT_SHIFT:
                return a >> b;
            default:
                throw new IllegalArgumentException("Unsupported bitwise operation");
        }
    }
    
    /**
     * Converts a number between different number systems.
     *
     * @param value The value to convert
     * @param fromBase The source base (2 for binary, 8 for octal, 10 for decimal, 16 for hex)
     * @param toBase The target base
     * @return The converted value as a string
     */
    public String convertNumberSystem(String value, int fromBase, int toBase) {
        try {
            // Parse the input value according to the source base
            long decimalValue = Long.parseLong(value, fromBase);
            
            // Convert to the target base
            return Long.toString(decimalValue, toBase);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for the specified base", e);
        }
    }
    
    /**
     * Enum for bitwise operations in programmer mode.
     */
    public enum BitwiseOperation {
        AND, OR, XOR, NOT, LEFT_SHIFT, RIGHT_SHIFT
    }
}