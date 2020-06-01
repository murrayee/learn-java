package demo19;

public class MyThread extends Thread{

    String name;
    Source s;

    MyThread(String name,Source s){
        this.name=name;
        this.s=s;
    }
    @Override
    public void run() {
        if("method1".equals(name)){
            try {
                s.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                s.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Source s= new Source();
        MyThread t1= new MyThread("method1",s);
        MyThread t2= new MyThread("method2",s);

        t1.start();
        t2.start();
    }
}
