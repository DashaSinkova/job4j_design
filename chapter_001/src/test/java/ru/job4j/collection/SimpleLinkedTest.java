package ru.job4j.collection;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedTest {
    @Test
    public void whenAddThenGet() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        assertThat(linked.get(0), is("first"));
    }
    @Test
    public void whenAddManyElements() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        linked.add("second");
        linked.add("third");
        linked.add("fourth");
        linked.add("fifth");
        assertThat(linked.get(2), is("third"));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.get(0);
    }
    @Test
    public void whenAddThenIt() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        String rsl = linked.iterator().next();
        assertThat(rsl, is("first"));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBound() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        linked.get(1);
    }
    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.iterator().next();
    }
    @Test
    public void whenHasNextFromEmptyIt() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        assertThat(linked.iterator().hasNext(), is(false));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        Iterator<String> it = linked.iterator();
        linked.add("second");
        it.next();
    }
    @Test
    public void whenGetLinkedLis() {
        SimpleLinked<String> linked = new SimpleLinked<>();
        linked.add("first");
        linked.add("second");
        linked.add("third");
        linked.add("fourth");
        linked.add("fifth");
        Iterator<String> iterator = linked.iterator();
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("third"));
        assertThat(iterator.next(), is("fourth"));
        assertThat(iterator.next(), is("fifth"));
        assertThat(iterator.next(), is("fourth"));
        assertThat(iterator.next(), is("third"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("third"));
    }

}
