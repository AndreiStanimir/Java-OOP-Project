package com.entities;

import com.servicies.Server;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    //public static Ticket null_ticket;
    int id;
    LocalDateTime date;
    float price;

    public Ticket(Event event) {
        id = Server.getNewTicketId();
        date = event.date;
        price = event.baseTicketPrice;

    }

    public Ticket(int id, Event event) {
        this.id = id;
        date = event.date;
        price = event.baseTicketPrice;
    }

    public Ticket(int id, LocalDateTime date, float price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ticket{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
