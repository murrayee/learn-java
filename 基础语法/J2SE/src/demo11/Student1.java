package demo11;

public class Student1 implements Student,People{
    @Override
    public void learn() {
        System.out.println("学生正在学习");
    }

    @Override
    public void eat() {
        System.out.println("学生正在吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("学生正在睡觉");
    }
}
