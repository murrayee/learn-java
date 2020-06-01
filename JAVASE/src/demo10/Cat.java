package demo10;

public class Cat  extends Feline{

    @Override
    public void catchAnimal(Animal animal) {

        //  首先判断要哺食的animal是否为老师，如果是，可以捕食

        if(animal instanceof  Mouse){
            System.out.println("猫正在捕食老鼠");
        }else {
            System.out.println("猫不能捕食它");
        }

    }
}
