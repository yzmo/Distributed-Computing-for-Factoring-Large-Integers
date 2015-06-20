/**
 * CP 493
 * Local Elliptic Curve Method
 * Yongzhao Mo
 **/
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;
import java.io.*;
/**
 * GUI
 *
 */
public class GUI extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextPane textNumber;
	private JScrollPane jScrollPane1;
	static JTextArea output;
	static JTextArea showTime;
	private JButton jFactorize;
	private JButton jClear;
	private JPanel contentPane;
	// End of variables declaration


	public GUI()
	{
		super();
		initializeComponent();

		this.setVisible(true);
	}

	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		showTime = new JTextArea();
		textNumber = new JTextPane();
		jScrollPane1 = new JScrollPane();
		output = new JTextArea();
		jFactorize = new JButton();
		jClear = new JButton();
		contentPane = (JPanel)this.getContentPane();


		jLabel1.setText("Input:");

		jLabel2.setText("Output:");
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
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 25,20,32,18);
		addComponent(contentPane, jLabel2, 25,264,60,18);
		addComponent(contentPane,showTime,130,264,360,18);
		addComponent(contentPane, jScrollPane1, 25,52,670,136);
		addComponent(contentPane, output, 25,290,661,139);
		addComponent(contentPane, jFactorize, 250,219,117,35);
		addComponent(contentPane, jClear, 420,219,117,35);
		//
		// GUI
		//
		this.setTitle("Factorization using the Elliptic Curve Method");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(720, 489));
	}


	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}


	private void jFactorize_actionPerformed(ActionEvent e)
	{
		output.setText("");
		showTime.setText("");
		output.setText("Factorizing, please wait...");
		ecm ecm1 = new ecm();
    	BigInteger big1, big2, big3;
    	big3 = new BigInteger("1");
    	ecm1.init();
    	int exp=344;
    	try
    	{
    		big3 = new BigInteger(textNumber.getText().toString().trim());
    	}
    	catch(Exception ee)
    	{
    		output.setText("Make sure you enter an number!");
    	}
    	ecm1.inNumber(big3);
    	ecm1.BigNbrToBigInt(big3);
   		ecm1.run();
   		output.setText(ecm1.getAllFactors());
	}




	public static void setTimeLabel(String ss)
	{
		showTime.setText(ss);
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
		new GUI();
	}


}
