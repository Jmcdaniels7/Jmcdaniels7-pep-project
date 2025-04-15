package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;


    //constructors
    public MessageService()
    {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO)
    {
        this.messageDAO = messageDAO;

    }

    //methods

    public Message addMessage(Message message)
    {
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
        if(messageDAO.getMessageByID(id) == null)
        {
            return null;
        }

        messageDAO.updateMessage(id, message);

        return messageDAO.getMessageByID(id);
    }

    public List<Message> getUserMessages(int user)
    {
        return messageDAO.getAllUserMessages(user);
    }


    
}
