package com.bean;

public class Expense
{
    private int id;
    private String date;
    private String time;
    private int amount;
    private String category;
    private String comment;

    public Expense(int id, String date, String time, int amount, String category, String comment)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.category = category;
        this.comment = comment;
    }

    public Expense(String date, String time, int amount, String category, String comment)
    {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.category = category;
        this.comment = comment;
    }

    @Override
    public String toString()
    {
        return id + ", " + date + ", " + time + ", " + amount + ", " + category + ", " + comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
