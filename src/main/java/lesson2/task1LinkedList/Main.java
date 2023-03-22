package lesson2.task1LinkedList;

public class Main {
    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList(7);
        list.insert(5);
        list.insert(45);
        list.insert(8);
        list.insert(77);
        list.insert(37);

        System.out.println("Length = " + list.getLength());
        System.out.println(list.getItemAt(2));
        System.out.println(list.remove(3));
        System.out.println("Length = " + list.getLength());
        list.insert(2);
        System.out.println("Length = " + list.getLength());

    }
}
