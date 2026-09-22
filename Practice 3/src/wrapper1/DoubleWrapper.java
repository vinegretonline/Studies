package wrapper1;

public class DoubleWrapper {

    public static void main(String[] args) {
        Double d1 = Double.valueOf(3.14);
        Double d2 = Double.valueOf("2.71");

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);

        String text = "123.456";
        double fromString = Double.parseDouble(text);
        System.out.println("Строка \"" + text + "\" преобразована в double: " + fromString);

        Double value = Double.valueOf(97.99);
        byte b = value.byteValue();
        short s = value.shortValue();
        int i = value.intValue();
        long l = value.longValue();
        float f = value.floatValue();
        double d = value.doubleValue();

        System.out.println("Объект Double: " + value);
        System.out.println("byte:   " + b);
        System.out.println("short:  " + s);
        System.out.println("int:    " + i);
        System.out.println("long:   " + l);
        System.out.println("float:  " + f);
        System.out.println("double: " + d);

        String str = Double.toString(3.14);
        System.out.println("Литерал 3.14 как строка: " + str);
        System.out.println("Длина этой строки: " + str.length());
    }
}
