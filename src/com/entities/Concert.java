package com.entities;

import java.time.LocalDateTime;

public class Concert extends Event {
    String singerName;

    public Concert(String name, float price, String location, LocalDateTime date, int seats, float baseTicket, String singer_name) {
        super(name, price, location, date, seats, baseTicket);
        this.singerName = singer_name;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singer_name) {
        this.singerName = singer_name;
    }
}
