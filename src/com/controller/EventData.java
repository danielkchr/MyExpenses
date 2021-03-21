package com.controller;

import java.util.HashMap;

public class EventData
{
    HashMap<String, Object> options;

    public EventData()
    {
        options = new HashMap<>();
    }

    public EventData put(String optionName, Object option)
    {
        try
        {
            if (options.containsKey(optionName))
            {
                throw new Exception("ERROR: Option '" + optionName + "' already exists");
            }
            options.put(optionName, option);
            return this;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public Object get(String optionName)
    {
        try
        {
            if (!options.containsKey(optionName))
            {
                throw new Exception("ERROR: Option '" + optionName + "' does not exist");
            }
            return options.get(optionName);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
