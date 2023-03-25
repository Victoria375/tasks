package lesson2.task2ArrayList;

public class MyArrayList<T> {

    private final int DEFAULT_SIZE = 10;
    private Object[] arr = new Object[DEFAULT_SIZE];
    private int pointer = 0;

    public void add(T item) {
        if(pointer == arr.length-1)
            resize(arr.length*2);
        arr[pointer++] = item;
    }

    public T get(int index) {
        return (T) arr[index];
    }

    public void remove(int index) {
        for (int i = index; i < pointer; i++) {
            arr[i] = arr[i + 1];
        }
        arr[pointer] = null;
        pointer--;
    }

    public int size() {
        return pointer;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(arr, 0, newArray, 0, pointer);
        arr = newArray;
    }

}
