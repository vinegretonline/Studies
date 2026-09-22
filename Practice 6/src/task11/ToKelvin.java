package task11;

public class ToKelvin implements Convertable {

    public double convert(double celsius) {
        return celsius + 273.15;
    }

    public String getUnit() {
        return "K";
    }
}
