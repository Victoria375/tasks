package lesson1.task3;

public class Square extends Figure{

    private double length;

    Square(double length) {
        this.length = length;
    }

    @Override
    void area() {
        System.out.println("Square area = " + length*length);
    }

}
