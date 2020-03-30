package com.users;

import com.entities.Agency;
import com.entities.Event;
public class Administrator extends User {
    public Administrator(String name) {
        super(name);
    }

    public void CreateEvent(Agency a, Event e)
    {
        a.AddEvent(e);
    }

}
