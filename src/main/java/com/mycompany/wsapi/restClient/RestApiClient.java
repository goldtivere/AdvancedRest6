/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsapi.restClient;

import com.mycompany.wsapi.model.Message;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gold
 */
public class RestApiClient {
    
    public static void main(String []args){
        
       /** Scanner scnr=new Scanner(System.in);
        System.out.println("Enter Value: ");
        String value=scnr.next();**/
       
        Client client=ClientBuilder.newClient();
        
        WebTarget baseTarget= client.target("http://localhost:8084/AdvancedRest6/webapi/");
        WebTarget messagesTarget= baseTarget.path("service");
        WebTarget singleMesageTarget= messagesTarget.path("{messageId}");
        
        Message message=singleMesageTarget
        .resolveTemplate("messageId","1")
        .request(MediaType.APPLICATION_JSON)
        .get(Message.class);
        //Message message=response.readEntity(Message.class);
     // System.out.println(message.getMessage());
     
     Message newMessage= new Message(4,"My new Message from AdvancedRest","Gold");
     Response postResponse=messagesTarget
             .request()
             .post(Entity.json(newMessage));
     Message createdMessage=postResponse.readEntity(Message.class);
     System.out.println(createdMessage.getMessage());
    }
    
}
