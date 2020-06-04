package demo01;

import java.util.Arrays;

public class MethodRefTest {
    public static void main(String[] args) {
        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array, MethodRefTest::cmp);
        System.out.println(String.join(", ", array));
    }
    static int cmp(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
