package demo10;

public abstract class Feline {
    private final int legCount=4 ;
    private final String headShape="round";
    private final boolean meatEater=true;


    public void cry(){
        System.out.println("动物在狂吼");
    }

    public abstract void catchAnimal(Animal animal);


}
