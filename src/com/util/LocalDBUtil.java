package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDBUtil extends DBUtil
{
    private Connection connection;

    public LocalDBUtil(String url, String username, String password)
    {
        try
        {
            Class.forName("org.h2.Driver");

            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    protected <T> T execute(String query, QueryHandler<T> handler)
    {
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            return handler.run(ps);
        }
        catch (SQLException e)
        {
            System.out.println("SQL Execute Exception: " + e.getMessage());
        }
        return null;
    }

    /*

    TO DELETE

    public static void main(String[] args)
    {
        //LocalDBUtil.getInstance();
        String currentDir = System.getProperty("user.dir");
        DBUtil.Initialize(new LocalDBUtil(DB_URL));
        System.out.println(currentDir);
    }
    */
}