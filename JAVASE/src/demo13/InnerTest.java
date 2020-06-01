package demo13;

public class InnerTest {
    public static void main(String[] args) {
        Outer out =new Outer();
        out.useInner();
        Outer.Inner in =out.new Inner();
        in.print();
    }
}
