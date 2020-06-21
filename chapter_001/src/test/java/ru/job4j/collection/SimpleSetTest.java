package ru.job4j.collection;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {
    @Test
    public void whenAddThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Dasha");
        set.add("Vera");
        set.add("Sveta");
        assertThat(set.add("Dasha"), is(false));
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("Dasha"));
    }
    @Test
    public void whenAddNullEl() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Dasha");
        set.add("Vera");
        assertThat(set.add(null), is(true));
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("Dasha"));
        assertThat(it.next(), is("Vera"));
        assertThat(it.next(), is((String) null));
    }
    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.iterator().next();
    }
    @Test
    public void whenItNotCorrupted() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Dasha");
        Iterator<String> it = set.iterator();
        set.add("Dasha");
        it.next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenItCorrupted() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Dasha");
        Iterator<String> it = set.iterator();
        set.add("Vera");
        it.next();
    }
}
