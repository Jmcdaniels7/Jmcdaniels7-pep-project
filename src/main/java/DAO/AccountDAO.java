package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    //verify user
    public Account verifyUser(String username, String password)
    {
        Connection connection = ConnectionUtil.getConnection();

        try
        {
            String sql = "select * from Account where username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));

                return account;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //new user
    public Account insertUser(Account account)
    {
        Connection connection = ConnectionUtil.getConnection();

        try
        {
            //sql query for inserting a new user
            String sql = "insert into Account (username, password) values(?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            return account;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByID(int id)
    {
        Connection connection = ConnectionUtil.getConnection();

        try
        {
            //sql query for inserting a new user
            String sql = "select * from Account where account_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
           
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));

                return account;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;

    }
    
}
