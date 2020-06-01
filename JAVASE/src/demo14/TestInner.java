package demo14;

public class TestInner {
    public static void main(String[] args) {
        // 此处new为接口的实现类
        // outer为new出来的引用
        OuterI outer= new OuterI() {
            @Override
            public void show() {
                System.out.println("这是匿名内部类的show");
            }
        };
    }
}

interface  OuterI{
    void show();
}