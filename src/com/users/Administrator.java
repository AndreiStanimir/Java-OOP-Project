package com.users;

import com.entities.Agency;
import com.entities.Event;

public class Administrator extends User {
    public Administrator(String name, String password) {
        super(name, password);
    }

    public void CreateEvent(Agency a, Event e) {
        a.AddEvent(e);
    }

    public void addFunds(Agency a, float amount) {

        if (amount < 0)
            //TO DO
            return;
        a.setBudget(a.getBudget() + amount);
    }

    public void DeleteEvent(Agency a, Event e) {
        a.deleteEvent(e);
    }
}
