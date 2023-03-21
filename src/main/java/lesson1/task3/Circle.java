package lesson1.task3;

public class Circle extends Figure{

    private double radius;

    Circle(double radius){
        this.radius = radius;
    }
    @Override
    void area(){
        System.out.println("Circle area = " + 3.14*radius*radius);
    }

}
