package ru.job4j.it;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.hamcrest.core.Is.*;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {
    @Test
    public void whenHasNextElement() {
        BackwardArrayIt it = new BackwardArrayIt(new int[]{1, 2, 3});
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(new int[]{});
        it.next();
    }
    @Test
    public void whenMultiCallHasNextIsTrue()  {
        ArrayIt arrayIt = new ArrayIt(new int[] {1, 2, 3});
        assertThat(arrayIt.hasNext(), is(true));
        assertThat(arrayIt.hasNext(), is(true));
    }
}
