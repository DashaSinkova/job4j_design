package ru.job4j.collection;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.Matchers.greaterThan;

public class SimpleHashMapTest {
    @Test
    public void whenAddItem() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "27.02.1996");
        assertThat(map.get("Sinkova"), is("27.02.1996"));
    }
    @Test
    public void whenAddThenDelete() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "27.02.1996");
        assertThat(map.delete("Sinkova"), is(true));
        Iterator<String> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void whenAddGetDeleteGet() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "27.02.1996");
        assertThat(map.get("Sinkova"), is("27.02.1996"));
        Iterator<String> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        map.delete("Sinkova");
        assertThat(map.get("Sinkova"), is((String) null));
    }
    @Test
    public void whenAddSameEl() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "27.02.1996");
        assertThat(map.insert("Sinkova", "27.02.1996"), is(false));
    }
    @Test
    public void whenAddMoreThen8El() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "27.02.1996");
        map.insert("Smirnova", "27.02.1996");
        map.insert("Zhakaeva", "27.02.1996");
        map.insert("Goryacheva", "27.02.1996");
        map.insert("Kulakova", "27.02.1996");
        map.insert("Belova", "27.02.1996");
        map.insert("Kolesnikova", "27.02.1996");
        map.insert("Yarishev", "27.02.1996");
        map.insert("Ivoninskaya", "27.02.1996");
        System.out.println(map.getLength());
        assertThat(map.getLength(), greaterThan(8));
    }
    @Test
    public void whenGetElWithIt() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "Sinkova");
        map.insert("Smirnova", "Smirnova");
        map.insert("Zhakaeva", "Zhakaeva");
        Iterator<String> iterator = map.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoElInMap() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThenAddEl() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("Sinkova", "Sinkova");
        map.insert("Smirnova", "Smirnova");
        Iterator<String> iterator = map.iterator();
        map.insert("Zhakaeva", "Zhakaeva");
        iterator.next();
    }
}
