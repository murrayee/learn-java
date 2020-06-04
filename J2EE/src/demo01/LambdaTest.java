package demo01;

import java.util.Arrays;

public class LambdaTest {
    public static void main(String[] args) {
        String[] array = new String[]{"Apple", "Orange", "Banana", "Lemon"};
        Arrays.sort(array, (o1, o2) -> {
            return o1.compareTo(o2);
        });
        System.out.println(String.join(", ", array));
    }

}

//@FunctionalInterface
//interface Callable<V> {
//    V call() throws Exception;
//}
