package com.entities;

import java.time.LocalDateTime;

public class Movie extends Event {
    //to do

    public Movie(String name, float price, String location, LocalDateTime date, int seats, float baseTicket) {
        super(name, price, location, date, seats, baseTicket);
    }
}
