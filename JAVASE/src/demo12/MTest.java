package demo12;

public class MTest implements Jia,Jian,Cheng,Chu {
    @Override
    public int jia(int a, int b) {
        return a+b;
    }

    @Override
    public int jian(int a, int b) {
        return a-b;
    }

    @Override
    public int cheng(int a, int b) {
        return a*b;
    }

    @Override
    public int chu(int a, int b) {
        return a/b;
    }
}
