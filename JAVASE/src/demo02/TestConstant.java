package demo02;

public class TestConstant {
    public static void main(String[] args) {
        final  int APPLE_PRiCE_PER_KM;
        int weightOfApple;
        int money;
        APPLE_PRiCE_PER_KM=3;
        weightOfApple=5;
        money=APPLE_PRiCE_PER_KM*weightOfApple;
        System.out.println("买苹果花了"+money+"元");
    }
}
