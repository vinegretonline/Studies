public class Task1 {

    static class Author {

        private String name;
        private String email;
        private char gender;

        public Author(String name, String email, char gender) {
            this.name = name;
            this.email = email;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public char getGender() {
            return gender;
        }

        public String toString() {
            return "Author[name=" + name + ", email=" + email + ", gender=" + gender + "]";
        }
    }

    public static void main(String[] args) {
        Author a1 = new Author("Иван Петров", "petrov@mail.ru", 'm');
        Author a2 = new Author("Мария Волкова", "volkova@mail.ru", 'f');

        System.out.println(a1);
        System.out.println(a2);

        System.out.println("Имя: " + a1.getName());
        System.out.println("Почта: " + a1.getEmail());
        System.out.println("Пол: " + a1.getGender());

        a1.setEmail("newpetrov@mail.ru");
        System.out.println("После смены почты: " + a1);
    }
}
