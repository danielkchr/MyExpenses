package com.dao;

import com.bean.Expense;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBUtil;
import com.util.LocalDBUtil;

public class ExpenseDAO
{
    public static int add(Expense expense)
    {
        String query = "INSERT INTO expense (date, time, amount, category, comment) VALUES (?, ?, ?, ?, ?)";
        DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ps.setString(1, expense.getDate());
            ps.setString(2, expense.getTime());
            ps.setInt(3, expense.getAmount());
            ps.setString(4, expense.getCategory());
            ps.setString(5, expense.getComment());
            ps.executeUpdate();

            return true;
        });

        return getID();
    }

    public static boolean delete(int id)
    {
        String query = "DELETE FROM expense WHERE id = ?";
        return DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        });
    }


    public static boolean update(int id, Expense expense)
    {
        String query = "UPDATE expense SET date = ?, time = ?, amount = ?, category = ?, comment = ? WHERE id = ?";
        return DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ps.setString(1, expense.getDate());
            ps.setString(2, expense.getTime());
            ps.setInt(3, expense.getAmount());
            ps.setString(4, expense.getCategory());
            ps.setString(5, expense.getComment());
            ps.setInt(6, id);
            ps.executeUpdate();

            return true;
        });
    }

    /*
    public boolean updateBalance(long id, double newBalance)
    {
        String query = "UPDATE Account SET balance = ? WHERE id = ?";
        return dbUtil.execute(query, (PreparedStatement ps) -> {

            ps.setDouble(1, newBalance);
            ps.setInt(2, (int) id);
            ps.executeUpdate();

            return true;
        });
    }

    public boolean updateCustomerID(long id, long newID)
    {
        String query = "UPDATE Account SET customer_id = ? WHERE id = ?";
        return dbUtil.execute(query, (PreparedStatement ps) -> {

            ps.setInt(1, (int) newID);
            ps.setInt(2, (int) id);
            ps.executeUpdate();

            return true;
        });
    }

    public boolean updateTransactionFee(long id, double newFee)
    {
        String query = "UPDATE Account SET transaction_fee = ? WHERE id = ?";
        return dbUtil.execute(query, (PreparedStatement ps) -> {

            ps.setDouble(1, newFee);
            ps.setInt(2, (int) id);
            ps.executeUpdate();

            return true;
        });
    }

    public boolean updateNumTransactions(long id, int newNumber)
    {
        String query = "UPDATE Account SET transaction_count = ? WHERE id = ?";
        return dbUtil.execute(query, (PreparedStatement ps) -> {

            ps.setInt(1, newNumber);
            ps.setInt(2, (int) id);
            ps.executeUpdate();

            return true;
        });
    }

    public boolean updateAccountType(long id, AccountType newType)
    {
        String query = "UPDATE Account SET account_type = ? WHERE id = ?";
        return dbUtil.execute(query, (PreparedStatement ps) -> {

            ps.setString(1, newType.toString());
            ps.setInt(2, (int) id);
            ps.executeUpdate();

            return true;
        });
    }
    */

    public static Expense get(int id)
    {
        String query = "SELECT * FROM expense WHERE id = ?";
        return DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                return new Expense(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getInt("amount"),
                    rs.getString("category"),
                    rs.getString("comment")
                );
            }
            return null;
        });
    }

    public static ArrayList<Expense> getAll()
    {
        String query = "SELECT * FROM expense";
        return DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ArrayList<Expense> expenses = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                expenses.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("amount"),
                        rs.getString("category"),
                        rs.getString("comment")
                ));
            }
            return expenses;
        });
    }


    private static int getID()
    {
        String query = "SELECT MAX(id) FROM expense";
        return DBUtil.executeQuery(query, (PreparedStatement ps) -> {

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getInt("MAX(id)");
            }
            return 0;
        });
    }
}
