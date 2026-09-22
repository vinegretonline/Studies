package task4;

public class MathFunc implements MathCalculable {

    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        double result = 1;
        int steps = exponent;
        if (steps < 0) {
            steps = -steps;
        }

        for (int i = 0; i < steps; i++) {
            result = result * base;
        }

        if (exponent < 0) {
            return 1 / result;
        }
        return result;
    }

    public double complexModulus(double real, double imaginary) {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public double circleLength(double radius) {
        return 2 * PI * radius;
    }

    public double circleArea(double radius) {
        return PI * power(radius, 2);
    }
}
