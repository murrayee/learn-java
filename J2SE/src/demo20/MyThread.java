package demo20;

public class MyThread extends Thread {

    Resource r1;
    Resource r2;

    MyThread(Resource r1,Resource r2,String name){
        super(name);
        this.r1=r1;
        this.r2=r2;
    }

    public void  run(){
        synchronized (r1){
            System.out.println(this.getName()+"获得了"+r1.resourceName);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(this.getName()+"在等待"+r2.resourceName);
            synchronized (r2){
                System.out.println(this.getName()+"获得了"+r2.resourceName);
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
