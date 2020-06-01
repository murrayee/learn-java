package demo19;


//synchronized 方法的对象获得锁也就是Source
public class Source {
    synchronized  void  method1() throws InterruptedException {
        System.out.println("进入method1方法，获得锁");
        Thread.sleep(1000);
        System.out.println("method1执行完毕，释放锁");

    }

    synchronized  void  method2() throws InterruptedException {
        System.out.println("进入method2方法，获得锁");
        Thread.sleep(1000);
        System.out.println("method2执行完毕，释放锁");

    }
}
