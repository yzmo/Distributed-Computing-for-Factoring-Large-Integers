/**
 * CP 493
 * Distributed Elliptic Curve Method Server
 * 
 * Student Number: 	    049004685				
 * Student Name:   		Yongzhao Mo
 * Student Email:       moxx4685@wlu.ca
 * Date:		August.10, 2007
 **/
// Elliptic Curve Method (ECM) Prime Factorization
// Based in Dario Alejandro Alpern's implementation
import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

class TCPServer 
{
   static int count = 0;
   
   //private static SendThread[] sThread = new SendThread[5];
   
   public static void main(String argv[]) throws Exception
   {
       	
       	try
       	{
       	
       	ServerSocket welcomeSocket = new ServerSocket(1234);
		while(true)
		{
			ServerThread connectionSocket = new ServerThread(welcomeSocket.accept());
			connectionSocket.setCount(count);
			connectionSocket.start();
			count = count + 1;
		
		}
       	}catch(Exception e)
        {
        	
        } 
    }
}

