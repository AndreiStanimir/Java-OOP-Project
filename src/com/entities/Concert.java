package com.entities;
import java.time.LocalDateTime;public class Concert extends Event {
    public Concert(String name,float price, String location, LocalDateTime date, int seats, float baseTicket) {
        super(name,price, location, date, seats, baseTicket);
    }
}
