package demo06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        List<Student> list = List.of(
                new Student("Bob", 78),
                new Student("Bob1", 68),
                new Student("Bob2", 48),
                new Student("Bob3", 28),
                new Student("Bob4", 18)
        );
        var holder = new Students(list);

        System.out.println(holder.getScore("Bob"));
        System.out.println(holder.getScore("Bob1"));
        System.out.println(holder.getScore("Bob3"));
    }

}

class Students {
    List<Student> list;
    Map<String, Integer> cache;

    public Students(List<Student> list) {
        this.list = list;
        cache = new HashMap<>();
    }

    int getScore(String name) {
        Integer score = this.cache.get(name);
        if (score == null) {
            score = this.findInList(name);
            cache.put(name, score);
        }
        return score == null ? -1 : score.intValue();
    }

    Integer findInList(String name) {
        for (var ss : this.list) {
            if (ss.name.equals(name)) {
                return ss.score;
            }
        }

        return null;
    }
}

class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}