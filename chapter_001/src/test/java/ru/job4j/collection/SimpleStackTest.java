package ru.job4j.collection;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStackTest {
    @Test(expected = NoSuchElementException.class)
    public void whenMultiDelete() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(4);
        stack.push(10);
        stack.push(8);
        stack.pop();
        Iterator<Integer> iterator = stack.iterator();
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(10));
        iterator.next();
    }
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyStack() {
        SimpleStack<Integer> linked = new SimpleStack<>();
        assertThat(linked.pop(), is((Integer) null));
    }
    @Test
    public void whenDeleteLastThenPush() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(4);
        stack.push(10);
        assertThat(stack.pop(), is(10));
        stack.push(8);
        Iterator<Integer> iterator = stack.iterator();
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(8));
    }
    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }
}
