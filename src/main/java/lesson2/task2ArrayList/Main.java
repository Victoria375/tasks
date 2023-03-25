package lesson2.task2ArrayList;

public class Main {
    public static void main(String[] args) {

        MyArrayList<Integer> arr = new MyArrayList<>();

        arr.add(56);
        arr.add(5);
        arr.add(71);
        arr.add(3);
        arr.add(2);

        System.out.println("Array size = " + arr.size());
        arr.remove(2);
        System.out.println(arr.get(3));
        System.out.println("Array size = " + arr.size());

    }
}
