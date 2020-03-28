package com.users;

import com.entities.Event;
import com.entities.Ticket;

import java.util.List;

public class Client extends User {
    float balance;
    int age;
    List<Ticket> bought_tickets;

    public void buy_ticket(Event e) {
        if (price >= balance) {
            balance -= price
        }
    }

    public List<Ticket> buy_tickets(Event event, int number) throws Exception {
        float price = event.getBaseTicketPrice() * number;

        if (balance < price)
            throw new UnsupportedOperationException("Not enough money");
        bought_tickets.addAll(event.reserveTickets(number)); // to test
        balance -= price;

//            catch (IndexOutOfBoundsException e)
//            {
//                System.err.println(e);
//            }


    }
}
