package com.ui;

import com.bean.Expense;
import com.controller.Event;
import com.controller.EventData;
import com.controller.Mediator;
import com.controller.Observer;
import com.dao.ExpenseDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MainForm implements Observer
{
    private JPanel rootPanel;
    private JTable expenseTable;
    private JButton addExpenseButton;
    private JButton deleteExpenseButton;
    private JTextField testTextField;
    private JButton button1;
    private JFrame frame;

    private HashMap<Integer, Integer> rowIndexToId;
    private Mediator mediator;

    private final String FORM_TITLE = "MyExpenses";

    public MainForm(Mediator mediator)
    {
        this.mediator = mediator;
        initialise();

        addExpenseButton.addActionListener(e -> {
            mediator.action(Event.ADD_EXPENSE);
        });

        deleteExpenseButton.addActionListener(e -> {
            mediator.action(Event.DELETE_EXPENSE);
        });

        expenseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int selectedRowIndex = expenseTable.getSelectedRow();
                System.out.println(rowIndexToId.get(selectedRowIndex));
                //System.out.println( expenseTable.getValueAt(selectedRowIndex, 5));
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

    private void updateTestTextField(String text)
    {
        testTextField.setText(text);
    }

    private void initialise()
    {
        //subscribe to events
        mediator.subscribe(this, Event.MAIN_FORM_SHOW);
        mediator.subscribe(this, Event.MAIN_FORM_HIDE);
        mediator.subscribe(this, Event.UPDATE_TEST_TEXT_FIELD);

        //create window and set it to
        frame = new JFrame(FORM_TITLE);
        frame.setContentPane(rootPanel);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                mediator.action(Event.MAIN_FORM_CLOSE, new EventData().put("frame", frame));
            }
        });


        //create table
        DefaultTableModel model =  new DefaultTableModel(new Object[][] { }, new String[] {"Date", "Time", "Amount", "Category", "Comment"}) {

            Class[] columnTypes = new Class[] {
                    String.class, String.class, Integer.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };

        ArrayList<Expense> expenses = ExpenseDAO.getAll();
        rowIndexToId = new HashMap<>();
        for (int i = 0; i < expenses.size(); i++)
        {
            Expense expense = expenses.get(i);
            rowIndexToId.put(i, expense.getId());
            model.addRow(new Object[] {expense.getDate(), expense.getTime(), expense.getAmount(), expense.getCategory(), expense.getComment()});
        }

        expenseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseTable.setModel(model);
    }


    /**
     * Concise wrapper around method to show user popup message
     */
    private void alert(String msg) {
        JOptionPane.showInternalMessageDialog(frame.getContentPane(), msg);
    }


    /**
     * Return the value in the column for the selected row in the table
     */
    private Object valOfSelectedRowForColumn(String columnName, JTable table) {
        final int rowNum = table.getSelectedRow();
        final int colNum = table.getColumn(columnName).getModelIndex();
        return table.getValueAt(rowNum, colNum);
    }

    /**
     * Set the value in the column for the selected row in the table
     */
    private void editSelectedRowColumn(String columnName, String newVal, JTable table) {
        final int rowNum = table.getSelectedRow();
        final int colNum = table.getColumn(columnName).getModelIndex();
        table.setValueAt(newVal, rowNum, colNum);
    }

    @Override
    public void onAction(Event event)
    {
        switch (event)
        {
            case MAIN_FORM_SHOW:
                this.show();
                break;

            case MAIN_FORM_HIDE:
                this.hide();
                break;
        }
    }

    @Override
    public void onAction(Event event, EventData data)
    {
        switch (event)
        {
            case UPDATE_TEST_TEXT_FIELD:
                updateTestTextField((String) data.get("text"));
        }
    }
}
