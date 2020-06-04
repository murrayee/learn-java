package demo16;

public class MyThread implements Runnable {

    @Override
    public void run(){
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("My Thread"+i);
        }
    }
}
