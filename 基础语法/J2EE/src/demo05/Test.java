package demo05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hello");
        String first = (String) list.get(0);

        //
        List<String> l1 = new ArrayList<String>();

        l1.add("hello");
        String f1 = l1.get(0);

        //
        Person[] ps = new Person[]{
                new Person("Bob", 61),
                new Person("Alice", 44),
                new Person("Lily", 55),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }
}


interface Comparable<T> {
    int compareTo(T o);
}

class Person implements Comparable<Person> {

    String name;
    int score;

    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name + "," + this.score;
    }
}