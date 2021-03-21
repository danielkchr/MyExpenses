package com.controller;

import com.dao.ExpenseDAO;
import com.ui.ExpenseForm;
import com.ui.MainForm;
import com.util.DBUtil;
import com.util.LocalDBUtil;

public class Main
{
    private static void start()
    {
        DBUtil.initialize(new LocalDBUtil("jdbc:h2:~/local_db", "root", ""));

        Mediator mediator = new Mediator();
        new MainForm(mediator);
        new ExpenseForm(mediator);


        new Controller(mediator);
    }

    static public void main(String[] args)
    {
        Main.start();
    }
}
