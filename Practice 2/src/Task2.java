public class Task2 {

    static class Ball {

        private double x = 0.0;
        private double y = 0.0;

        public Ball(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Ball() {
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public void setXY(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void move(double xDisp, double yDisp) {
            x = x + xDisp;
            y = y + yDisp;
        }

        public String toString() {
            return "Ball[x=" + x + ", y=" + y + "]";
        }
    }

    public static void main(String[] args) {
        Ball b1 = new Ball(2.5, 4.0);
        Ball b2 = new Ball();

        System.out.println("Первый мяч: " + b1);
        System.out.println("Второй мяч: " + b2);

        b1.move(1.5, -2.0);
        System.out.println("После перемещения: " + b1);

        b2.setXY(10.0, 20.0);
        System.out.println("После setXY: " + b2);

        b2.setX(15.0);
        b2.setY(25.0);
        System.out.println("x = " + b2.getX() + ", y = " + b2.getY());
    }
}
