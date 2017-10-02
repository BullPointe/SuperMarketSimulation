import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SuperMarketGUI extends JFrame
{
	Container contentPane = this.getContentPane();
	private Supermarket superMarket;
	private ShoppingQueue shoppingQueue;
	private JLabel welcomeLabel,cashierLabel,timeJLabel;
	private JButton startSimulationButton;
	private JLabel showQueueSize;
	private JLabel clickedfirstName, clickedlastName, clickedcustomerID, clickedQueueTime;
	private JTextArea clickfirstName, clicklastName, clickcustomerID, clickQueueTime;
	private int displayedIndex;
//	private ArrayList<JButton> customerButtons;
	private JButton customerButton;
	private JButton[] customerButtons = new JButton[20];
	private int numofButtonsVisible=0;
	private int nextXBounds=0, nextYBounds = 200;
	private boolean ifStarted,ismovingLeft = false, ismovingRight = true;
	private Timer showClock;
	private int nextColor=0;
	private int previousNumberCustomer;
		
	public SuperMarketGUI() 
	{
		this.createUserInteface();
		// TODO Auto-generated constructor stub
		 ActionListener TimerActionListener = 
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							updateGui(event);
						}
					};
			
			showClock = new Timer(1000, TimerActionListener);
	}


	private void createUserInteface() 
	{
		
		welcomeLabel = new JLabel("Welcome to Sahil's Supermarket");
		welcomeLabel.setBounds(40, 50, 480, 100);
		welcomeLabel.setFont(new Font("Arial Black", Font.BOLD, 21));
		contentPane.add(welcomeLabel);
		
		startSimulationButton = new JButton("Open Sahil's Supermarket");
		startSimulationButton.setBounds(85, 175, 300, 100);
		startSimulationButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		startSimulationButton.addActionListener(
				 
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent e)
					 {	 	
						startSimulation();

					 }// end action performed
					 
				 }//End class Action
				 
		);//End Action Listener
		contentPane.add(startSimulationButton);
		
		this.cashierLabel = new JLabel("Cashier");
		this.cashierLabel.setBounds(0,140,200,100);
		this.cashierLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		this.cashierLabel.setVisible(false);
		contentPane.add(this.cashierLabel);
		
		this.timeJLabel = new JLabel("00:00:00");
		this.timeJLabel.setBounds(175, 40, 100, 100);
		this.timeJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		this.timeJLabel.setVisible(false);
		contentPane.add(this.timeJLabel);
		
		
		for(int i=0; i<this.customerButtons.length-1;i++)
		{
		
			customerButton = new JButton("YOU");
			customerButton.setBounds(this.nextXBounds, this.nextYBounds, 80, 80);
			customerButton.setFont(new Font("Arial Black", Font.BOLD, 12));
			customerButton.setVisible(false);
			customerButton.addActionListener(
					 
					 new ActionListener()
					 {
						 public void actionPerformed(ActionEvent e)
						 {	 	
							sendIndex(e);
						 }// end action performed
						 
					 }//End class Action
					 
			);//End Action Listener
			contentPane.add(customerButton);
			this.customerButtons[i] = customerButton;
			
			if(this.nextXBounds<400 && this.ismovingRight)
			{
				this.nextXBounds+=80;
			}
			else if(this.nextXBounds==400&&this.ismovingRight)
			{
				this.ismovingRight=false;
				this.ismovingLeft=true;
				this.nextYBounds+=80;
			}
			else if(this.nextXBounds>0 && this.ismovingLeft)
			{
				this.nextXBounds-=80;
			}
			else if(this.nextXBounds==0 && this.ismovingLeft)
			{
				this.ismovingRight=true;
				this.ismovingLeft=false;
				this.nextYBounds+=80;
			}
			
		}
		
		clickedcustomerID = new JLabel("Customer ID:");
		clickedcustomerID.setBounds(500, 100, 200, 40);
		clickedcustomerID.setFont(new Font("Arial Black", Font.BOLD, 15));
		clickedcustomerID.setVisible(false);
		contentPane.add(clickedcustomerID);
		
		clickedfirstName = new JLabel("FirstName:");
		clickedfirstName.setBounds(500, 200, 200, 40);
		clickedfirstName.setFont(new Font("Arial Black", Font.BOLD, 15));
		clickedfirstName.setVisible(false);
		contentPane.add(clickedfirstName);
		
		clickedlastName = new JLabel("LastName:");
		clickedlastName.setBounds(500, 300, 200, 40);
		clickedlastName.setFont(new Font("Arial Black", Font.BOLD, 15));
		clickedlastName.setVisible(false);
		contentPane.add(clickedlastName);
		
		clickedQueueTime = new JLabel("Queue Time:");
		clickedQueueTime.setBounds(500, 400, 200, 40);
		clickedQueueTime.setFont(new Font("Arial Black", Font.BOLD, 15));
		clickedQueueTime.setVisible(false);
		contentPane.add(clickedQueueTime);
		
		
		clickcustomerID = new JTextArea("");
		clickcustomerID.setBounds(650, 100, 100, 40);
		clickcustomerID.setFont(new Font("Arial Black", Font.BOLD, 20));
		clickcustomerID.setVisible(false);
		clickcustomerID.setEditable(false);
		contentPane.add(clickcustomerID);
		
		clickfirstName = new JTextArea("");
		clickfirstName.setBounds(650, 200, 300, 40);
		clickfirstName.setFont(new Font("Arial Black", Font.BOLD, 20));
		clickfirstName.setVisible(false);
		clickfirstName.setEditable(false);
		contentPane.add(clickfirstName);
		
		clicklastName = new JTextArea("");
		clicklastName.setBounds(650, 300, 300, 40);
		clicklastName.setFont(new Font("Arial Black", Font.BOLD, 20));
		clicklastName.setVisible(false);
		clicklastName.setEditable(false);
		contentPane.add(clicklastName);
		
		clickQueueTime = new JTextArea("");
		clickQueueTime.setBounds(650, 400, 120, 40);
		clickQueueTime.setFont(new Font("Arial Black", Font.BOLD, 20));
		clickQueueTime.setVisible(false);
		clickQueueTime.setEditable(false);
		contentPane.add(clickQueueTime);
		
		showQueueSize = new JLabel("The Current Queue Size is: ");
		showQueueSize.setBounds(75, 100, 400, 80);
		showQueueSize.setFont(new Font("Arial Black", Font.BOLD, 20));
		showQueueSize.setVisible(false);
		contentPane.add(showQueueSize);
		
		
		this.setTitle("SuperMarket");
		this.setLayout(null);
		this.setSize(500,500);
		this.setVisible(true);
		this.setLayout(null);
	}


	private void sendIndex(ActionEvent e) 
	{
		for(int i=0; i< this.customerButtons.length;i++)
		{
			if(e.getSource() == this.customerButtons[i])
			{
				this.displayedIndex = i;
			}
		}
		showClickedCustomer();
		
	}


	public void startSimulation() 
	{
		this.ifStarted = true;
		this.startSimulationButton.setVisible(false);
		showSimulation();
		 superMarket = new Supermarket();
		//System.out.println(test.getSuperMarketTime());
		 superMarket.openSuperMarketSimulation();
		 
		this.showClock.start();
//		System.out.println("YO");
		
	}


	private void showSimulation() 
	{
		// Show the simulation thing :)
		this.welcomeLabel.setText("Sahil's SuperMarket");
		this.welcomeLabel.setBounds(100, -40, 480, 180);
		this.cashierLabel.setVisible(true);
		this.timeJLabel.setVisible(true);
		clickedcustomerID.setVisible(true);
		clickedfirstName.setVisible(true);
		clickedlastName.setVisible(true);
		clickedQueueTime.setVisible(true);
		clickcustomerID.setVisible(true);
		clickfirstName.setVisible(true);
		clicklastName.setVisible(true);
		clickQueueTime.setVisible(true);
		showQueueSize.setVisible(true);
		this.setSize(1000,520);


		
	}
	
	
	public void updateGui(ActionEvent e)
	{
		reShowTime();
		//SHOW THE BUTTONS
		showQueueButtons();
		showClickedCustomer();
		
		
		
		
	}
	
	private void showClickedCustomer()
	{
		try{
		if(superMarket.getQueueManager().getQueue().getShoppingQueue().size() != 0)
		{
			this.clickcustomerID.setText(String.valueOf(superMarket.getQueueManager().getQueue().getShoppingQueue().get(displayedIndex).getCustomerID()));
			this.clickfirstName.setText(String.valueOf(superMarket.getQueueManager().getQueue().getShoppingQueue().get(displayedIndex).getFirstName()));
			this.clicklastName.setText(String.valueOf(superMarket.getQueueManager().getQueue().getShoppingQueue().get(displayedIndex).getLastName()));
			this.clickQueueTime.setText(String.valueOf(superMarket.getQueueManager().getQueue().getShoppingQueue().get(displayedIndex).getQueueClock().getDisplayTime()));
		}
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
	}


	private void showQueueButtons() 
	{
		if(this.superMarket.getQueueManager().getQueueSize() !=0)
		{
		if(this.previousNumberCustomer != this.superMarket.getQueueManager().getQueue().getShoppingQueue().get(0).getCustomerID())
		{
			this.nextColor++;
			this.previousNumberCustomer = this.superMarket.getQueueManager().getQueue().getShoppingQueue().get(0).getCustomerID();
		}
		}
		
			
		//RENAME THE BUTTONS 
		this.shoppingQueue = superMarket.getQueueManager().getQueue();
		int queueSize = this.superMarket.getQueueManager().getQueueSize();
		if(queueSize < 19)
		{
		if(queueSize == this.numofButtonsVisible && queueSize !=0)
		{
			for(int i=0; i<this.numofButtonsVisible;i++)
			{
				this.customerButtons[i].setText(String.valueOf(this.shoppingQueue.getShoppingQueue().get(i).getCustomerID()));
				this.customerButtons[i].setVisible(true);
			}
		}
		else
		{
			//NEED TO ADD ONE 
//			if(this.numofButtonsVisible <19)
//			{
			if(queueSize > this.numofButtonsVisible)
			{
				this.numofButtonsVisible++;
				this.customerButtons[this.numofButtonsVisible-1].setText(String.valueOf(this.shoppingQueue.getShoppingQueue().get(this.numofButtonsVisible-1).getCustomerID()));
				this.customerButtons[this.numofButtonsVisible-1].setVisible(true);
			}
//			}
			if(queueSize < this.numofButtonsVisible)
			{
				this.numofButtonsVisible--;
				this.customerButtons[this.numofButtonsVisible].setVisible(false);;
			}
			
		}
		}
		else
		{
			for(int i=0; i<this.numofButtonsVisible;i++)
			{
				this.customerButtons[i].setText(String.valueOf(this.shoppingQueue.getShoppingQueue().get(i).getCustomerID()));
				this.customerButtons[i].setVisible(true);
			}
		}
		
		
		switch(this.nextColor%3)
		{
		case 0:this.customerButtons[0].setBackground(Color.GREEN);
		break;
		case 1:this.customerButtons[0].setBackground(Color.CYAN);
		break;
		case 2:this.customerButtons[0].setBackground(Color.YELLOW);
		break;
		}
		
		
	}


	public void reShowTime()
	{
		
			UniversalClock temp =this.superMarket.getSuperMarketClock();
			this.timeJLabel.setText(temp.getDisplayTime());	 
			
			this.showQueueSize.setText("The Current Queue Size is: " + superMarket.getQueueManager().getQueueSize());
	}
	
	public boolean getIsStarted()
	{
		return this.ifStarted;
	}
	
	

}
