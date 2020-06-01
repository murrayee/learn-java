package demo04;

public class People {
    private String name;
    private String sex;
    private int age;

    void eat() {
        System.out.println("正在吃饭");
    }

    void tea() {
        System.out.println("正在喝茶");
    }

    public String run() {

        return "我在跑步";
    }

    public void eat(String type, int num) {
        System.out.println("正在吃" + type + "吃了" + num+"个");
    }

}
