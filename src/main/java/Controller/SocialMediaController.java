package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/accounts", this::postAccountHandler);
        app.get("/accounts", this::verifyUserHandler);
        app.post("/messages", this::postMessage);
        app.get("/messages", this::getAllMessagesByUser);
        app.get("/messages", this::getAllMessages);
        app.post("/messages", this::deleteMessageHandler);
        app.post("/messages", this::updateMessageHandler);
        app.get("/messages", this::getMessagesByMessageID);



        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void verifyUserHandler(Context ctx)
    {
        /*Need to figure out how to make statements to read username and then another statement for password 
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body());
        Account verfiyAccount = accountService.verifyAccount(account);
        */
        
    

    }
    private void postAccountHandler(Context ctx) throws JsonProcessingException 
    {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);

        if(addedAccount != null)
        {
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
        else
        {
            ctx.status(400);
        }
    }

    private void postMessage(Context ctx)
    {

    }

    private void getAllMessagesByUser(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        
        int userID = Integer.parseInt(ctx.pathParam("posted_by"));
        List<Message> userMessages = messageService.getUserMessages(userID);

        System.out.println(userMessages);

        if(userMessages == null)
        {
            ctx.status(400);

        }
        else
        {
            ctx.json(mapper.writeValueAsString(userMessages));

        }

    }

    private void getAllMessages(Context ctx)
    {
        List<Message> messages = messageService.getAllMessage();
        ctx.json(messages);

    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.messageByID(message_id);

        System.out.println(deletedMessage);

        if(deletedMessage == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(deletedMessage));
            messageService.deleteMessByID(message_id);
        }

    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessages((message_id), message);
        System.out.println(updatedMessage);

        if(updatedMessage == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }


    }

    private void getMessagesByMessageID(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));

        Message messagesByMessID = messageService.messageByID(message_id);
        System.out.println(messagesByMessID);

        if(messagesByMessID == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(messagesByMessID));
        }
        

    }


}