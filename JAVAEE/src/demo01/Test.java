package demo01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {
        // Comparator 匿名类实现排序
        String[] array = {"Apple", "Orange", "Banana", "Lemon"};

        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(String.join(", ", array));

        // 如果要把一个List<String>转换为List<Person>，应该怎么办？
        //传统的做法是先定义一个ArrayList<Person>，然后用for循环填充这个List：
        List<String> names = List.of("Bob", "Alice", "Tim");
        List<Person> persons = new ArrayList<>();
        for (String name : names) {
            persons.add(new Person(name));
        }
        System.out.println(persons);
        // 要更简单地实现String到Person的转换，我们可以引用Person的构造方法：
        List<String> names1 = List.of("Bob", "Alice", "Tim");
        List<Person> persons1 = names1.stream().map(Person::new).collect(Collectors.toList());
        System.out.println(persons1);
    }
}
