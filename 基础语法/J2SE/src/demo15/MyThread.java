package demo15;

public class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }
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
