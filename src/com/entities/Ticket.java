package com.entities;

import com.servicies.MainServices;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    //public static Ticket null_ticket;
    String id;
    float price;
    String client_id;
    String event_id;

    public Ticket(Event event) {
        id = MainServices.getNewTicketId();
        price = event.baseTicketPrice;
        client_id="0";
        event_id=event.getId();
    }

    public Ticket(String id, Event event) {
        this.id = id;
        price = event.baseTicketPrice;
        event_id=event.getId();
        client_id="0";
    }

//    public Ticket(String id, LocalDateTime date, float price) {
//        this.id = id;
//        this.date = date;
//        this.price = price;
//        client_id="0";
//    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ticket{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setPrice(float price) {
        this.price = price;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
