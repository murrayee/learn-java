package demo13;

public class Outer {
    private  String out_string="外部私有字符串";


    public void useInner(){
        Inner in=new Inner();
        in.print();
    }
    class Inner{
        public void print(){
            System.out.println(out_string);
        }
    }
}
