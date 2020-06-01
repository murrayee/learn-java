package demo21;

import java.util.Scanner;

public class Game {
    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public void start() {
        System.out.println("开始游戏");
        System.out.println(player.getName() + "进入游戏，初始积分为：" + player.getScore());
        String[] arr = new String[]{"剪刀", "石头", "布"};
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入 石头 剪刀 布 其中一个，退出请输入exit");
            String s1 = sc.next(); // 玩家
            if(s1.equals("exit")){
                break;
            }
            System.out.println("玩家出：" + s1);
            int count = (int) (Math.random() * 3);//电脑
            String s2 = arr[count];
            System.out.println("电脑出：" + s2);
            int flag = rule(s1, s2);

            if (flag > 0) {
                player.setScore(player.getScore() + 10);
                System.out.println("赢了,积分+10,当前积分：" + player.getScore());
            }

            if (flag == 0) {
                System.out.println("平了,积分+0，当前积分：" + player.getScore());

            }

            if (flag < 0) {
                player.setScore(player.getScore() - 10);
                System.out.println("输了，积分-10，当前积分：" + player.getScore());
            }
        }
    }

    public int rule(String s1, String s2) {

        if (s1.equals(s2)) {
            return 0;
        }
        if (s1.equals("剪刀")) {
            if (s2.equals("石头")) {
                return -1;
            }
            if (s2.equals("布")) {
                return 1;
            }
        }
        if (s1.equals("石头")) {
            if (s2.equals("剪刀")) {
                return 1;
            }
            if (s2.equals("布")) {
                return -1;
            }
        }
        if (s1.equals("布")) {
            if (s2.equals("剪刀")) {
                return -1;
            }
            if (s2.equals("石头")) {
                return 1;
            }
        }
        return 0;
    }
}
