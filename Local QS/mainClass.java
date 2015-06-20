/**
 * Local Quadratic Sieve
 * Yongzhao Mo
 **/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;
import java.io.*;
import java.lang.Object;
import java.text.DateFormat;
import java.util.*;
/**
 * main class
 *
 */
public class mainClass extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextPane textNumber;
	private JScrollPane jScrollPane1;
	private JTextField SizeField1;
	private JTextPane textOutput;
	static JTextArea output;
	static JTextArea showTime;
	private JButton jFactorize;
	private JButton jClear;
	private JPanel contentPane;
	
	// End of variables declaration


	public mainClass()
	{
		super();
		initializeComponent();
		this.setVisible(true);
	}

	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		showTime = new JTextArea();
		textNumber = new JTextPane();
		jScrollPane1 = new JScrollPane();
		textOutput = new JTextPane();
		output = new JTextArea();
		jFactorize = new JButton();
		jClear = new JButton();
		contentPane = (JPanel)this.getContentPane();
		SizeField1 = new JTextField();

		jLabel1.setText("Input Number:");
		jLabel2.setText("Output:");
		jLabel3.setText("Size:");
		showTime.setText("");
		jScrollPane1.setViewportView(textNumber);
		jFactorize.setText("Factorize");
		jFactorize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				output.setText("");
				jFactorize_actionPerformed(e);
			}

		});
		jClear.setText("Clear");
		jClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				output.setText("");
				textNumber.setText("");
				showTime.setText("");
				SizeField1.setText("");
				
			}

		});
		
		SizeField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 25,20,70,18);
		addComponent(contentPane, jLabel2, 25,264,60,18);
		addComponent(contentPane, jLabel3, 25,200,60,18);
		addComponent(contentPane,showTime,130,264,360,18);
		addComponent(contentPane, jScrollPane1, 25,52,670,136);
		addComponent(contentPane, SizeField1, 25,225,60,18);
		addComponent(contentPane, output, 25,290,661,139);
		addComponent(contentPane, jFactorize, 250,219,117,35);
		addComponent(contentPane, jClear, 420,219,117,35);
		//
		// mainClass
		//
		this.setTitle("Factorization using the Quadratic Sieve");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(720, 489));
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}


	private void jFactorize_actionPerformed(ActionEvent e)
	{
		output.setText("");
		output.setText("Factorizing, please wait...");
		long Old;
		long New;
		long OldTimeElapsed;
    	BigInteger n = new BigInteger("1");
    	BigInteger factor = new BigInteger("1");
    	BigInteger two = new BigInteger("2");
    	BigInteger zero = new BigInteger("0");
    	BigInteger miOne = BigInteger.valueOf(-1);
    	int t = 0;
    	String value;
    	String textAreaContents = "";
    	String textOut = "";
    	String headOut = "";
    	int i = 0;
    	int dig = 0;
    	boolean endFlag = false;
    	try
    	{
    		n = new BigInteger(textNumber.getText().toString().trim());
    		t = Integer.parseInt(SizeField1.getText());
    	}
    	catch(Exception ee)
    	{
    		output.setText("Make sure you enter an number!");
    	}
    	Old = System.currentTimeMillis();
    	if(n.compareTo(new BigInteger("2")) == -1)
    	{
    		output.setText("Please enter an number larger than 1!");
    	}
    	else if(n.isProbablePrime(100))		//can be replaced by Miller-Rabin primality test
    	{
    		output.setText(n +" is a prime number!");
    	}
    	else
    	{
    		factor = Calculating.getCalculating2(n,t);
    		if(factor.equals(miOne) == true)
    		{
    			do
        		{	
        			factor = Calculating.getCalculating(n,t);
        			t = t + 1;

       			}while(factor.equals(miOne) == true);
    		}
    		
			
			value = n.toString() + " = "+ factor.toString() +" * "+ (n.divide(factor)).toString();
			dig = 6 & 0x3FF;
			i = (value.length() + dig - 1) % dig + 1;
			headOut = value.substring(0, i);
			while (i < value.length())
   			{
      			
      			if((textOut.length()+ 1 + value.substring(i, i + dig).length())>=73)
      			{
      				textAreaContents = textAreaContents + textOut + "\n";
      				textOut = value.substring(i, i + dig)+" ";
      				endFlag = true;
      			}
      			else
      			{
      				textOut = textOut + value.substring(i, i + dig)+" ";
      				endFlag = false;
      			}

      			i += dig;
    		}
			if(endFlag == false)
			{
				textAreaContents = textAreaContents + textOut;
			}
    
    		output.setText(headOut + textAreaContents);
    		
    	}
    	
    	New = System.currentTimeMillis();
    	OldTimeElapsed = New - Old;
          String timeElapsed = GetDHMS(OldTimeElapsed / 1000);
          String textAreaInfo = "Factorization complete in " + timeElapsed;
   		showTime.setText(textAreaInfo);
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
		new mainClass();
	}


}
