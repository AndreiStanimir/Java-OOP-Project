package com.servicies;

import com.users.Administrator;
import com.users.Client;
import com.users.User;

import java.util.HashMap;
import java.util.List;

public class Server {
    //map<pair<String,String>,User>
    static int eventId=1;
    static int ticketId=1;

    List<Client> clients;
    List<Administrator> administrators;

    public static int getEventId() {
        return ++eventId;
    }

    public static int getTicketId() {
        return ++ticketId;
    }
    private void initServer()
    {

    }
    private void closeServer()
    {
        
    }
    public static void main(String[] args) {

        registerUser("Andrei","1234");

    }
    public static boolean registerUser(String user, String password){

        return true;
    }
    public static User getUser(String user, String password)
    {

    }
}
