package demo06;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;

public class EnumMapTest {
    public static void main(String[] args) {

        Map<DayOfWeek, String> wks = new EnumMap<>(DayOfWeek.class);
        wks.put(DayOfWeek.FRIDAY, "星期五");
        System.out.println(wks);
    }
}
