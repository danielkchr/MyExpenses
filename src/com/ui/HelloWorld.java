package com.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorld
{
    private static int width = 500;
    private static int height = 300;

    private JPanel rootPanel;
    private JTable table1;

    String data[][]={   {"101","Amit","670000"},
                        {"102","Jai","780000"},
                        {"101","Sachin","700000"}};

    String column[]={"ID","NAME","SALARY"};

    public HelloWorld()
    {
        //DefaultTableModel model = (DefaultTableModel) table1.getModel();
        //model.addRow()

        DefaultTableModel model = new DefaultTableModel();
        table1.setModel(model);
        model.addColumn("Column 1");
        model.addColumn("Column 2");
        model.addColumn("Column 3");

         for (int i = 0; i < 100; i++)
             model.addRow(new Object[] {"test " + i, "test " + i, "test " + i, 1});
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("HelloWorld");
        frame.setContentPane(new HelloWorld().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        //frame.setSize(width, height);
        frame.setVisible(true);
    }
}
