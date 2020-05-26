package com.users;

import com.entities.Event;
import com.entities.Ticket;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.List;

public class Client extends User implements Serializable {
    float balance;
    int age;
    List<Ticket> bought_tickets;

    public Client(String id, String name, String password, float balance, int age) {
        super(id, name, password);
        this.balance = balance;
        this.age = age;
    }
    public Client(String name, String password, float balance, int age) {
        super(name, password);
        this.balance = balance;
        this.age = age;

    }

    public void buy_ticket(Event e) {
        buy_tickets(e, 1);
    }

    public void buy_tickets(Event event, int number) {
        float price = event.getBaseTicketPrice() * number;
        try {
            if (number <= 0)
                throw new InvalidParameterException("Ticket number must be positive ");
            if (balance < price)
                throw new UnsupportedOperationException("Not enough money");
            bought_tickets.addAll(event.reserveTickets(number)); // to test
            balance -= price;

//            catch (IndexOutOfBoundsException e)
//            {
//                System.err.println(e);
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void refund_ticket(Ticket t) {

    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "balance=" + balance +
                ", age=" + age +
                ", bought_tickets=" + bought_tickets +
                ", name='" + name + '\'' +
                "}\n";
    }

}
