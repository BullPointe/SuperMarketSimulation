import java.util.ArrayList;
import java.util.Random;

public class QueueManager {
	
	private int customerID = 1;
	
	private Customer nextCustomer = null;
	private int nextCustomerEnqueueTime;

	private ShoppingQueue shoppingQueue;

	private int firstCustomerDequeueTime;
	private ArrayList<String> BoyNames = null;
	private ArrayList<String> GirlNames = null;
	
	private int longestQueueSize=0;
	private Customer longestPersoninQueue = null;
	private String longestQueueSizeTime;

	
	
	

	public QueueManager(ShoppingQueue queue) 
	{
		// TODO Auto-generated constructor stub
		this.shoppingQueue = queue;
		
		//READ
		ReadFile reader1 = new ReadFile("GirlNames");
		ReadFile reader2 = new ReadFile("BoyNames");
		try {
			this.GirlNames = reader1.getList();
			this.BoyNames = reader2.getList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Customer analyzeState(UniversalClock superMarketTime, DataCollector reporter) //GETS THE TIME EVERYTIME LOOP RUNS IN SUPERMARKET
	{
		//System.out.println(superMarketTime.getSeconds());

		//FIGURE OUT WHAT TO DO 
		//WHAT SHOULD I DO BASED ON THE MARKET TIME
		
		//CHECK THE NEXT CUSTOMER
		
		//CHECK IF I SHOULD MAKE A NEW CUSTOMER TO START SHOPPING
		
		if(this.nextCustomer == null)//NEED TO MAKE ONE
		{
			createNextCustomer(superMarketTime);
		}
		else
		{//THERE IS A CUSTOMER SHOPPING 
			
			//System.out.println(superMarketTime.getSeconds() >= this.nextCustomerEnqueueTime);
			
			//CHECK IF WE SHOULD ENQUEUE 
			if(superMarketTime.getSeconds() < this.nextCustomerEnqueueTime)
			{
				
			}	
			else
			{
				System.out.println("ENQUEUE");
				
				
				if(shoppingQueue.getShoppingQueue().size()!=0)
				{
					this.nextCustomer.startQueueClock();
				}
				this.shoppingQueue.enqueue(this.nextCustomer);
				        
				//SET THE DEQUEUE TIME FOR THE PERSON 
				
				if(shoppingQueue.getShoppingQueue().size()==1)
				{
				this.firstCustomerDequeueTime = superMarketTime.getSeconds() + this.nextCustomer.getServingTime();
				System.out.println("NEXT DEQUEUE TIME: " + this.firstCustomerDequeueTime);
				}
				
				System.out.println("DONE SHOPPING ADDED TO QUEUE");
				System.out.println("THE SIZE OF THE QUEUE IS: " + shoppingQueue.getShoppingQueue().size() );
				System.out.println(" ");
				
				String s = this.nextCustomer.getFirstName() +"  "+this.nextCustomer.getLastName() + " Was Enqueued";
				reporter.reporter(s);
				
				createNextCustomer(superMarketTime);
				
				checkQueueSize(superMarketTime);
				
				
			}
		}
		
		
		//CHECK THE CUSTOMER WHO IS GETTING SERVED
		
		if(shoppingQueue.getShoppingQueue().isEmpty())
		{
			//NOTHING TO DO/CHECK
			//MOVE ON 
		}
		else
		{
			//CHECK THE FIRST PERSON IN THE QUEUE -----> THE ONLY GUY TO CHECK
			if(this.firstCustomerDequeueTime <= superMarketTime.getSeconds())
			{
				//DEQUEUE THE PERSON (THEIR TIME IS UP)
				
				//this.shoppingQueue.getShoppingQueue().get(0).stopQueueClock();
				Customer dequeuedCustomer = this.shoppingQueue.getShoppingQueue().get(0);
				
				//TO SEND HIM OTHER PLACE IF WANTED
				
				this.shoppingQueue.dequeue();
				
				//CALCULATE NEW PERSON's DEQUEUE TIME(NEXT IN LINE)
				
				if(!(shoppingQueue.getShoppingQueue().isEmpty()))
				{
					//NEW DEQUEUE TIME
					this.firstCustomerDequeueTime = superMarketTime.getSeconds() + 
							this.shoppingQueue.getShoppingQueue().get(0).getServingTime();    
					
					this.shoppingQueue.getShoppingQueue().get(0).stopQueueClock();	
					
					System.out.println("NEXT DEQUEUE TIME: " + this.firstCustomerDequeueTime);
				}	
				
				checkLongestCustomer(dequeuedCustomer);
				
				System.out.println("DEQUEUED!!!!");
				System.out.println("THE SIZE OF THE QUEUE IS: " + shoppingQueue.getShoppingQueue().size() );
				System.out.println(" ");
				
				String s = dequeuedCustomer.getFirstName() +"  "+dequeuedCustomer.getLastName() + " Was Dequeued";
				reporter.reporter(s);
				
				return dequeuedCustomer;
			}	
		}
		
		return null;
	}
	

	private void checkLongestCustomer(Customer dequeued) 
	{
		// TODO Auto-generated method stub
		if(this.longestPersoninQueue==null|| dequeued.getQueueTime() >= this.longestPersoninQueue.getQueueTime())
		{
				System.out.println(dequeued.getQueueTime());
				dequeued.stopQueueClock();
				this.longestPersoninQueue = dequeued;
		}
	}

	private void checkQueueSize(UniversalClock time) 
	{
		if(shoppingQueue.getShoppingQueue().size() >= longestQueueSize)
		{
			longestQueueSize = shoppingQueue.getShoppingQueue().size();
			this.longestQueueSizeTime = time.getDisplayTime();
		}
		
	}

	private void createNextCustomer(UniversalClock superMarketTime)
	{
			this.nextCustomer = null;
			//JUST CHECK IF NULL IN CASE
		
			Random gen = new Random();
			String first;
			int gender = gen.nextInt(2);
			if(gender == 0)
			{
				//FEMALE
				first = GirlNames.get(gen.nextInt(GirlNames.size()-1));
			}
			else
			{
				//MALE
				first = BoyNames.get(gen.nextInt(BoyNames.size()-1));

			}
			
			this.nextCustomer = new Customer(this.customerID,gender,first);
			this.customerID++;
			
			//CALCULATE THE TIME THE CUSTOMER NEEDS TO ENTER THE QUEUE ;)
			this.nextCustomerEnqueueTime = superMarketTime.getSeconds() + this.nextCustomer.getShoppingTime();
			
			System.out.println("CREATED A NEW PERSON WHO IS SHOPPING");
			
			System.out.println("CUSTOMER WILL BE ADDED AT: " + this.nextCustomer.getShoppingTime()+", "  +this.nextCustomerEnqueueTime);
			System.out.println(" ");
			//NOW WE WAIT
	
	}
	
	public void dequeueEveryone()
	{
		ShoppingQueue nothing = new ShoppingQueue();
		this.shoppingQueue = nothing;
	}
	
	public int getLongestQueueSize()
	{
		return longestQueueSize;
	}
	
	public Customer getLongestCustomer()
	{
		return this.longestPersoninQueue;
	}
	
	int getQueueSize()
	{
		return this.shoppingQueue.getShoppingQueue().size();
	}
	
	ShoppingQueue getQueue()
	{
		return this.shoppingQueue;
	}
	
	String getLongestQueueSizeTime()
	{
		return this.longestQueueSizeTime;
	}
	
	

}
