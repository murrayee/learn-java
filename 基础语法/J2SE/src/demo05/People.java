package demo05;

public class People {
    String name;
    String sex;
    int age;

    People(){
        name=null;
        sex=null;
        age=0;
    }
    People(String name,String sex,int age ){
    this.name=name;
    this.sex=sex;
    this.age=age;
    }

    public static void main(String[] args) {
        People p= new People();
        System.out.println(p.name);
        System.out.println(p.sex);
        System.out.println(p.age);

        People p1= new People("李老师","男",30);
        System.out.println(p1.name);
        System.out.println(p1.sex);
        System.out.println(p1.age);
    }
}
