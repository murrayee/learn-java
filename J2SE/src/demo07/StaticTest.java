package demo07;

public class StaticTest {
    int commonint=0;
    static int staticint=0;

    StaticTest(int x){
    this.commonint=x;
    }

    static void printInt(int i){
        System.out.println(i);
    }
    public static void main(String[] args) {
        StaticTest s1=new StaticTest(1);
        StaticTest s2=new StaticTest(2);
        System.out.println(s1.commonint);
        System.out.println(s2.commonint);

        s1.staticint=1;
        System.out.println(s1.staticint);
        System.out.println(s2.staticint);
        s2.staticint=18;
        System.out.println(s1.staticint);
        System.out.println(s2.staticint);

        s1.printInt(2);
    }
}
