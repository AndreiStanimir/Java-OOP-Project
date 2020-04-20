package com.servicies;

import com.entities.Agency;
import com.entities.Concert;
import com.entities.Event;
import com.entities.Ticket;
import com.users.Administrator;
import com.users.Client;

import java.time.LocalDateTime;
import java.util.*;

public class Server {
    //map<pair<String,String>,User>
    static int eventId = 1;
    static int ticketId = 1;

    static ArrayList<Client> clients;
    static HashMap<String, Administrator> admins;

    public static int getNewEventId() {
        return ++eventId;
    }

    public static int getNewTicketId() {
        return ++ticketId;
    }

    private static void initServer() {
        clients = new ArrayList<Client>();
        admins = new HashMap<String, Administrator>();

        FileService.readFromFile(clients,Client.class,"clients.txt");
        FileService.readFromFile(admins,Administrator.class,"admins.txt");
        //FileService.readFromFile();
        //registerUser(new Administrator("Andrei", "123", 0));
        //registerUser(new Client("Ana", "123", 23, 12, new ArrayList<Ticket>()));

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

        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date, 5, 10, "Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(20), 2, 10, "Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(10), 5, 10, "Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(15), 5, 10, "Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(60), 5, 10, "Andreea"));
        a.CreateEvent(agency, new Concert("concert", 12345, "Bucharest", date.plusDays(5), 5, 10, "Andreea"));

        //Users searches for events
        LinkedList<Event> events = agency.getEventsByName("con");
        for (Event e : events) {
            System.out.println(e);
        }

        Client client = clients.get(0);

        client.buy_tickets(events.get(2), 3);

        client.setBalance(100);

        client.buy_tickets(events.get(2), 3);
        try {
            FileService.writeToFile(clients, Client.class, "clients.txt");
            //File_Reader.writeToFile(admins, Administrator.class,"admins.txt");
            FileService.writeToFile(agency, Agency.class, "agencies.txt");

            FileService.writeToFile(client.getBought_tickets(), Ticket.class, "tickets.txt");
            List<Ticket> tickets = new ArrayList<Ticket>();
            FileService.readFromFile(tickets, Ticket.class, "tickets.txt");
            for (var t : tickets) {
                System.out.println(t);
            }
            //System.out.println(ReadFile.toCSV(events, Event.class));
        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println(client.getBought_tickets());


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
        if (admins.containsKey(a.getName())) {
            return false;
        }
        admins.put(a.getName(), a);
        FileService.appendToFile(a, Administrator.class, "admins.txt");
        AuditService.addLogMessage("Register administrator "+ a.toString());
        return true;
    }

    public static boolean registerUser(Client c) {
        if (clients.stream().anyMatch(other -> c.getId() == other.getId()))
            return false;
        clients.add(c);
        FileService.appendToFile(c, Client.class, "clients.txt");
        AuditService.addLogMessage("Register client "+ c.toString());
        return true;
    }


//    public static User getUser(String user, String password) {
//
//    }
}
