package com.controller;

import com.bean.Expense;
import com.ui.MainForm;

import javax.swing.*;

public class Controller implements Observer
{
    Mediator mediator;

    public Controller(Mediator mediator)
    {
        this.mediator = mediator;

        initialise();
    }

    private void initialise()
    {
        mediator.subscribe(this, Event.ADD_EXPENSE);
        mediator.subscribe(this, Event.DELETE_EXPENSE);
        mediator.subscribe(this, Event.MAIN_FORM_CLOSE);

        mediator.action(Event.MAIN_FORM_SHOW);
        mediator.action(Event.EXPENSE_FORM_SHOW);
    }

    private void formClosePrompt(JFrame frame)
    {
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to close this window?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    private void addExpense(Expense expense)
    {
        mediator.action(Event.UPDATE_TEST_TEXT_FIELD,
                new EventData().put("text", "add button updated the field"));
    }

    private void deleteExpense(int id)
    {
        mediator.action(Event.UPDATE_TEST_TEXT_FIELD,
                new EventData().put("text", "delete button updated the field"));
    }

    //event handlers
    @Override
    public void onAction(Event event)
    {
        switch (event)
        {

        }
    }

    @Override
    public void onAction(Event event, EventData data)
    {
        switch (event)
        {
            case MAIN_FORM_CLOSE:
                formClosePrompt((JFrame) data.get("frame"));
                break;

            case ADD_EXPENSE:
                addExpense((Expense) data.get("expense"));
                break;

            case DELETE_EXPENSE:
                deleteExpense((Integer) data.get("id"));
                break;
        }
    }
}
