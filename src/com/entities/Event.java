package com.entities;

import com.servicies.Server;

import java.util.Date;
import java.util.List;

public class Event {
    String name;
    int id;
    float price;
    String location;
    Date date;
    int seats;
    float baseTicketPrice;
    List<Ticket> tickets;
    public Event(String name, float price, String location, Date date, int seats, float baseTicketPrice) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.date = date;
        this.seats = seats;
        this.baseTicketPrice = baseTicketPrice;
        this.id = Server.getEventId();
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getTickets(int ) {
        return tickets;
    }

    public List<Ticket> reserveTickets(int number) {
        List<Tickets> t=tickets.subList(tickets.size()-number,tickets.size());

    }


    public float getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public float getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public String getName() {
        return name;
    }
}
