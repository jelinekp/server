package cz.kvalitne.zapisy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
 
public class Server
{
 
    private static Socket socket;
 
    public static void main(String[] args)
    {
        try
        {
 
			ServerSocket serverSocket = new ServerSocket(25004);
            System.out.println("Server běží");

            while(true)
            {
                //serverSocket.();
            	socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String helloServer = br.readLine();
                System.out.println(helloServer);
                String odpoved;
                switch(helloServer){
                case "hello-server":
                	 odpoved = "hello-client";
                	 System.out.println(odpoved);
                break;
                case "random":
                	String[] random = { "A", "B", "C", "D"};
            		odpoved = (random[new Random().nextInt(random.length)]);
            		System.out.println(odpoved);
            	break;
            	default: odpoved = "chyba";
            	break;
                }
                
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(odpoved);
                bw.flush();
                
                serverSocket.close();
            }
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}