package ru.job4j.tdd;

import java.util.List;

public class Session3D implements Session {

    private List<Ticket> tickets;

    public Session3D(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public Session3D() {

    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
