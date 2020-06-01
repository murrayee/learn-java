package demo18;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt= new MyThread();
        mt.start();

        for (int i = 0; i < 100; i++) {

            if(i==5){
                    mt.join();
            }
            System.out.println("+++++++++");
        }
    }

}


class MyThread extends Thread{
    public void run(){
        for (int i = 0; i < 100; i++) {

            System.out.println("*******");

        }
    }
}