import java.util.ArrayList;

public class ShoppingQueue {
	
	private ArrayList<Customer> shoppingQueue;

	public ShoppingQueue() {
		// TODO Auto-generated constructor stub
		shoppingQueue = new ArrayList<Customer>();
	}
	
	public void enqueue(Customer o)
	{
		shoppingQueue.add(o);
	}
	
	public Object dequeue()
	{
		if(shoppingQueue.isEmpty())
		{
			return null;
		}
		else
		{
			return shoppingQueue.remove(0);
		}
	}
	
	public ArrayList<Customer> getShoppingQueue()
	{
		return this.shoppingQueue;
	}

	
}
