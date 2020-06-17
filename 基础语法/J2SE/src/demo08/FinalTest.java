package demo08;

public class FinalTest {
    public static  final  String SKIN_COLOR="黄色";
    public static final int EYE_COUNT=2;
    public static final  String EYE_COLOR="黑色";


}

class Tree{
    public final void develop(){
        System.out.println("'小树在生长'");
    }
}

class Oak extends Tree{
    public Oak(){
        System.out.println("初始化橡树");
    }

//    public void develop(){}  final方法不能改变

    public void  develop(String x){
        // 重载是可以的
    }
}
