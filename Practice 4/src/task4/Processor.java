package task4;

public class Processor {

    private String model;
    private int cores;
    private double frequency;

    public Processor(String model, int cores, double frequency) {
        this.model = model;
        this.cores = cores;
        this.frequency = frequency;
    }

    public String getModel() {
        return model;
    }

    public int getCores() {
        return cores;
    }

    public double getFrequency() {
        return frequency;
    }

    public String toString() {
        return model + ", " + cores + " ядер, " + frequency + " ГГц";
    }
}
