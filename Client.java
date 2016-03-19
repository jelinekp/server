package cz.kvalitne.zapisy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
 
public class Client
{
 
    private static Socket socket;	
 
    public static void main(String args[])
    {
        try
        {
            socket = new Socket("localhost", 25004);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            String hello = "hello-server";
 
            //String sendMessage = "hello-server";
            String sendMessage = hello + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);
            
            if(message.equals("hello-client")){
            	OutputStream os2 = socket.getOutputStream();
                OutputStreamWriter osw2 = new OutputStreamWriter(os2);
                BufferedWriter bw2 = new BufferedWriter(osw2);
                String random = "random";
                String sendRandom = random + "\n";
                bw2.write(sendRandom);
                bw2.flush();
                System.out.println("Požadavek na random odeslán... : "+sendRandom);
            }else{
            	System.out.println("chyba");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}