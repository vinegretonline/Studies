public class Task15 {

    public static void main(String[] args) {
        Task12.Shape s1 = new Task12.Circle(5.5, "RED", false);

        System.out.println(s1);
        System.out.println(s1.getArea());
        System.out.println(s1.getPerimeter());
        System.out.println(s1.getColor());
        System.out.println(s1.isFilled());

        Task12.Circle c1 = (Task12.Circle) s1;
        System.out.println(c1);
        System.out.println(c1.getArea());
        System.out.println(c1.getPerimeter());
        System.out.println(c1.getColor());
        System.out.println(c1.isFilled());
        System.out.println(c1.getRadius());

        Task12.Shape s3 = new Task12.Rectangle(1.0, 2.0, "RED", false);
        System.out.println(s3);
        System.out.println(s3.getArea());
        System.out.println(s3.getPerimeter());
        System.out.println(s3.getColor());

        Task12.Rectangle r1 = (Task12.Rectangle) s3;
        System.out.println(r1.getLength());
        System.out.println(r1.getWidth());
    }
}
