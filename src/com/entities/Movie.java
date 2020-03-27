package com.entities;

import java.util.Date;

public class Movie extends Event {
    String name;

    public Movie(float price, String location, Date date, unsigned seats, Ticket baseTicket, String name) {
        super(price, location, date, seats, baseTicket);
        this.name = name;
    }
}
