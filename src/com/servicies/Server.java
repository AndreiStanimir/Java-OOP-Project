package com.servicies;

import com.entities.Agency;
import com.entities.Concert;
import com.entities.Event;
import com.entities.Ticket;
import com.users.Administrator;
import com.users.Client;
import com.users.User;

import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;

public class Server {
    //map<pair<String,String>,User>
    static int eventId = 1;
    static int ticketId = 1;

    static ArrayList<Client> clients;
    static HashMap<String,Administrator> admins;

    public static int getNewEventId() {
        return ++eventId;
    }

    public static int getNewTicketId() {
        return ++ticketId;
    }

    private static void initServer() {
        clients = new ArrayList<Client>();
        admins = new HashMap<String, Administrator>();

        registerUser(new Administrator("Andrei"));
        registerUser(new Client("Ana", 23, 12, new ArrayList<Ticket>()));

        //read events from file
    }

    private static void closeServer() {
        // save all events to file
    }

    public static void main(String[] args) {
        initServer();

        // adm
        Administrator a = admins.get("Andrei");
        Agency agency = new Agency();
        LocalDateTime date = LocalDateTime.now();

        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date, 5, 10,"Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(20), 2, 10,"Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(10), 5, 10,"Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(15), 5, 10,"Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(60), 5, 10,"Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(5), 5, 10,"Andreea"));

        //Users searches for events
        LinkedList<Event> events = agency.getEventsByName("con");
        for (Event e : events) {
            System.out.println(e);
        }

        Client client = clients.get(0);

        client.buy_tickets(events.get(2), 3);

        client.setBalance(100);

        client.buy_tickets(events.get(2), 3);

        System.out.println(client.getBought_tickets());


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
        admins.put(a.getName(),a);
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
