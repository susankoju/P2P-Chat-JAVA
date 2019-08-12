package p2pChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Day 1: August 10 2019
 * 
 * @author 
 * Susan Koju
 * 
 * [Add your names above]
 */
public class P2PChat {
    
    public static void client() throws IOException{
        
        
        try {
            String serverIpAddress = "dell.localhost.run";
            int serverPort = 80;
            Socket s = new Socket(serverIpAddress,serverPort);
            
            Scanner scan = new Scanner(System.in);
            System.out.println("You are Connected");
            while(true){
                String sendMsg = "";
                
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String msg = in.readLine();
                System.out.println("Other: "+msg);
                
                System.out.print("You: ");
                sendMsg = scan.nextLine();
                if(sendMsg.equals(".bye"))
                    break;
                PrintWriter out = new PrintWriter(s.getOutputStream());
                out.println(sendMsg);
                out.flush();
                
                

            }

            
        } catch (IOException ex) {
            System.out.println("Server not found! Exception:"+ex.getMessage());
            //server();
        }
    
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("1. Server \n2. Clent \n:");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        if(i>1){
            client();
        }else{
            server();
        }
    }    
    
    public static void server() throws IOException{
        System.out.println("[Starting Server]");
        ServerSocket ss = new ServerSocket(65432);
        
        //Runtime.getRuntime().exec("ssh -R 80:localhost:8123 ssh.localhost.run");
        
        System.out.println("Server: server started");
        System.out.println("Server: waiting for client connect");
        
        Socket s = ss.accept();
        
        System.out.println("Server: cliend connected "+s.getLocalPort());
        
        Scanner scan = new Scanner(System.in);
        
        while(true)
        {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String receivedMsg = in.readLine();
            System.out.println("Other: "+receivedMsg);
            
            
            System.out.print("You: ");
            String sendMsg = scan.nextLine();
            if(sendMsg.equals(".bye"))
                break;

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println(sendMsg);
            out.flush();
        }
    }
}
