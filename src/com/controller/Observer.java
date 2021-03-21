package com.controller;

public interface Observer
{
    void onAction(Event event);

    void onAction(Event event, EventData data);
}
