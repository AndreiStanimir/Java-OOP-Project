package com.entities;

public class Concert extends Event {
    String name;

    public Concert(float price, String location, Date date, unsigned seats, Ticket baseTicket, String name) {
        super(price, location, date, seats, baseTicket);
        this.name = name;
    }
}
