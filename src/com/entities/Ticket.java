package com.entities;

public class Ticket {
    public static Ticket null_ticket;
    int id;
    Event event;

    public Ticket(int id, Event event) {
        this.id = id;
        this.event = event;
    }
    public float getPrice()
    {
        return event.getBaseTicketPrice();
    }
}
