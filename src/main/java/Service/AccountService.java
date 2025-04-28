package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService
{
    AccountDAO accountDAO;


    //constructors
    public AccountService()
    {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO)
    {
        this.accountDAO = accountDAO;
    }

    //methods
    public Account addAccount(Account account)
    {
        if(account.username != null && account.password.length() > 4 && accountDAO.verifyUser(account.username,  account.password) == null)
        {
            return accountDAO.insertUser(account);

        }

        return null;
       
    }

    public Account verifyAccount(Account account)
    {
        return accountDAO.verifyUser(account.username, account.password);
    }

    public Account getAccountByID(Account account)
    {
        return accountDAO.getAccountByID(account.getAccount_id());
    }


    
}