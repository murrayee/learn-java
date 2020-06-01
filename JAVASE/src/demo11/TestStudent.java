package demo11;

public class TestStudent {
    public static void main(String[] args) {
        Student1 st=new Student1();
        st.learn();
        st.eat();
        st.sleep();

//        st.banji=1;  接口自动加上 public static final 类型 常量

        System.out.println(st.banji);
        System.out.println(Student1.banji);
    }
}
