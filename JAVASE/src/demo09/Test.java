package demo09;

public class Test {
    public static void main(String[] args) {
        Animal a=new Animal();
        a.showMe();
        Animal t =new Tiger();
        t.showMe();
        Animal c=new Cat();
        c.showMe();
        Animal d=new Dog();
        d.showMe();
        showMe(d);
    }

    public static  void  showMe(Animal a){
        a.showMe();
    }
}
