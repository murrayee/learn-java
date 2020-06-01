package demo06;

import java.util.List;
import java.util.Objects;

public class EqualsTest {
    public static void main(String[] args) {
        List<String> list = List.of("A", "b", "c");
        System.out.println(list.contains("C"));
        System.out.println(list.indexOf("b"));

        List<Person> p = List.of(
                new Person("小明", 19),
                new Person("校长", 22),
                new Person("小心", 13)
        );

        boolean exist = p.contains(new Person("小心", 13));

        System.out.println(exist);
    }
}


class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Person) {
            Person p = (Person) o;

            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }
}