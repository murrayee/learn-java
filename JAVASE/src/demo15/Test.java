package demo15;

public class Test {
    public static void main(String[] args) {
        MyThread mt= new MyThread("线程1");
        mt.start();
        MyThread m2= new MyThread("线程2");
        m2.start();
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程"+i);
        }
        System.out.println("程序执行完毕");
    }
}
