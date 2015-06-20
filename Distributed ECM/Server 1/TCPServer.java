/**
 * Distributed Elliptic Curve Method Server
 * Yongzhao Mo
 **/
// Elliptic Curve Method (ECM) Prime Factorization
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

