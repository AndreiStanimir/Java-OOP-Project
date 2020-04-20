package com.entities;

import com.servicies.AuditService;

import java.io.Serializable;
import java.util.*;

public class Agency implements Serializable {
    float budget;
    LinkedList<Event> events; // sorted by date
    public Agency() {
        events = new LinkedList<Event>();
        float budget = 0;
    }

    public LinkedList<Event> getEventsByName(String name) {
        LinkedList<Event> eventsMatch = new LinkedList<Event>();
        for (Event e : events) {
            if (e.getName().contains(name)) {
                eventsMatch.add(e);
            }
        }
        return eventsMatch;
    }

    public <T> LinkedList<T> getEventsByType(Class<T> type) { //test
        LinkedList<T> eventsMatch = new LinkedList<T>();
        for (Event e : events) {
            if (e.getClass() == type) {
                eventsMatch.add((T) e);
            }
        }
        return eventsMatch;
    }

    public void AddEvent(Event e) {
        if (events.size() == 0) {
            events.add(e);
            return;
        }
//        ListIterator<Event> it = events.listIterator(0);
//        while (it.hasNext()) {
//            if (it.hasPrevious()) {
//                //System.out.println(it.previous().toString()+ "<"+it.next().toString());
//
//                if (it.previous().getDate().compareTo(it.next().getDate()) <= 0) {
//                    it.add(e);
//                    //events.add(it.nextIndex(),e);
//
//                    return;
//                }
//            } else {
//                it.next();
//            }
//            ;
//        }

        for (int i = 0; i < events.size() - 1; i++) {
            if (e.getDate().compareTo(events.get(i).getDate()) <= 0) {
                events.add(i, e);
                return;
            }
        }
        events.addLast(e);
        AuditService.addLogMessage("Add event "+ e.toString());

    }

    public void deleteEvent(Event e) {
        events.remove(e);
        AuditService.addLogMessage("Remove event "+ e.toString());
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}
