import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Supermarket {
	
	private SuperMarketGUI gui;
	private UniversalClock superMarketTime;
	private Timer timerLoop;
	private QueueManager queueManager;
	private DataCollector dataManager;
	
	private int superMarketSeconds;
	private ShoppingQueue shoppingQueue = new ShoppingQueue();

	public Supermarket() {
		
		// TODO Auto-generated constructor stub
//		gui = new SuperMarketGUI();
		superMarketTime = new UniversalClock();
		ActionListener timerActionListener = 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						loopTimerActionPerformed(event);
					}
				};
		timerLoop = new Timer(1000,timerActionListener);
		
	}
	
	private void loopTimerActionPerformed(ActionEvent event) 
	{
//		gui.reShowTime(superMarketTime);
		//System.out.println(superMarketTime.getSeconds());
		if(superMarketTime.getSeconds()<8)
		{
//			System.out.println(superMarketTime.getSeconds());
			//WHILE THE SUPERMARKET IS OPEN
			//RUN THE QUEUE MANAGER-----> MANAGES THE CUSTOMERS WHO ENTER THE MARKET, DEQUEUES THE CUSTOMERS, ENQUEUES THE CUSTOMERS
			Customer dequedCustomer = queueManager.analyzeState(superMarketTime,dataManager);
			if(!(dequedCustomer==null))
			{
				System.out.println(dequedCustomer.getFirstName() + " "+ dequedCustomer.getLastName() + " " + dequedCustomer.getGender());
				dataManager.addReport(dequedCustomer);
			}
			//RETURNS THE DEQUED PERSON< NULL IS NO ONE IS DEQUED
			
		}//ENDS AFTER 24 HOURS 
		else
		{
			timerLoop.stop();
			superMarketTime.timerStop();
			closeSuperMarketSimulation();
		}
	}

	void openSuperMarketSimulation()
	{
		//Start THE WHOLE THING :))
		
		//INITIATE THE OBJECT
		
		queueManager = new QueueManager(shoppingQueue);
		dataManager = new DataCollector();
		timerLoop.start();
		superMarketTime.timerStart();
//		while(timerLoop.isRunning())
//		{
//			
//		}
		
		
		//CLOSE THE MARKET :(((
	}
	
	
	private void closeSuperMarketSimulation()
	{
		// TODO Auto-generated method stub
		
		//FINISH THE SIMULATION
		
		System.out.println("CLOSED THE MARKET");
		System.out.println(" ");
		dataManager.reporter("---------------------------THE SUPER MARKET CLOSED---------------------------");
		dataManager.printResults(this.queueManager.getLongestQueueSize(),this.queueManager.getLongestCustomer(),this.queueManager.getLongestQueueSizeTime());
		dataManager.printReport();
		queueManager.dequeueEveryone();
	}

	int getSuperMarketTime()
	{
		return superMarketTime.getSeconds();
	}
	
	UniversalClock getSuperMarketClock()
	{
		return this.superMarketTime;
	}
	
	boolean isSuperMarketDone()
	{
		if(this.timerLoop.isRunning())//SECONDS IN A DAY
		{
			return false;
			
		}
		else
		{
			superMarketTime.timerStop();
			return true;
		}
	}
	
	
	QueueManager getQueueManager()
	{
		return this.queueManager;
	}
	
	
	
	

}
