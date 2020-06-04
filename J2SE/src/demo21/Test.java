package demo21;

public class Test {

    public static void main(String[] args) {
        Player p=new Player("小明",100);
        Game g =new Game(p);
        g.start();
    }
}
