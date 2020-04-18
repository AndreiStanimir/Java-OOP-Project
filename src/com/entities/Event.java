package com.entities;

import com.servicies.Server;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class Event {
    String name;
    int id;
    float price; //hosting price
    String location;
    LocalDateTime date;
    int seats;
    float baseTicketPrice;
    ArrayList<Ticket> tickets;

    public Event(String name, float price, String location, LocalDateTime date, int seats, float baseTicketPrice) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.date = date;
        this.seats = seats;
        this.baseTicketPrice = baseTicketPrice;
        this.id = Server.getNewEventId();

        tickets = new ArrayList<Ticket>(seats);
        tickets.size();
        for (int i = 0; i < seats; i++) {
            //tickets.set(i,new Ticket(this));
            tickets.add(new Ticket(this));
        }
    }

    public int getId() {
        return id;
    }

//    public List<Ticket> getTickets(int tickets) {
//        return tickets;
//    }

    public List<Ticket> reserveTickets(int number) {
        List<Ticket> t = tickets.subList(tickets.size() - number, tickets.size());
        return t;
    }


    public float getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate() {
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

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", seats=" + seats +
                ", baseTicketPrice=" + baseTicketPrice +
                ", tickets=" + tickets +
                '}';
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setBaseTicketPrice(float baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }

}
