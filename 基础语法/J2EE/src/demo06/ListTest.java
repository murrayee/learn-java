package demo06;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        //给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字：


        final int start = 10;
        final int end = 20;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < end; i++) {

            list.add(i);

        }
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNUmber(start, end, list);
        System.out.println(list.toString());

        System.out.println("丢失的：" + removed + "" + "找到的：" + found);

    }

    static int findMissingNUmber(int start, int end, List<Integer> list) {

        List<Integer> refList = new ArrayList<>();

        for (int i = 0; i < end; i++) {
            refList.add(i);
        }

        for (Integer item : list) {
            refList.remove(item);
        }

        return refList.get(0);
    }
}
