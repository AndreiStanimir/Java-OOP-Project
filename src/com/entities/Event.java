package com.entities;

import com.servicies.AuditService;
import com.servicies.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class Event implements Serializable {
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

    public void setId(int id) {
        this.id = id;
    }

    public List<Ticket> reserveTickets(int number) {
        List<Ticket> t = tickets.subList(tickets.size() - number, tickets.size());
        AuditService.addLogMessage("Reverserve " + number + " tickets");
        return t;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(float baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
