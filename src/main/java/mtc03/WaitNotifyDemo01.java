package mtc03;

class MyThread1 extends Thread{
    private Object lock;
    public MyThread1(Object lock) {
        this.lock = lock;
    }
    
    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("Start Wait   @ " + System.currentTimeMillis());
                lock.wait();
                System.out.println("  End Wait   @ " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread{
    private Object lock;
    public MyThread2(Object lock) {
        this.lock = lock;
    }
    
    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("Start Notify @ " + System.currentTimeMillis());
                lock.notify();
                System.out.println("  End Notify @ " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class WaitNotifyDemo01{
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            MyThread2 t1 = new MyThread2(lock);
            MyThread2 t2 = new MyThread2(lock);
            t1.start();
            Thread.sleep(3000);
            t2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}