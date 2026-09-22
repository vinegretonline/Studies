package task4;

public class MathFuncTest {

    public static void main(String[] args) {
        MathCalculable mc1 = new MathFunc();

        // MathCalculable mc2 = new MathCalculable(); - error, you cannot create an instance of an interface

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
