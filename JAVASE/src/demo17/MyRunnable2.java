package demo17;

public class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            System.out.print("*");
        }
    }
}
