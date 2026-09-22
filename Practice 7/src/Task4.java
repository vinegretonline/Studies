public class Task4 {

    interface MathCalculable {

        double PI = 3.141592653589793;

        double power(double base, int exponent);

        double complexModulus(double real, double imaginary);
    }

    static class MathFunc implements MathCalculable {

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

    public static void main(String[] args) {
        MathCalculable mc1 = new MathFunc();

        System.out.println("2 в степени 10 = " + mc1.power(2, 10));
        System.out.println("5 в степени 0 = " + mc1.power(5, 0));
        System.out.println("2 в степени -3 = " + mc1.power(2, -3));
        System.out.println("1.5 в степени 4 = " + mc1.power(1.5, 4));

        System.out.println("Модуль числа 3 + 4i = " + mc1.complexModulus(3, 4));
        System.out.println("Модуль числа -5 + 12i = " + mc1.complexModulus(-5, 12));

        System.out.println("Число PI из интерфейса: " + MathCalculable.PI);

        MathFunc func = new MathFunc();
        System.out.printf("Длина окружности радиуса 7: %.4f%n", func.circleLength(7));
        System.out.printf("Площадь круга радиуса 7: %.4f%n", func.circleArea(7));
    }
}
