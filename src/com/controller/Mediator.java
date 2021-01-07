package com.controller;

import java.util.ArrayList;
import java.util.HashMap;

public class Mediator
{
    private HashMap<Event, ArrayList<Observer>> observers;

    public Mediator()
    {
        observers = new HashMap<>();
    }

    public void action(Event event)
    {
        ArrayList<Observer> eventObservers = observers.get(event);

        for (Observer o : eventObservers)
        {
            o.onAction(event);
        }
    }

    public void subscribe(Observer observer, Event event)
    {
        if (!observers.containsKey(event))
        {
            ArrayList<Observer> newEventObserversList = new ArrayList<>();
            newEventObserversList.add(observer);
            observers.put(event, newEventObserversList);
        }
        else
        {
            ArrayList<Observer> currentEventObservers = observers.get(event);
            currentEventObservers.add(observer);
            //observers.replace(event, eventObservers);
        }
    }

    public void unsubscribe(Observer observer, Event event)
    {
        if (observers.containsKey(event))
        {
            ArrayList<Observer> currentEventObservers = observers.get(event);
            currentEventObservers.remove(observer);
        }
    }
}
