package com.entities;
import java.util.Date;
public class Concert extends Event {
    public Concert(String name,float price, String location, Date date, int seats, float baseTicket) {
        super(name,price, location, date, seats, baseTicket);
    }
}
