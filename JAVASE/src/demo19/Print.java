package demo19;


// 线程同步问题 以及解决方案
public class Print {
    /**
     *
     * @param name 学生名字
     * @param eScore 英语成绩
     * @param mScore 数学成绩
     * @param yScore 语文成绩
     */
    public synchronized void printScore(String name,int eScore,int mScore,int yScore) throws InterruptedException {
        System.out.println(name+"的英语成绩："+eScore);
        Thread.sleep(2000);
        System.out.println(name+"的数学成绩："+mScore);
        Thread.sleep(2000);
        System.out.println(name+"的语文成绩："+yScore);

    }
}
