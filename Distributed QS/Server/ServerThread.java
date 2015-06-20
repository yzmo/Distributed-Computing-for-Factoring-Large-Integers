/**
 * Distributed Quadratic Sieve Server
 * Yongzhao Mo
 **/
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
  	static String t;
    static BigInteger factor;
    BigInteger close = BigInteger.valueOf(-1);
    BigInteger timeout = BigInteger.valueOf(-7);
    BigInteger miOne = BigInteger.valueOf(-1);
    
	public ServerThread(Socket s)
	{
		super();
		socket= s;
		n = null;
		t = null;
		count = 0;
		flag = false;
	}
	
	public  void run()
	{
		try{

		
		
        BigInteger factor;

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
    		t = inFromClient.readLine();
        	

        	factor = Calculating.getCalculating2(new BigInteger(String.valueOf(n)),Integer.parseInt(t));
    		if(factor.equals(miOne) == true)
    		{
    			do
        		{	
        			factor = Calculating.getCalculating(new BigInteger(String.valueOf(n)),Integer.parseInt(t));
        			t = t + 1;

       			}while(factor.equals(miOne) == true);
    		}
        	
      		outToClient.writeBytes(factor.toString()+ '\n');
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
	
	public void setT(String tt)
	{
		t = tt;
	}
	public String getT()
	{
		return t;
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