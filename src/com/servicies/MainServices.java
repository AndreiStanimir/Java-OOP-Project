package com.servicies;

import com.Repository.AdminRepository;
import com.Repository.ClientRepository;
import com.entities.Ticket;
import com.entities.*;
import com.users.Administrator;
import com.users.Client;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.*;

public class MainServices {
    //map<pair<String,String>,User>
    //static int eventId = 1;
    //static int ticketId = 1;

    static ArrayList<Client> clients;
    static HashMap<String, Administrator> admins;

    public static String getNewEventId() {
        return UUID.randomUUID().toString();
    }

    public static String getNewTicketId() {
        return UUID.randomUUID().toString();
    }

    private static void initServer() {
        clients = new ArrayList<Client>();
        admins = new HashMap<String, Administrator>();
        Administrator admin=new Administrator("Andrei","1234",1);
        Client client=new Client("Ana", "123", 23, 12);
        registerUser(admin);
        registerUser(client);



        //FileService.readFromFile(clients, Client.class, "clients.csv");
        //FileService.readFromFile(admins, Administrator.class, "admins.csv");
        //FileService.readFromFile();
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
        List<Event> events=new ArrayList<>();
        events.add(new Concert("concert", 12345, "Bucharest", date.plusDays(20), 2, 10, "Andreea"));
        events.add(new Concert("concert", 12345, "Bucharest", date.plusDays(1), 2, 10, "Andreea"));
        events.add(new Concert("concert", 12345, "Bucharest", date.plusDays(50), 2, 10, "Andreea"));
        events.add(new Concert("concert", 12345, "Bucharest", date.plusDays(10), 2, 10, "Andreea"));
        events.add(new Concert("concert", 12345, "Bucharest", date.plusDays(20), 2, 10, "Andreea"));
        events.add(new Movie("movie1", 12345, "Bucharest", date.plusDays(20), 2, 10, "Disney"));
        events.add(new Movie("movie1", 12345, "Bucharest", date.plusDays(30), 2, 10, "Disney"));
        events.add(new Movie("movie1", 12345, "Bucharest", date.plusDays(250), 2, 10, "Disney"));

        FileService.writeToFile(events,Event.class,"events.csv");
        FileService.readFromFile(events,Event.class,"events.csv");

        System.out.println(events);

        //Users searches for events
        LinkedList<Event> events2 = agency.getEventsByName("con");
        for (Event e : events2) {
            System.out.println(e);
        }

        Client client = clients.get(0);

        client.buy_tickets(events2.get(2), 3);

        client.setBalance(100);

//        client.buy_tickets(events2.get(2), 3);
//        try {
//            FileService.writeToFile(clients, Client.class, "clients.csv");
//            //File_Reader.writeToFile(admins, Administrator.class,"admins.csv");
//            FileService.writeToFile(agency, Agency.class, "agencies.csv");
//
//            //FileService.writeToFile(client.getBought_tickets(), Ticket.class, "tickets.csv");
//            List<Ticket> tickets = new ArrayList<Ticket>();
//            FileService.readFromFile(tickets, Ticket.class, "tickets.csv");
//            for (var t : tickets) {
//                System.out.println(t);
//            }
//            //System.out.println(ReadFile.toCSV(events, Event.class));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
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
        AdminRepository.getInstance().insert(a);
        FileService.appendToFile(a, Administrator.class, "admins.csv");
        AuditService.addLogMessage("Register administrator " + a.toString());
        return true;
    }

    public static boolean registerUser(Client c) {
        if (clients.stream().anyMatch(other -> c.getId() == other.getId()))
            return false;
        clients.add(c);
        ClientRepository.getInstance().insert(c);
        FileService.appendToFile(c, Client.class, "clients.csv");
        AuditService.addLogMessage("Register client " + c.toString());
        return true;
    }


//    public static User getUser(String user, String password) {
//
//    }
}
