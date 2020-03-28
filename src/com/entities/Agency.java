package com.entities;

import java.util.*;

public class Agency {
    List<Event> events;
    int budget;


    public List<Event> getEventsByName(String name)
    {
        List<Event> eventsMatch=new ArrayList<Event>();
        for (Event e: events) {
            if (e.getName().contains(name)){
                eventsMatch.add(e);
            }
        }
        return eventsMatch;
    }
    public void AddEvent(Event e)
    {
        events.add(e);
    }
}
