package com.calculator.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CalculatorEngine class.
 */
public class CalculatorEngineTest {
    
    private CalculatorEngine calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new CalculatorEngine();
    }
    
    @Test
    public void testBasicArithmetic() {
        // Addition
        assertEquals(5, calculator.evaluate("2+3"), 0.0001);
        
        // Subtraction
        assertEquals(7, calculator.evaluate("10-3"), 0.0001);
        
        // Multiplication
        assertEquals(24, calculator.evaluate("6×4"), 0.0001);
        
        // Division
        assertEquals(5, calculator.evaluate("10÷2"), 0.0001);
        
        // Mixed operations
        assertEquals(14, calculator.evaluate("2+3×4"), 0.0001);
        assertEquals(20, calculator.evaluate("(2+3)×4"), 0.0001);
    }
    
    @Test
    public void testScientificFunctions() {
        // Square root
        assertEquals(3, calculator.sqrt(9), 0.0001);
        
        // Power
        assertEquals(8, calculator.power(2, 3), 0.0001);
        
        // Logarithms
        assertEquals(1, calculator.log10(10), 0.0001);
        assertEquals(Math.log(10), calculator.ln(10), 0.0001);
        
        // Trigonometric functions
        assertEquals(0, calculator.sin(0, false), 0.0001);
        assertEquals(1, calculator.sin(90, true), 0.0001);
        assertEquals(1, calculator.cos(0, false), 0.0001);
        assertEquals(0, calculator.cos(90, true), 0.0001);
    }
    
    @Test
    public void testFactorial() {
        assertEquals(1, calculator.factorial(0), 0.0001);
        assertEquals(1, calculator.factorial(1), 0.0001);
        assertEquals(120, calculator.factorial(5), 0.0001);
        
        // Test for exception with negative input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.factorial(-1);
        });
        
        assertTrue(exception.getMessage().contains("Factorial is not defined for negative numbers"));
    }
    
    @Test
    public void testMemoryFunctions() {
        // Test memory storage and retrieval
        calculator.setMemory(10);
        assertEquals(10, calculator.getMemory(), 0.0001);
        
        // Test memory addition
        calculator.addToMemory(5);
        assertEquals(15, calculator.getMemory(), 0.0001);
        
        // Test memory subtraction
        calculator.subtractFromMemory(7);
        assertEquals(8, calculator.getMemory(), 0.0001);
        
        // Test memory clear
        calculator.clearMemory();
        assertEquals(0, calculator.getMemory(), 0.0001);
    }
    
    @Test
    public void testUnitConversions() {
        // Length conversions
        assertEquals(100, calculator.convert(1, "m", "cm"), 0.0001);
        assertEquals(0.01, calculator.convert(1, "cm", "m"), 0.0001);
        assertEquals(3.28084, calculator.convert(1, "m", "ft"), 0.0001);
        assertEquals(0.3048, calculator.convert(1, "ft", "m"), 0.0001);
        
        // Weight conversions
        assertEquals(2.20462, calculator.convert(1, "kg", "lb"), 0.0001);
        assertEquals(0.453592, calculator.convert(1, "lb", "kg"), 0.0001);
        
        // Temperature conversions
        assertEquals(32, calculator.convert(0, "C", "F"), 0.0001);
        assertEquals(0, calculator.convert(32, "F", "C"), 0.0001);
        assertEquals(273.15, calculator.convert(0, "C", "K"), 0.0001);
        assertEquals(0, calculator.convert(273.15, "K", "C"), 0.0001);
        
        // Same unit conversion
        assertEquals(10, calculator.convert(10, "m", "m"), 0.0001);
        
        // Test for exception with unsupported conversion
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.convert(1, "m", "kg");
        });
        
        assertTrue(exception.getMessage().contains("Conversion from m to kg is not supported"));
    }
    
    @Test
    public void testBitwiseOperations() {
        // AND operation
        assertEquals(2, calculator.bitwiseOperation(6, 3, CalculatorEngine.BitwiseOperation.AND));
        
        // OR operation
        assertEquals(7, calculator.bitwiseOperation(6, 3, CalculatorEngine.BitwiseOperation.OR));
        
        // XOR operation
        assertEquals(5, calculator.bitwiseOperation(6, 3, CalculatorEngine.BitwiseOperation.XOR));
        
        // NOT operation
        assertEquals(~6, calculator.bitwiseOperation(6, 0, CalculatorEngine.BitwiseOperation.NOT));
        
        // Left shift
        assertEquals(24, calculator.bitwiseOperation(6, 2, CalculatorEngine.BitwiseOperation.LEFT_SHIFT));
        
        // Right shift
        assertEquals(1, calculator.bitwiseOperation(6, 2, CalculatorEngine.BitwiseOperation.RIGHT_SHIFT));
    }
    
    @Test
    public void testNumberSystemConversion() {
        // Binary to decimal
        assertEquals("10", calculator.convertNumberSystem("1010", 2, 10));
        
        // Decimal to binary
        assertEquals("1010", calculator.convertNumberSystem("10", 10, 2));
        
        // Decimal to hexadecimal
        assertEquals("a", calculator.convertNumberSystem("10", 10, 16));
        
        // Hexadecimal to decimal
        assertEquals("16", calculator.convertNumberSystem("10", 16, 10));
        
        // Octal to binary
        assertEquals("1000", calculator.convertNumberSystem("10", 8, 2));
        
        // Test for exception with invalid number format
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.convertNumberSystem("G", 16, 10);
        });
        
        assertTrue(exception.getMessage().contains("Invalid number format for the specified base"));
    }
}