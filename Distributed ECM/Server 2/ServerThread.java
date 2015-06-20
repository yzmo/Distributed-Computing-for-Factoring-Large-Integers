/**
 * Distributed Elliptic Curve Method Server
 * Yongzhao Mo
 **/
// Elliptic Curve Method (ECM) Prime Factorization

import java.io.*;
import java.net.*;
import java.util.*;
import java.math.BigInteger;

public class ServerThread extends Thread
{
	private Socket socket ;
	
	int count = 0;
    boolean flag;
    static String sentence;
  	static String n;
    static String factors;
    BigInteger close = BigInteger.valueOf(-1);
    BigInteger timeout = BigInteger.valueOf(-7);
    
	public ServerThread(Socket s)
	{
		super();
		socket= s;
		n = null;
		count = 0;
		flag = false;
	}
	
	public  void run()
	{
		try{

		//System.out.println("connected");
		
        BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(
                  socket.getInputStream()));
           DataOutputStream outToClient =
               new DataOutputStream(
                  socket.getOutputStream()); 
	    
	   
	    
	   
	    
	 do   
	 { 
    	try
    	{
    	
    	n = inFromClient.readLine();

    	if(close.equals(new BigInteger (String.valueOf(n))))
    	{
    		
    		socket.close();
    		inFromClient.close();
    		inFromClient = null;
    		outToClient.close();
    		outToClient = null;

    		break;
    	}
    	else
    	{	
        	ecm ecm1 = new ecm();
        	ecm1.init();
        	ecm1.inNumber(new BigInteger(n));
    		ecm1.BigNbrToBigInt(new BigInteger(n));
   			ecm1.run();
   			factors = ecm1.getAllFactors().trim();
        	System.out.println(factors);
      		outToClient.writeBytes(factors+ '\n');
        	socket.close();
    	}
    	}catch(Exception er)
        {
        }
        
	   		
     }while(socket.isConnected());    
         
       
       }
       catch(SocketException e)
       {
       		
       		
       }
       catch(Exception e)
         {
       	    
         }
      
      
	
	}
	
	public void setN(String nn)
	{
		n = nn;
	}
	public String getN()
	{
		return n;
	}
	public void setCount(int co)
	{
		count = co;
	}
	public int getCount()
	{
		return count;
	}
	public void setFlag(boolean ff)
	{
		flag = ff;
	}
	public boolean getFlag()
	{
		return flag;
	}
	
	
}