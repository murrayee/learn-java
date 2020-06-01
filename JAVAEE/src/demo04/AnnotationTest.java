package demo04;

import java.lang.reflect.Field;

public class AnnotationTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        Person p1 = new Person("Bob", "Chengdu", 10);
        Person p2 = new Person("", "Shanghai", 13);
        Person p3 = new Person("Alice", "Beijing", 300);

        for (Person p : new Person[]{p1, p2, p3}) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {
                System.out.println("Person " + p + " checked failed: " + e);
            }
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        for (Field field : person.getClass().getFields()) {
            Check check = (Check) field.getAnnotation(Check.class);
            if (check != null) {
                Object value = field.get(person);
                // TODO:
            }

        }
    }
}

class Person {
    @Check(min = 1, max = 20)
    public String name;

    @Check(max = 10)
    public String city;

    @Check(min = 1, max = 100)
    public int age;

    public Person(String name, String city, int age) {
        this.age = age;
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
    }
}