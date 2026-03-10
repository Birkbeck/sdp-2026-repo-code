package threads;

public class IllegalMonitorStateExceptionDemo {

    //This object is used for synchronisation among threads.
    private final static Object obj = new Object();

    public static void main(String[] args) {
        Thread.ofPlatform().start(() -> waitingThread());
        Thread.ofPlatform().start(() -> notifyingThread());
    }

    public static void waitingThread() {
        try {
            System.out.println(Thread.currentThread().getName() + " starts waiting");
            obj.wait();
            System.out.println(Thread.currentThread().getName() + " ends waiting");
        }
        catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName() + " gets interrupted");
        }
    }

    public static void notifyingThread() {
        try {
            // Thread sleep for 5 sec
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " notifies");
            obj.notifyAll();
        }
        catch (InterruptedException ex) {
            System.err.println(Thread.currentThread().getName() + " gets interrupted");
        }
    }
}
