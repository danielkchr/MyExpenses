package com.controller;

import com.ui.MainForm;

public class Controller implements Observer
{
    MainForm mainForm;

    public Controller(Mediator mediator)
    {
        mediator.subscribe(this, Event.ADD_EXPENSE);
        new Controller2(mediator);

        mediator.action(Event.MAIN_FORM_SHOW);
    }

    @Override
    public void onAction(Event event)
    {
        System.out.println("Controller: " + event);
    }
}
