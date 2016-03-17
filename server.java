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
 
public class server
{
 
    private static Socket socket;
 
    public static void main(String[] args)
    {
        try
        {
 
            ServerSocket serverSocket = new ServerSocket(9343);
            System.out.println("Server běží");

            while(true)
            {
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String helloServer = br.readLine();
                System.out.println(helloServer);

                String odpoved;
                String hello = "hello-server";
                String equal = hello + "\n";
                if(helloServer != equal){
                odpoved = "hello-client" + "\n";
                	
                }else{
                	odpoved = "funguje to!!" + "\n";
                }
                if(helloServer != "random"){
            		String[] random = { "A", "B", "C", "D"};
            		odpoved = (random[new Random().nextInt(random.length)]);
            	}else{
            		odpoved = "funguje to" + "\n";
            	}

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(odpoved);
                System.out.println(odpoved);
                bw.flush();
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
        //serverSocket.close();
    }
}