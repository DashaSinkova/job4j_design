package ru.job4j.collection;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
    @Test
    public void whenAddTwoSameObjects() {
        User first = new User("Sinkova", 0, new GregorianCalendar(1996, Calendar.FEBRUARY, 27));
        User second = new User("Sinkova", 0, new GregorianCalendar(1996, Calendar.FEBRUARY, 27));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
        System.out.println("HashCode for first: " + first.hashCode());
        System.out.println("HashCode for second: " + second.hashCode());
        System.out.println("Equals for second and first: " + first.equals(second));
    }
}
