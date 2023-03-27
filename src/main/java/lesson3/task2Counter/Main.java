package lesson3.task2Counter;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " counter = " + counter.getValue());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.decrement();
                System.out.println(Thread.currentThread().getName() + " counter = " + counter.getValue());
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.getValue());
    }
}
