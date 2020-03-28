package com.entities;

import java.util.Date;

public class Movie extends Event {


    public Movie(String name,float price, String location, Date date, int seats, float baseTicket) {
        super(name,price, location, date, seats, baseTicket);
    }
}
