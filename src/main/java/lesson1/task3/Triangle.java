package lesson1.task3;

public class Triangle extends Figure{

    private double width;
    private double height;

    Triangle(double width, double height){
        this.width = width;
        this.height = height;
    }
    @Override
    void area(){
        System.out.println("Triangle area = " + (width*height)/2);
    }

}
