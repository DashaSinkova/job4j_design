package ru.job4j.kiss;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MaxMinTest {
    private List<Ticket> tickets = new ArrayList<>();

    @BeforeClass
    public void createdList() {
        tickets.add(new Ticket(3, "ticket"));
        tickets.add(new Ticket(10, "ticket"));
        tickets.add(new Ticket(1, "ticket"));
        tickets.add(new Ticket(6, "ticket"));
        tickets.add(new Ticket(0, "ticket"));
        tickets.add(new Ticket(50, "ticket"));
    }

    @Test
    public void whenFindMax() {
        Ticket max = new Ticket(126, "ticket");
        tickets.add(max);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(tickets, Comparator.comparing(Ticket::getId))).isEqualTo(max);
    }

    @Test
    public void whenFindMin() {
        Ticket min = new Ticket(0, "ticket");
        tickets.add(min);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(tickets, Comparator.comparing(Ticket::getId))).isEqualTo(min);
    }

    @Test
    public void whenListIsNull() {
        tickets.clear();
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(tickets, Comparator.comparing(Ticket::getId))).isNull();
        assertThat(maxMin.max(tickets, Comparator.comparing(Ticket::getId))).isNull();
    }
}

