package com.entities;

import java.time.LocalDateTime;

public class Movie extends Event {
    //to do
    String producer;

    public Movie(String name, float price, String location, LocalDateTime date, int seats, float baseTicket, String producer) {
        super(name, price, location, date, seats, baseTicket);
        this.producer = producer;
    }
}
