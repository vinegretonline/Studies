public class Task1 {

    enum Season {

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

    public static void printSeason(Season season) {
        switch (season) {
            case ЗИМА:
                System.out.println("Я люблю зиму");
                break;
            case ВЕСНА:
                System.out.println("Я люблю весну");
                break;
            case ЛЕТО:
                System.out.println("Я люблю лето");
                break;
            case ОСЕНЬ:
                System.out.println("Я люблю осень");
                break;
        }
    }

    public static void main(String[] args) {
        Season favourite = Season.ЛЕТО;

        System.out.println("Любимое время года: " + favourite);
        System.out.println("Средняя температура: " + favourite.getAverageTemperature());
        System.out.println("Описание: " + favourite.getDescription());
        System.out.println("Номер в перечислении: " + favourite.ordinal());

        printSeason(favourite);
        printSeason(Season.ЗИМА);

        System.out.println("Все времена года:");
        for (Season season : Season.values()) {
            System.out.printf("%-6s средняя температура %4d, %s%n",
                    season, season.getAverageTemperature(), season.getDescription());
        }
    }
}
