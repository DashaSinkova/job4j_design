package ru.job4j.it;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
public class ArrayItTest {
    @Test
    public void whenMultiCallHasNextIsTrue()  {
        ArrayIt arrayIt = new ArrayIt(new int[] {1, 2, 3});
        assertThat(arrayIt.hasNext(), is(true));
        assertThat(arrayIt.hasNext(), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        ArrayIt arrayIt = new ArrayIt(new int[] {});
        arrayIt.next();
    }
    @Test
    public void whenHasNextElement() {
        ArrayIt arrayIt = new ArrayIt(new int[] {1, 2, 3});
        assertThat(arrayIt.next(), is(1));
        assertThat(arrayIt.next(), is(2));
        assertThat(arrayIt.next(), is(3));
    }
}
