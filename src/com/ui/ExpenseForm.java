package com.ui;

import com.controller.Event;
import com.controller.EventData;
import com.controller.Mediator;
import com.controller.Observer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExpenseForm implements Observer
{
    private JPanel rootPanel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;

    private JFrame frame;
    private Mediator mediator;

    public ExpenseForm(Mediator mediator)
    {
        this.mediator = mediator;

        initialise();
    }

    private void initialise()
    {
        //subscribe to events
        mediator.subscribe(this, Event.EXPENSE_FORM_SHOW);
        mediator.subscribe(this, Event.EXPENSE_FORM_HIDE);

        //create frame
        frame = new JFrame("Expense");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        //close button listener
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                mediator.action(Event.EXPENSE_FORM_CLOSE, new EventData().put("frame", frame));
            }
        });
    }

    private void show()
    {
        frame.setVisible(true);
    }

    private void hide()
    {
        frame.setVisible(false);
    }

    //event handlers
    @Override
    public void onAction(Event event)
    {
        switch (event)
        {
            case EXPENSE_FORM_SHOW:
                show();
                break;

            case EXPENSE_FORM_HIDE:
                hide();
                break;
        }
    }

    @Override
    public void onAction(Event event, EventData data)
    {

    }
}
