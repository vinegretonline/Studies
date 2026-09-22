package task3;

public enum Catalog {

    ТЕЛЕФОНЫ("Телефоны"),
    НОУТБУКИ("Ноутбуки"),
    НАУШНИКИ("Наушники");

    private String title;

    Catalog(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
