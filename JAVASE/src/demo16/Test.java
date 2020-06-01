package demo16;

public class Test {
    public static void main(String[] args) {
        MyThread mt=new MyThread();
        Thread t=new Thread(mt);
        t.start();

        for (int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程"+i);
        }
    }
}
