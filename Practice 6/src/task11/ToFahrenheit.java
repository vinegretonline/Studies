package task11;

public class ToFahrenheit implements Convertable {

    public double convert(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public String getUnit() {
        return "F";
    }
}
