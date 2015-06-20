/**
 * Distributed Quadratic Sieve Client
 * Yongzhao Mo
 **/ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.text.DateFormat;
/**
 *TCPClient
 *
 */
public class TCPClient extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel6;
	private JTextField IPField1;
	private JTextField PortField1;
	private JTextField IPField2;
	private JTextField PortField2;
	private JTextField IPField3;
	private JTextField PortField3;
	private JTextField IPField4;
	private JTextField PortField4;
	private JTextField IPField5;
	private JTextField PortField5;
	private JTextField SizeField;
	private JTextField showTime;
	private JTextPane jTextPane1;
	private JScrollPane jScrollPane3;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane2;
	private JButton ConnectButton;
	private JButton SendButton;
	private JPanel contentPane;
	// program variables declaration
 	private boolean connected = false;
 	public static boolean flag = false;
 	public static int event = 0; 
 	
 	private static String IP;
 	private static String Port;
 	public static Socket clientSocket = null;

 	public static DataOutputStream outToServer = null;
 	public static BufferedReader inFromServer = null;
	static String sentence;
  	static String n;
  	static String t;
  	static BigInteger nTry;
    static BigInteger factor;
    static  Vector connectedQueue;
    static  ClientThread ct[] = new ClientThread[20];
    static BigInteger primeSignal = BigInteger.valueOf(-9);
	static long Old;
	static long New;
	static long OldTimeElapsed;
	static boolean timeFlag = false;


	public TCPClient()
	{
		super();
		initializeComponent();
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent ev) 
			{
				try
				{
					//-1 to acknowlge tell server it is about to close connection
					outToServer.writeBytes("-1");  
					clientSocket.close();      //close conection
					inFromServer.close();
					
					outToServer.close();
				}
				catch (Exception e)
				{
				}
				System.exit(0);
				
			}
			
		} );

		this.setVisible(true);
	}

	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel6 = new JLabel();
		IPField1 = new JTextField();
		PortField1 = new JTextField();
		IPField2 = new JTextField();
		PortField2 = new JTextField();
		IPField3 = new JTextField();
		PortField3 = new JTextField();
		IPField4 = new JTextField();
		PortField4 = new JTextField();
		IPField5 = new JTextField();
		PortField5 = new JTextField();
		SizeField = new JTextField();
		showTime = new JTextField();
		jTextPane1 = new JTextPane();
		jScrollPane3 = new JScrollPane();
		jTextArea1 = new JTextArea();
		jScrollPane2 = new JScrollPane();
		ConnectButton = new JButton();
		SendButton = new JButton();
		contentPane = (JPanel)this.getContentPane();


		jLabel1.setText("IP Address:");
		jLabel2.setText("Port Number:");
		jLabel3.setText("Number to be factorized:");
		jLabel4.setText("Size:");
		jLabel6.setText("Output:");

		IPField1.setText("localhost");
		IPField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				IPField1_actionPerformed(e);
			}

		});

		PortField1.setText("1234");
		PortField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PortField1_actionPerformed(e);
			}

		});

		IPField2.setText("localhost");
		IPField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				IPField2_actionPerformed(e);
			}

		});

		PortField2.setText("1234");
		PortField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PortField2_actionPerformed(e);
			}

		});

		IPField3.setText("localhost");
		IPField3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				IPField3_actionPerformed(e);
			}

		});

		PortField3.setText("1234");
		PortField3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PortField3_actionPerformed(e);
			}

		});

		IPField4.setText("localhost");
		IPField4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				IPField4_actionPerformed(e);
			}

		});

		PortField4.setText("1234");
		PortField4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PortField4_actionPerformed(e);
			}

		});

		IPField5.setText("localhost");
		IPField5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				IPField5_actionPerformed(e);
			}

		});

		PortField5.setText("1234");
		PortField5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				PortField5_actionPerformed(e);
			}

		});

		SizeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SizeField_actionPerformed(e);
			}

		});


		showTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showTime_actionPerformed(e);
			}

		});

		jScrollPane3.setViewportView(jTextPane1);

		jScrollPane2.setViewportView(jTextArea1);

		ConnectButton.setText("Connect");
		ConnectButton.setToolTipText("Click to connect or disconnect");
		ConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ConnectButton1_actionPerformed(e);
				ConnectButton2_actionPerformed(e);
				ConnectButton3_actionPerformed(e);
				ConnectButton4_actionPerformed(e);
				ConnectButton5_actionPerformed(e);
			}

		});

		

		SendButton.setText("Send");
		SendButton.setToolTipText("Click to send data");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SendButton_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 47,18,60,18);
		addComponent(contentPane, jLabel2, 196,18,71,18);
		addComponent(contentPane, jLabel3, 496,25,130,18);
		addComponent(contentPane, jLabel4, 368,27,47,18);
		addComponent(contentPane, jLabel6, 124,326,51,26);
		addComponent(contentPane, IPField1, 47,51,137,22);
		addComponent(contentPane, PortField1, 194,51,70,22);
		addComponent(contentPane, IPField2, 46,109,137,22);
		addComponent(contentPane, PortField2, 194,109,73,22);
		addComponent(contentPane, IPField3, 44,168,138,22);
		addComponent(contentPane, PortField3, 194,168,73,21);
		addComponent(contentPane, IPField4, 43,227,137,22);
		addComponent(contentPane, PortField4, 194,227,75,22);
		addComponent(contentPane, IPField5, 42,282,136,22);
		addComponent(contentPane, PortField5, 194,282,77,22);
		addComponent(contentPane, SizeField, 374,52,44,25);
		addComponent(contentPane, showTime, 172,329,368,23);
		addComponent(contentPane, jScrollPane3, 493,55,203,180);
		addComponent(contentPane, jScrollPane2, 123,354,488,171);
		addComponent(contentPane, ConnectButton, 280,49,81,25);
		addComponent(contentPane, SendButton, 546,262,99,47);
		//
		// TCPClient
		//
		this.setTitle("Distributed System for Factoring Large Integers");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(732, 576));
		this.setResizable(false);
	}


	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}



	private void IPField1_actionPerformed(ActionEvent e)
	{
		
	

	}

	private void PortField1_actionPerformed(ActionEvent e)
	{
		
	

	}

	private void IPField2_actionPerformed(ActionEvent e)
	{
		
		

	}

	private void PortField2_actionPerformed(ActionEvent e)
	{
		
		

	}

	private void IPField3_actionPerformed(ActionEvent e)
	{
		
		

	}

	private void PortField3_actionPerformed(ActionEvent e)
	{
		

	}

	private void IPField4_actionPerformed(ActionEvent e)
	{
		

	}

	private void PortField4_actionPerformed(ActionEvent e)
	{
		

	}

	private void IPField5_actionPerformed(ActionEvent e)
	{
		

	}

	private void PortField5_actionPerformed(ActionEvent e)
	{
		

	}

	private void SizeField_actionPerformed(ActionEvent e)
	{


	}


	private void showTime_actionPerformed(ActionEvent e)
	{

	}

	private void ConnectButton1_actionPerformed(ActionEvent e)
	{
		
			IP = IPField1.getText();
			Port = PortField1.getText();
		
			if (e.getActionCommand().equals("Connect"))  //click connect
			{
				try 
				{
			
				   clientSocket = new Socket(IP, Integer.parseInt(Port));	//create TCP connection

			   	 	(new ClientThread(clientSocket)).start();
			   	 	//break;
					
				
	    		}
				catch (UnknownHostException ConnectException)
				{
					JOptionPane.showMessageDialog(this, "Error! Unknown host. Connection could not be established.", "Error while connecting", JOptionPane.ERROR_MESSAGE);
					ConnectButton.setText("Connect");
					//jTextArea1.setText("Not connected");
				}
				catch (IOException IOE)
				{
					JOptionPane.showMessageDialog(this, "Error! IO Exception caught.", "Error", JOptionPane.ERROR_MESSAGE);
					ConnectButton.setText("Connect");
					//jTextArea1.setText("Not connected");
				}
				catch (NumberFormatException NumE)
				{
					JOptionPane.showMessageDialog(this, "Error! Port number not in correct format.", "Error", JOptionPane.ERROR_MESSAGE);			
					ConnectButton.setText("Connect");
					//jTextArea1.setText("Not connected");
				}
			}
			else if (e.getActionCommand().equals("Disconnect")) //disconnect
			{
				try
				{
					//-1 to acknowlge tell server it is about to close connection
					outToServer.writeBytes("-1");
					ConnectButton.setText("Connect"); //change button name
					(new SendThread1(clientSocket)).stop();
					clientSocket.close();
					clientSocket = null;
					outToServer.close();
					inFromServer.close();
					//jTextArea1.setText("Not connected");
					jTextPane1.setEditable(false);
					SendButton.setEnabled(false);
				
				}
				catch (Exception excep)
				{
					
				}
			
			}
	}


	private void ConnectButton2_actionPerformed(ActionEvent e)
	{
		IP = IPField2.getText();
		Port = PortField2.getText();
		
		if (e.getActionCommand().equals("Connect"))  //click connect
		{
			try 
			{
				clientSocket = new Socket(IP, Integer.parseInt(Port));	//create TCP connection

			   	 	(new ClientThread(clientSocket)).start();
			   	 	//break;
				
			}
			catch (UnknownHostException ConnectException)
			{
				JOptionPane.showMessageDialog(this, "Error! Unknown host. Connection could not be established.", "Error while connecting", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (IOException IOE)
			{
				JOptionPane.showMessageDialog(this, "Error! IO Exception caught.", "Error", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (NumberFormatException NumE)
			{
				JOptionPane.showMessageDialog(this, "Error! Port number not in correct format.", "Error", JOptionPane.ERROR_MESSAGE);			
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
		}
		else if (e.getActionCommand().equals("Disconnect")) //disconnect
		{
			try
			{
				//-1 to acknowlge tell server it is about to close connection
				outToServer.writeBytes("-1");
				ConnectButton.setText("Connect"); //change button name
				(new SendThread2(clientSocket)).stop();
				clientSocket.close();
				clientSocket = null;
				outToServer.close();
				inFromServer.close();
				//jTextArea1.setText("Not connected");
				jTextPane1.setEditable(false);
				SendButton.setEnabled(false);
				
			}
			catch (Exception excep)
			{
				
			}
			
		}

	}


	private void ConnectButton3_actionPerformed(ActionEvent e)
	{
		IP = IPField3.getText();
		Port = PortField3.getText();
		
		if (e.getActionCommand().equals("Connect"))  //click connect
		{
			try 
			{
				clientSocket = new Socket(IP, Integer.parseInt(Port));	//create TCP connection

			   	 	(new ClientThread(clientSocket)).start();
			   	 	//break;
				
			}
			catch (UnknownHostException ConnectException)
			{
				JOptionPane.showMessageDialog(this, "Error! Unknown host. Connection could not be established.", "Error while connecting", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (IOException IOE)
			{
				JOptionPane.showMessageDialog(this, "Error! IO Exception caught.", "Error", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (NumberFormatException NumE)
			{
				JOptionPane.showMessageDialog(this, "Error! Port number not in correct format.", "Error", JOptionPane.ERROR_MESSAGE);			
				ConnectButton.setText("Connect");
			//	jTextArea1.setText("Not connected");
			}
		}
		else if (e.getActionCommand().equals("Disconnect")) //disconnect
		{
			try
			{
				//-1 to acknowlge tell server it is about to close connection
				outToServer.writeBytes("-1");
				ConnectButton.setText("Connect"); //change button name
				(new SendThread3(clientSocket)).stop();
				clientSocket.close();
				clientSocket = null;
				outToServer.close();
				inFromServer.close();
				//jTextArea1.setText("Not connected");
				jTextPane1.setEditable(false);
				SendButton.setEnabled(false);
				
			}
			catch (Exception excep)
			{
				
			}
			
		}

	}


	private void ConnectButton4_actionPerformed(ActionEvent e)
	{
		IP = IPField4.getText();
		Port = PortField4.getText();
		
		if (e.getActionCommand().equals("Connect"))  //click connect
		{
			try 
			{
				clientSocket = new Socket(IP, Integer.parseInt(Port));	//create TCP connection

			   	 	(new ClientThread(clientSocket)).start();
			   	 	//break;
				
			}
			catch (UnknownHostException ConnectException)
			{
				JOptionPane.showMessageDialog(this, "Error! Unknown host. Connection could not be established.", "Error while connecting", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (IOException IOE)
			{
				JOptionPane.showMessageDialog(this, "Error! IO Exception caught.", "Error", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
				//jTextArea1.setText("Not connected");
			}
			catch (NumberFormatException NumE)
			{
				JOptionPane.showMessageDialog(this, "Error! Port number not in correct format.", "Error", JOptionPane.ERROR_MESSAGE);			
				ConnectButton.setText("Connect");
			//	jTextArea1.setText("Not connected");
			}
		}
		else if (e.getActionCommand().equals("Disconnect")) //disconnect
		{
			try
			{
				//-1 to acknowlge tell server it is about to close connection
				outToServer.writeBytes("-1");
				ConnectButton.setText("Connect"); //change button name
				(new SendThread4(clientSocket)).stop();
				clientSocket.close();
				clientSocket = null;
				outToServer.close();
				inFromServer.close();
			//	jTextArea1.setText("Not connected");
				jTextPane1.setEditable(false);
				SendButton.setEnabled(false);
				
			}
			catch (Exception excep)
			{
				
			}
			
		}

	}


	private void ConnectButton5_actionPerformed(ActionEvent e)
	{
		IP = IPField5.getText();
		Port = PortField5.getText();
		
		if (e.getActionCommand().equals("Connect"))  //click connect
		{
			try 
			{
				clientSocket = new Socket(IP, Integer.parseInt(Port));	//create TCP connection

			   	 	(new ClientThread(clientSocket)).start();
			   	 	//break;
				
			}
			catch (UnknownHostException ConnectException)
			{
				JOptionPane.showMessageDialog(this, "Error! Unknown host. Connection could not be established.", "Error while connecting", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
			//	jTextArea1.setText("Not connected");
			}
			catch (IOException IOE)
			{
				JOptionPane.showMessageDialog(this, "Error! IO Exception caught.", "Error", JOptionPane.ERROR_MESSAGE);
				ConnectButton.setText("Connect");
			//	jTextArea1.setText("Not connected");
			}
			catch (NumberFormatException NumE)
			{
				JOptionPane.showMessageDialog(this, "Error! Port number not in correct format.", "Error", JOptionPane.ERROR_MESSAGE);			
				ConnectButton.setText("Connect");
			//	jTextArea1.setText("Not connected");
			}
		}
		else if (e.getActionCommand().equals("Disconnect")) //disconnect
		{
			try
			{
				//-1 to acknowlge tell server it is about to close connection
				outToServer.writeBytes("-1");
				ConnectButton.setText("Connect"); //change button name
				(new SendThread5(clientSocket)).stop();
				clientSocket.close();
				clientSocket = null;
				outToServer.close();
				inFromServer.close();
			//	jTextArea1.setText("Not connected");
				jTextPane1.setEditable(false);
				SendButton.setEnabled(false);
				
			}
			catch (Exception excep)
			{
				
			}
			
		}

	}


	private void SendButton_actionPerformed(ActionEvent e)
	{
		n = jTextPane1.getText();
       	t = SizeField.getText();
       	Old = System.currentTimeMillis();
       	New = System.currentTimeMillis();

		(new SendThread1(clientSocket)).start();
		(new SendThread2(clientSocket)).start();
		(new SendThread3(clientSocket)).start();
		(new SendThread4(clientSocket)).start();
		(new SendThread5(clientSocket)).start();
		
    	OldTimeElapsed = New - Old;
          String timeElapsed = GetDHMS(OldTimeElapsed / 1000);
          String textAreaInfo = "Factorization complete in " + timeElapsed;
   		showTime.setText(textAreaInfo);
	}
	
	public class SendThread1 extends Thread
	{
		
		Socket clientTSocket = null;  
		 
		public SendThread1(Socket clientSSocket)
		{
			this.clientTSocket=clientSSocket;
		}
		public void run() 
    	{
    		
    		try{
    			
    	
    		if (clientTSocket.isConnected() == true)
			{
				
    		try
			{
				if (!jTextPane1.getText().equals(""))  //send only there are text on the text area
				{
					try
					{
						nTry = new BigInteger(String.valueOf(jTextPane1.getText()));
						try			
						{
							//n = jTextPane1.getText();
       						//t = SizeField.getText();
							//SizeField.setText("");
							
							
							//sent text to server
							outToServer.writeBytes(n + '\n');
							outToServer.flush();
							outToServer.writeBytes(t + '\n');
							outToServer.flush();

							//jTextPane1.setText("");
							
						//	jTextArea1.setText("Sent");
						}
         				catch(Exception exp)
						{
							
						}
						try
						{		
							while (!inFromServer.ready()) 
							{
								//System.out.println("NOT READY");
							}
							//System.out.println("READY");
       						String text = inFromServer.readLine();
   
       						factor = new BigInteger(String.valueOf(text));


	       					if (!text.equals("null null"))
       						{
       							if(factor.equals(primeSignal))
       							{
       								jTextArea1.append(n +" is a prime number!"+"\n");
       							}
       							else
       							{
       								jTextArea1.append(n +" = "+factor + " * " +(new BigInteger(n)).divide(factor) +"\n");
       								
       							}
       				

       							ConnectButton.setText("Connect"); //change button name
       
       				
								clientSocket.close();
								clientSocket = null;
								outToServer.close();
								inFromServer.close();
								//jTextArea1.setText("Not connected");
								jTextPane1.setEditable(false);
								SendButton.setEnabled(false);
       						}
       						else
       						{
       							//jTextArea1.setText("Didn't get needed infomation");
       						}
      					}
         				catch(Exception ex)
						{
							
						}
				}
				catch(Exception ep)
				{
					JOptionPane.showMessageDialog(null, "Error! Please enter integer only.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			
			}
			else
			{
				//jTextArea1.setText("No text to send.");
			}
			}
		catch(Exception e){};
    	}
    	if(timeFlag==false)
    	{
    		New = System.currentTimeMillis();
    		timeFlag = true;
    	}
    	
   		}catch(Exception eeeex)
		{
		}
      }	
	}
	
	public class SendThread2 extends Thread
	{
		Socket clientTSocket = null;  
		 
		public SendThread2(Socket clientSSocket)
		{
			this.clientTSocket=clientSSocket;
		}
		public void run() 
    	{
    		try
    		{
    		
    		if (clientTSocket.isConnected() == true)
			{
				
    		try
			{
				if (!jTextPane1.getText().equals(""))  //send only there are text on the text area
				{
					try
					{
						nTry = new BigInteger(String.valueOf(jTextPane1.getText()));
						try			
						{
							//n = jTextPane1.getText();
       						//t = SizeField.getText();
							//SizeField.setText("");
							
							
							//sent text to server
							outToServer.writeBytes(n + '\n');
							outToServer.flush();
							outToServer.writeBytes(t + '\n');
							outToServer.flush();
							//jTextPane1.setText("");
							
						//	jTextArea1.setText("Sent");
						}
         				catch(Exception exp)
						{
							
						}
						try
						{		
							while (!inFromServer.ready()) 
							{
								//System.out.println("NOT READY");
							}
							
							String text = inFromServer.readLine();
       						
       						factor = new BigInteger(String.valueOf(text));

	       					if (!text.equals("null null"))
       						{
       							if(factor.equals(primeSignal))
       							{
       								jTextArea1.append(n +" is a prime number!"+"\n");
       							}
       							else
       							{
       								jTextArea1.append(n +" = "+factor + " * " +(new BigInteger(n)).divide(factor) +"\n");
       								
       							}
       				

       							ConnectButton.setText("Connect"); //change button name
       
       				
								clientSocket.close();
								clientSocket = null;
								outToServer.close();
								inFromServer.close();
								//jTextArea1.setText("Not connected");
								jTextPane1.setEditable(false);
								SendButton.setEnabled(false);
       						}
       						else
       						{
       							//jTextArea1.setText("Didn't get needed infomation");
       						}
      					}
         				catch(Exception ex)
						{

						}
				}
				catch(Exception ep)
				{
					JOptionPane.showMessageDialog(null, "Error! Please enter integer only.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			
			}
			else
			{
				//jTextArea1.setText("No text to send.");
			}
			}
		catch(Exception e){};
    	}
    	if(timeFlag==false)
    	{
    		New = System.currentTimeMillis();
    		timeFlag = true;
    	}
   		}catch(Exception eeeex)
		{
		}
      }	
	}
	
	public class SendThread3 extends Thread
	{
		Socket clientTSocket = null;  
		 
		public SendThread3(Socket clientSSocket)
		{
			this.clientTSocket=clientSSocket;
		}
		public void run() 
    	{
    		try
    		{

    		if (clientTSocket.isConnected() == true)
			{
				
    		try
			{
				if (!jTextPane1.getText().equals(""))  //send only there are text on the text area
				{
					try
					{
						nTry = new BigInteger(String.valueOf(jTextPane1.getText()));
						try			
						{
							//n = jTextPane1.getText();
       						//t = SizeField.getText();
							//SizeField.setText("");
							
							
							//sent text to server
							outToServer.writeBytes(n + '\n');
							outToServer.flush();
							outToServer.writeBytes(t + '\n');
							outToServer.flush();

							//jTextPane1.setText("");
							
						//	jTextArea1.setText("Sent");
						}
         				catch(Exception exp)
						{
							
						}
						try
						{		
							while (!inFromServer.ready()) 
							{
								//System.out.println("NOT READY");
							}
							//System.out.println("READY");
       						String text = inFromServer.readLine();

       						factor = new BigInteger(String.valueOf(text));

	       					if (!text.equals("null null"))
       						{
       							if(factor.equals(primeSignal))
       							{
       								jTextArea1.append(n +" is a prime number!"+"\n");
       							}
       							else
       							{
       								jTextArea1.append(n +" = "+factor + " * " +(new BigInteger(n)).divide(factor) +"\n");
       								
       							}
       				

       							ConnectButton.setText("Connect"); //change button name
       
       				
								clientSocket.close();
								clientSocket = null;
								outToServer.close();
								inFromServer.close();
								//jTextArea1.setText("Not connected");
								jTextPane1.setEditable(false);
								SendButton.setEnabled(false);
       						}
       						else
       						{
       							//jTextArea1.setText("Didn't get needed infomation");
       						}
      					}
         				catch(Exception ex)
						{

						}
				}
				catch(Exception ep)
				{
					JOptionPane.showMessageDialog(null, "Error! Please enter integer only.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			
			}
			else
			{
				//jTextArea1.setText("No text to send.");
			}
			}
		catch(Exception e){};
    	}
    	if(timeFlag==false)
    	{
    		New = System.currentTimeMillis();
    		timeFlag = true;
    	}

   		}catch(Exception eeeex)
		{
		}
      }	
	}
	
	public class SendThread4 extends Thread
	{
		Socket clientTSocket = null;  
		 
		public SendThread4(Socket clientSSocket)
		{
			this.clientTSocket=clientSSocket;
		}
		public void run() 
    	{
    		try
    		{
    	
    		if (clientTSocket.isConnected() == true)
			{
				
    		try
			{
				if (!jTextPane1.getText().equals(""))  //send only there are text on the text area
				{
					try
					{
						nTry = new BigInteger(String.valueOf(jTextPane1.getText()));
						try			
						{
							//n = jTextPane1.getText();
       						//t = SizeField.getText();
							//SizeField.setText("");
							
							
							//sent text to server
							outToServer.writeBytes(n + '\n');
							outToServer.flush();
							outToServer.writeBytes(t + '\n');
							outToServer.flush();

							//jTextPane1.setText("");
							
						//	jTextArea1.setText("Sent");
						}
         				catch(Exception exp)
						{
							
						}
						try
						{		
							while (!inFromServer.ready()) 
							{
								//System.out.println("NOT READY");
							}
							//System.out.println("READY");
       						String text = inFromServer.readLine();
       					
       						factor = new BigInteger(String.valueOf(text));

	       					if (!text.equals("null null"))
       						{
       							if(factor.equals(primeSignal))
       							{
       								jTextArea1.append(n +" is a prime number!"+"\n");
       							}
       							else
       							{
       								jTextArea1.append(n +" = "+factor + " * " +(new BigInteger(n)).divide(factor) +"\n");
       								
       							}
       				

       							ConnectButton.setText("Connect"); //change button name
       
       				
								clientSocket.close();
								clientSocket = null;
								outToServer.close();
								inFromServer.close();
								//jTextArea1.setText("Not connected");
								jTextPane1.setEditable(false);
								SendButton.setEnabled(false);
       						}
       						else
       						{
       							//jTextArea1.setText("Didn't get needed infomation");
       						}
      					}
         				catch(Exception ex)
						{

						}
				}
				catch(Exception ep)
				{
					JOptionPane.showMessageDialog(null, "Error! Please enter integer only.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			
			}
			else
			{
				//jTextArea1.setText("No text to send.");
			}
			}
		catch(Exception e){};
    	}
    	if(timeFlag==false)
    	{
    		New = System.currentTimeMillis();
    		timeFlag = true;
    	}

   		}catch(Exception eeeex)
		{
		}
      }	
	}
	
	public class SendThread5 extends Thread
	{
		long Old = System.currentTimeMillis();;
		long New;
		long OldTimeElapsed;
		Socket clientTSocket = null;  
		 
		public SendThread5(Socket clientSSocket)
		{
			this.clientTSocket=clientSSocket;
		}
		public void run() 
    	{
    		try
			{
    		if (clientTSocket.isConnected() == true)
			{
				
    		try
			{
				if (!jTextPane1.getText().equals(""))  //send only there are text on the text area
				{
					try
					{
						nTry = new BigInteger(String.valueOf(jTextPane1.getText()));
						try			
						{
							//n = jTextPane1.getText();
       						//t = SizeField.getText();
							//SizeField.setText("");
							
							
							//sent text to server
							outToServer.writeBytes(n + '\n');
							outToServer.flush();
							outToServer.writeBytes(t + '\n');
							outToServer.flush();

							//jTextPane1.setText("");
							
						//	jTextArea1.setText("Sent");
						}
         				catch(Exception exp)
						{
							
						}
						try
						{		
							while (!inFromServer.ready()) 
							{
								//System.out.println("NOT READY");
							}
							//System.out.println("READY");
       						String text = inFromServer.readLine();
       					
       						factor = new BigInteger(String.valueOf(text));
       			
	       					if (!text.equals("null null"))
       						{
       							if(factor.equals(primeSignal))
       							{
       								jTextArea1.append(n +" is a prime number!"+"\n");
       							}
       							else
       							{
       								jTextArea1.append(n +" = "+factor + " * " +(new BigInteger(n)).divide(factor) +"\n");
       								
       							}
       				

       							ConnectButton.setText("Connect"); //change button name
       
       				
								clientSocket.close();
								clientSocket = null;
								outToServer.close();
								inFromServer.close();
								//jTextArea1.setText("Not connected");
								jTextPane1.setEditable(false);
								SendButton.setEnabled(false);
       						}
       						else
       						{
       							//jTextArea1.setText("Didn't get needed infomation");
       						}
      					}
         				catch(Exception ex)
						{
							
						}
				}
				catch(Exception ep)
				{
					JOptionPane.showMessageDialog(null, "Error! Please enter integer only.", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			
			}
			else
			{
				//jTextArea1.setText("No text to send.");
			}
			}
		catch(Exception e){};
    	}
    	
    	if(timeFlag==false)
    	{
    		New = System.currentTimeMillis();
    		timeFlag = true;
    	}

   		}catch(Exception eeeex)
		{
		}
   		
      }	
	}
		
	public class ClientThread extends Thread{
    
    Socket clientTSocket = null;       
    ClientThread ct[]; 
    
    /*public ClientThread(Socket clientSSocket, ClientThread[] ctt){
	this.clientTSocket=clientSSocket;
        this.ct=ctt;
    }*/
    public ClientThread(Socket clientSSocket){
	this.clientTSocket=clientSSocket;
    }
    
    public void run() 
    {
		try
		{
			if (clientTSocket.isConnected() == true)
			{

       			ConnectButton.setText("Disconnect"); //change button name

				
				jTextPane1.setEditable(true);
			//	jTextArea1.setText("Connection 1 Connected");	
				SendButton.setEnabled(true);
				outToServer = new DataOutputStream(clientTSocket.getOutputStream());	
				inFromServer = new BufferedReader(new 
            	InputStreamReader(clientSocket.getInputStream())); 
			}
	    	//clientTSocket.close();
		}
		catch(IOException e){};
    }
  }  
 
 


	public String GetDHMS(long time)
  	{
   	 	return time / 86400 + "d " + (time % 86400) / 3600 + "h " +
           ((time % 3600) / 60) + "m " + (time % 60) + "s";
  	}
  	
	public static void main(String[] args)
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{

		}
		new TCPClient();
	}
//= End of Testing =


}
