package com.entities;

import java.util.*;

public class Agency {
    LinkedList<Event> events; // sorted by date
    int budget;

    public Agency() {
        events = new LinkedList<Event>();
        int budget = 0;
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

        for (int i = 0; i < events.size()-1; i++) {
            if(e.getDate().compareTo(events.get(i).getDate())<=0){
                events.add(i,e);
                return;
            }
        }
        events.addLast(e);
    }
}
