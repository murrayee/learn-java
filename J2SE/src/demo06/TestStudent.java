package demo06;

public class TestStudent {
    public static void main(String[] args) {
        Student s=new Student();
        s.name="王大锤";
        s.stuno="2001";
        s.sex="男";
        s.age=3;
        System.out.println("姓名"+s.name);
        System.out.println("性别"+s.stuno);
        System.out.println("学号"+s.sex);
        System.out.println("年龄"+s.age);

        s.play();
        s.study();
    }
}
