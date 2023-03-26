package lesson3.task1PingPong;

public class PingPong implements Runnable {

    private final String word;
    private static final Object monitor = new Object();
    public static volatile PingPong lastStepped;

    PingPong(String word) {
        this.word = word;
    }

    public void run() {
        try {
            while (true) {
                synchronized (monitor) {
                    monitor.notifyAll();
                    while (lastStepped == this) monitor.wait();
                    lastStepped = this;
                    System.out.print(word + " ");
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        PingPong ping = new PingPong("ping");
        PingPong pong = new PingPong("PONG");

        PingPong.lastStepped = pong;

        new Thread(ping).start();
        new Thread(pong).start();
    }

}
