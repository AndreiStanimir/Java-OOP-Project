package com.entities;
import java.time.LocalDateTime;public class Concert extends Event {
    String singer_name;
    public Concert(String name,float price, String location, LocalDateTime date, int seats, float baseTicket,String singer_name) {
        super(name,price, location, date, seats, baseTicket);
        this.singer_name=singer_name;
    }
}
