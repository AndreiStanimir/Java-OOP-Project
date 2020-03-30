package com.servicies;

import com.entities.Agency;
import com.entities.Concert;
import com.entities.Event;
import com.entities.Ticket;
import com.users.Administrator;
import com.users.Client;
import com.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Server {
    //map<pair<String,String>,User>
    static int eventId = 1;
    static int ticketId = 1;

    static ArrayList<Client> clients;
    static ArrayList<Administrator> admins;

    public static int getNewEventId() {
        return ++eventId;
    }

    public static int getNewTicketId() {
        return ++ticketId;
    }

    private static void initServer() {
        clients = new ArrayList<Client>();
        admins = new ArrayList<Administrator>();

        registerUser(new Administrator("Andrei"));
        registerUser(new Client("Ana", 23, 12, new ArrayList<Ticket>()));


    }

    private static void closeServer() {
        // TO DO
    }

    public static void main(String[] args) {
        initServer();

        Administrator a = admins.get(0);
        Agency agency = new Agency();
        LocalDateTime date = LocalDateTime.now();

        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date, 10, 10));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(20), 10, 10));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(10), 10, 10));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(15), 10, 10));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(60), 10, 10));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(5), 10, 10));

        LinkedList<Event> events = agency.getEventsByName("con");
        for (Event e : events) {
            System.out.println(e);
        }
        Client client = clients.get(0);

        client.buy_tickets(events.get(2), 3);

        client.setBalance(100);

        client.buy_tickets(events.get(2), 3);

//        System.out.println(client.getBought_tickets());


    }

    public static boolean registerUser(String user, String password, boolean admin) {
        //if user registered
//        if(admin)
//        {
//            administrators.add(new Administrator(user));
//        else
//        {
//            clients.add(new Client(user))
//        }
        return true;
    }

    public static boolean registerUser(Administrator a) {
        admins.add(a);
        return true;
    }

    public static boolean registerUser(Client c) {
        clients.add(c);
        return true;
    }

//    public static User getUser(String user, String password) {
//
//    }
}
