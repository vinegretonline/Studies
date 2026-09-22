package task10;

public class Processor {

    private String model;
    private int cores;

    public Processor(String model, int cores) {
        this.model = model;
        this.cores = cores;
    }

    public String getModel() {
        return model;
    }

    public int getCores() {
        return cores;
    }

    public String toString() {
        return model + " (" + cores + " ядер)";
    }
}
