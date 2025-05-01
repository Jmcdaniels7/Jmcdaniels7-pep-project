package Service;

import Model.Message;

import Model.Account;

import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;


    //constructors
    public MessageService()
    {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO)
    {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;

    }

    //methods

    public Message addMessage(Message message)
    {
        if(message.getMessage_text() == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255)
        {
            return null;
        }

        return messageDAO.newMessage(message);
    }

    public List<Message> getAllMessage()
    {
        return messageDAO.getAllMessages();
    }

    public Message messageByID(int message_id)
    {
        if(messageDAO.getMessageByID(message_id) == null)
        {
            return null;
        }

        return messageDAO.getMessageByID(message_id);
    }

    public void deleteMessByID(int message_id)
    {
        if(messageDAO.getMessageByID(message_id) != null)
        {
            messageDAO.deleteMessage(message_id);
        }

    }

    public Message updateMessages(int id, Message message)
    {
        if(messageDAO.getMessageByID(id) == null || message.getMessage_text().length() > 255 || message.getMessage_text() == null || message.getMessage_text().isEmpty())
        {
            return null;
        }
        else
        {
            messageDAO.updateMessage(id, message);

            return messageDAO.getMessageByID(id);

        }

        
    }

    public List<Message> getUserMessages(int user)
    {
        Account getAccount = accountDAO.getAccountByID(user);

        if(getAccount == null || messageDAO.getAllUserMessages(user) == null || messageDAO.getAllUserMessages(user).isEmpty() ) 
        {
            return null;

        }

        return messageDAO.getAllUserMessages(user);
    }


    
}
