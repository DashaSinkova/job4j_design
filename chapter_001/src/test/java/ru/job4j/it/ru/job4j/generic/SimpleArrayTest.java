package ru.job4j.it.ru.job4j.generic;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import ru.job4j.generic.SimpleArray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddValue() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        arr.add(5);
        assertThat(arr.get(0), is(5));
    }

    @Test
    public void whenSetNewValue() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(4);
        arr.add(3);
        arr.add(3);
        assertThat(arr.get(2), is(3));
        arr.set(2, 35);
        assertThat(arr.get(2), is(35));
    }

    @Test
    public void whenRemoveValue() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(4);
        arr.add(3);
        arr.add(5);
        assertThat(arr.get(1), is(3));
        arr.remove(1);
        assertThat(arr.get(1), is(5));
        arr.remove(0);
        assertThat(arr.get(0), is(5));
        arr.add(10);
        assertThat(arr.get(1), is(10));
        arr.add(15);
        assertThat(arr.get(2), is(15));
    }

    @Test
    public void whenGetValue() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(4);
        arr.add(3);
        assertThat(arr.get(1), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNextElement() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(4);
        arr.add(3);
        Iterator<Integer> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(3));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        Iterator<Integer> it = arr.iterator();
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
