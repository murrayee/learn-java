package demo20;


public class Test {
    public static void main(String[] args) {
        Resource rs1=new Resource("资源1");
        Resource rs2=new Resource("资源2");
        Resource rs3=new Resource("资源3");

        MyThread t1=new MyThread(rs1,rs2,"线程1");
        MyThread t2=new MyThread(rs2,rs3,"线程2");
        MyThread t3=new MyThread(rs3,rs1,"线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
