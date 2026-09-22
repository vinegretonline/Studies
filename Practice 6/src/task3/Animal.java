package task3;

public class Animal implements Nameable {

    private String nickname;
    private String species;

    public Animal(String nickname, String species) {
        this.nickname = nickname;
        this.species = species;
    }

    public String getName() {
        return nickname + " (" + species + ")";
    }
}
