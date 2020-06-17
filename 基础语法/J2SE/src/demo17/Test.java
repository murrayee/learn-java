package demo17;

public class Test {

    public static void main(String[] args) {
        MyRunnable1 r1=new MyRunnable1();
        MyRunnable2 r2=new MyRunnable2();

        Thread t1=new Thread(r1);
        Thread t2 =new Thread(r2);
        System.out.println("t1的优先级"+t1.getPriority());
        System.out.println("t2的优先级"+t2.getPriority());
        t1.setPriority(Thread.MAX_PRIORITY);
        System.out.println("t1的优先级"+t1.getPriority());
        t2.setPriority(Thread.MIN_PRIORITY);
        System.out.println("t2的优先级"+t2.getPriority());

        t1.start();
        t2.start();
    }
}
