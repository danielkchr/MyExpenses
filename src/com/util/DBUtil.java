package com.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DBUtil
{
    private static DBUtil utilInstance = null;

    public interface QueryHandler<T>
    {
        T run(PreparedStatement ps) throws SQLException;
    }

    public static <T> T executeQuery(String query, QueryHandler<T> handler)
    {
        try
        {
            return getInstance().execute(query, handler);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public static void initialize(DBUtil instance)
    {
        utilInstance = instance;
    }

    protected static DBUtil getInstance() throws Exception
    {
        if (utilInstance == null) throw new Exception("ERROR: Instance is not initialized");

        return utilInstance;
    }

    protected abstract <T> T execute(String query, QueryHandler<T> handler);
}

