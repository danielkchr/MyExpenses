package com.controller;

public class Controller2 implements Observer
{
    public Controller2(Mediator mediator)
    {
        mediator.subscribe(this, Event.ADD_EXPENSE);
        mediator.unsubscribe(this, Event.ADD_EXPENSE);
    }

    @Override
    public void onAction(Event event)
    {
        System.out.println("Controller2: " + event);
    }
}
