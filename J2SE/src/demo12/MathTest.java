package demo12;

public class MathTest {
    public static void main(String[] args) {

        MTest mt=new MTest();
        System.out.println("使用引用对象调用方法");
        System.out.println("a+b="+mt.jia(15,3));
        System.out.println("a-b="+mt.jian(15,3));
        System.out.println("a/b="+mt.chu(15,3));
        System.out.println("a*b="+mt.cheng(15,3));
        System.out.println("使用接口类型引用调用方法");
        System.out.println("a+b="+ ((Jia) mt).jia(15,3));
        System.out.println("a+b="+ ((Jian) mt).jian(15,3));
        System.out.println("a+b="+ ((Chu) mt).chu(15,3));
        System.out.println("a+b="+ ((Cheng) mt).cheng(15,3));
    }
}


interface Jia {
    int jia(int a, int b);
}

interface Jian {
    int jian(int a, int b);
}

interface Cheng{
    int cheng(int a, int b);
}

interface  Chu{
    int chu(int a,int b);
}