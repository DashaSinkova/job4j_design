package ru.job4j.collection;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

import java.util.List;

public class AnalyzeTest {
    @Test
    public void whenPreviousLargeThenCurrent() {
        List<Analize.User> previous =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(2, "Nastya"),
                new Analize.User(3, "Ivan"),
                new Analize.User(4, "Sasha"),
                new Analize.User(5, "Alex"),
                new Analize.User(6, "Elen"));
        List<Analize.User> current =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(3, "Ivand"),
                new Analize.User(10, "Elegjgn"),
                new Analize.User(5, "change"),
                new Analize.User(11, "change"));

        Analize.Info res = new Analize().diff(previous, current);
        assertThat(res.getAdded(), is(2));
        assertThat(res.getChanged(), is(2));
        assertThat(res.getDeleted(), is(3));
    }
    @Test
    public void whenPreviousSameAsCurrent() {
        List<Analize.User> previous =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(2, "Nastya"),
                new Analize.User(3, "Ivan"),
                new Analize.User(4, "Sasha"),
                new Analize.User(5, "Alex"),
                new Analize.User(6, "Elen"));
        List<Analize.User> current =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(2, "Nastya"),
                new Analize.User(3, "Ivan"),
                new Analize.User(4, "Sasha"),
                new Analize.User(5, "Alex"),
                new Analize.User(6, "Elen"));

        Analize.Info res = new Analize().diff(previous, current);
        assertThat(res.getAdded(), is(0));
        assertThat(res.getChanged(), is(0));
        assertThat(res.getDeleted(), is(0));
    }
    @Test
    public void whenCurrentLargeThenPrevious() {
        List<Analize.User> previous =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(2, "Nastya"),
                new Analize.User(3, "Ivan"),
                new Analize.User(6, "Elen"));
        List<Analize.User> current =  List.of(new Analize.User(1, "Dasha"),
                new Analize.User(3, "Ivana"),
                new Analize.User(4, "Sasha"),
                new Analize.User(5, "Alex"),
                new Analize.User(6, "Elen"));

        Analize.Info res = new Analize().diff(previous, current);
        assertThat(res.getAdded(), is(2));
        assertThat(res.getChanged(), is(1));
        assertThat(res.getDeleted(), is(1));
    }
}
