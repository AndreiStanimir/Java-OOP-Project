package com.entities;

import com.servicies.Server;

import java.time.LocalDateTime;
import java.time.LocalDateTime;public class Ticket {
    public static Ticket null_ticket;
    int id;
    LocalDateTime date;
    float price;
    public Ticket(Event event) {
        id= Server.getNewTicketId();
        date=event.date;
        price=event.baseTicketPrice;

    }
    public Ticket(int id, Event event) {
        this.id = id;
        date=event.date;
        price=event.baseTicketPrice;
    }
    public float getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                "}\n";
    }
}
