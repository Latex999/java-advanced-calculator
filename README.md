# Advanced Java Calculator

A feature-rich calculator application built in Java with a modern JavaFX GUI. This calculator goes beyond basic arithmetic operations to provide a comprehensive calculation tool.

## Features

- **Basic Operations**: Addition, subtraction, multiplication, division
- **Scientific Functions**: 
  - Trigonometric functions (sin, cos, tan, etc.)
  - Logarithmic functions (log, ln)
  - Exponential functions
  - Square root and nth root
  - Factorial calculations
- **Memory Functions**: Store and recall values
- **Unit Conversions**:
  - Length (meters, feet, inches, etc.)
  - Weight (kg, pounds, etc.)
  - Temperature (Celsius, Fahrenheit, Kelvin)
  - Area and Volume
- **Expression Parsing**: Enter complex mathematical expressions that are evaluated according to order of operations
- **History Tracking**: View and recall previous calculations
- **Customizable Interface**: Light/dark mode and adjustable precision
- **Programmer Mode**: Binary, octal, and hexadecimal calculations with bitwise operations

## Requirements

- Java 17 or higher
- Maven for dependency management

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/Latex999/java-advanced-calculator.git
   ```

2. Navigate to the project directory:
   ```
   cd java-advanced-calculator
   ```

3. Build the project with Maven:
   ```
   mvn clean package
   ```

4. Run the application:
   ```
   java -jar target/java-advanced-calculator-1.0-SNAPSHOT.jar
   ```

## Project Structure

- `src/main/java/com/calculator/` - Main source code
  - `core/` - Core calculation engine
  - `model/` - Data models
  - `service/` - Services for different calculator functions
  - `ui/` - JavaFX user interface components
  - `util/` - Utility classes
- `src/test/` - Unit and integration tests

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.