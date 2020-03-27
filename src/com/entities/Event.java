package com.entities;

import java.util.Date;

public abstract class Event {

    float price;
    String location;
    Date date;
    unsigned seats;
    Ticket baseTicket;

    public Event(float price, String location, Date date, unsigned seats, Ticket baseTicket) {
        this.price = price;
        this.location = location;
        this.date = date;
        this.seats = seats;
        this.baseTicket = baseTicket;
    }

}
