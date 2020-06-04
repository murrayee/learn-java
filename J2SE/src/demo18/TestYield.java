package demo18;

public class TestYield {
    public static void main(String[] args) {
        Thread t1=new Thread(new MyRunnable1());
        Thread t2=new Thread(new MyRunnable2());
        t1.start();
        t2.start();
    }


}
class MyRunnable1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.print("+");
            Thread.yield();
        }
    }
}

class MyRunnable2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.print("*");
            Thread.yield();
        }
    }
}