package task1;

public enum Season {

    ЗИМА(-10) {
        public String getDescription() {
            return "Холодное время года";
        }
    },
    ВЕСНА(8),
    ЛЕТО(22) {
        public String getDescription() {
            return "Теплое время года";
        }
    },
    ОСЕНЬ(5);

    private int averageTemperature;

    Season(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public String getDescription() {
        return "Холодное время года";
    }
}
