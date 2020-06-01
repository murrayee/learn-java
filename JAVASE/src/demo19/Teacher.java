package demo19;

public class Teacher implements Runnable{

    private  Print p;
    private String name;
    private int eScore,mScore,yScore;
    public Teacher(Print p,String name,int eScore,int mScore,int yScore){

        this.p=p;
        this.name=name;
        this.eScore=eScore;
        this.mScore=mScore;
        this.yScore=yScore;

    }



    public Print getP() {
        return p;
    }

    public void setP(Print p) {
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int geteScore() {
        return eScore;
    }

    public void seteScore(int eScore) {
        this.eScore = eScore;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getyScore() {
        return yScore;
    }

    public void setyScore(int yScore) {
        this.yScore = yScore;
    }
    @Override
    public void run() {

        synchronized (p){
            // 锁定需要同步的对象，使其同步执行
            try {
                p.printScore(name,eScore,mScore,yScore);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
