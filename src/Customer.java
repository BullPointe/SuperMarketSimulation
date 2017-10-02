import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Customer 
{
	private String firstName;
	private String lastName;
	private UniversalClock queueTime;
	private int shoppingTime;
	private int servingTime;
	private int queueSeconds;
	private int budget;
	private String gender;
	private int customerId;
	private int rating;
	
	private String[] LastNames= {"SMITH","JOHNSON","WILLIAMS","JONES","BROWN","DAVIS","MILLER","WILSON","MOORE","TAYLOR","ANDERSON","THOMAS","JACKSON","WHITE","HARRIS","MARTIN","THOMPSON","GARCIA","MARTINEZ","ROBINSON","GRAY "    ,     " RAMIREZ "  ,    "JAMES "    ,    " WATSON "    ,   "BROOKS "    ,  "KELLY"  ,   "SANDERS "    ,   "PRICE "  ,       "BENNETT "   ,    "WOOD "    ,      "BARNES " ,      "ROSS"    ,      " HENDERSON " ,   " COLEMAN",        "JENKINS ",       
			"PERRY",         " POWELL "   ,    "LONG "    ,      "PATTERSON"   , "HUGHES"    ,    
			"FLORES "   ,     "WASHINGTON"   ,  "BUTLER"     ,   "SIMMONS"   ,     "FOSTER"    ,     "GONZALES"      , "BRYANT" ,        "ALEXANDER" ,     "RUSSELL"     ,   "GRIFFIN",       " DIAZ",          "HAYES"  ,        "MYERS " ,        "FORD"  ,         "HAMILTON"  ,     
			"GRAHAM"   ,      "SULLIVAN"  ,     "WALLACE" ,     " WOODS"     ,     "COLE " ,         
			"WEST"  ,         "JORDAN"    ,     "OWENS"  ,        "REYNOLDS ",      "FISHER",         
			"ELLIS"  ,        "HARRISON "    ,  "GIBSON"      };
	
	
	public Customer(int id,int gender, String first) 
	{	
		// TODO Auto-generated constructor stub
		Random gen = new Random();
		setFirstName(first);
		setLastName(lastName= LastNames[gen.nextInt(LastNames.length-1)]);
		setGender(gender);
		setRating(gen);
//		shoppingTime = (gen.nextInt(4) + 1)*60;
//		servingTime = (gen.nextInt(4) + 1)*60;
		shoppingTime = (gen.nextInt(4) + 1);
		servingTime = (gen.nextInt(4) + 1);
//		shoppingTime = 1;
//		servingTime = 5;
		customerId = id;
		
		queueTime = new UniversalClock();
		
	}
	
	private void setGender(int i) {
		// TODO Auto-generated method stub
		if(i==1)
		{
			this.gender = "Male";
		}
		else
		{
			this.gender = "Female";
		}
	}
	
	public String getGender()
	{
		return this.gender;
	}

	private void setFirstName(String name)
	{
		firstName = name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	private void setLastName(String name)
	{
		name =name.replaceAll("\\s", "");
		name= name.toLowerCase();
		name = name.substring(0,1).toUpperCase() + name.substring(1); 
		
		this.lastName= name;
	}
	
	public String getLastName()
	{
		
		return lastName;
	}
	
	int getCustomerID()
	{
		return this.customerId;
	}
	
	int getServingTime()
	{
		return this.servingTime;
	}
	
	int getShoppingTime()
	{
		return this.shoppingTime;
	}
	
	public Object[] getCustomerDetails()
	{
		Object[] temp = {getCustomerID(),getGender(),getFirstName(),getLastName(),
				getShoppingTime(),getQueueTime(),getServingTime(),getRating()};
		return temp;
	}
	
	private void setRating(Random gen)
	{
		this.rating = gen.nextInt(11);
	}
	
	public int getRating()
	{
		return this.rating;
	}
	
	UniversalClock getQueueClock()
	{
		return queueTime;
	}
	
	public int getQueueTime()
	{
		return queueTime.getSeconds();
	}
	
	void startQueueClock()
	{
		queueTime.timerStart();
	}
	
	void stopQueueClock()
	{
		queueTime.timerStop();
	}
	
	void restartQueueClock()
	{
		queueTime.timerRestart();
	}
	
	
	
	
	
}
