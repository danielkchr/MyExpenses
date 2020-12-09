package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDBUtil
{
    private static LocalDBUtil instance;
    private Connection connection;

    private final String DB_URL = "jdbc:h2:~/local_db";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "";

    public static void main(String[] args)
    {
        new LocalDBUtil();
    }

    private LocalDBUtil()
    {
        try
        {
            Class.forName("org.h2.Driver");
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Singleton class implementation
    public static LocalDBUtil getInstance()
    {
        if (instance == null)
        {
            instance = new LocalDBUtil();
        }
        return instance;
    }

    public interface QueryHandler<T>
    {
        public T run(PreparedStatement ps) throws SQLException;
    }

    public <T> T execute(String query, QueryHandler<T> handler)
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
}