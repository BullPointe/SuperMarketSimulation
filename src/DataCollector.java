import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataCollector {

	private JTable customerData;
	private DefaultTableModel model = new DefaultTableModel();
	private WriteLog logReport;
	
	public DataCollector() 
	{
		// TODO Auto-generated constructor stub
		customerData = new JTable(model);
		model.addColumn("Customer ID");
		model.addColumn("Gender");
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Shopping Time");
		model.addColumn("Time in Queue");
		model.addColumn("Service Time");
		model.addColumn("Review Ratings");
		
		logReport = new WriteLog("Report");
		logReport.writeHeader();
		
		
		
	}
	
	public void addReport(Customer recieved)
	{
		Object[] recievedData = recieved.getCustomerDetails();
		model.addRow(recievedData);
		logReport.writetoFile(recievedData);
	}
	
	public void reporter(String s)
	{
		logReport.reportToFile(s);
	}
	
	
	public void printReport()
	{
		//CALCULATE
		
		 for(int row = 0; row < customerData.getRowCount(); row++) {
		        for(int column = 0; column < customerData.getColumnCount(); column++) {
		            System.out.print(customerData.getColumnName(column) + ": ");
		            System.out.println(customerData.getValueAt(row, column));
		        }
		        System.out.println(""); 
		 }
		 
		 int sum = 0;
		 for(int row =0; row<customerData.getRowCount(); row++)
		 {
			sum += (int)customerData.getValueAt(row, 5);
		 }
		 System.out.println("THE AVERAGE TIME IN QUEUE: " + sum/customerData.getRowCount()); 
		 reporter("THE AVERAGE TIME IN QUEUE: " + sum/customerData.getRowCount()); 
		 sum = 0;
		 for(int row =0; row<customerData.getRowCount(); row++)
		 {
			sum += (int)customerData.getValueAt(row, 4);
		 }
		 System.out.println("THE AVERAGE TIME SHOPPING: " + sum/customerData.getRowCount());
		 reporter("THE AVERAGE TIME SHOPPING: " + sum/customerData.getRowCount());
		 sum=0;
		 for(int row =0; row<customerData.getRowCount(); row++)
		 {
			sum += (int)customerData.getValueAt(row, 6);
		 }
		System.out.println("THE AVERAGE TIME IN SERVING: " + sum/customerData.getRowCount());
		reporter("THE AVERAGE TIME IN SERVING: " + sum/customerData.getRowCount());
		logReport.closeWrite();
		 
//		 logReport.writetoFile();
	}
	
	public void printResults(int size, Customer longest, String s)
	{
		String temp1 = "THE LONGEST QUEUE SIZE IS: " + size +" Customers"+ " AT: " + s;
		System.out.println(temp1);
		String temp2 = "THE CUSTOMER IN THE QUEUE THE LONGEST: " +longest.getFirstName()+" "
				+longest.getLastName() +" " +longest.getQueueTime() + " Seconds";
		System.out.println(temp2);
		
		reporter(temp1);
		reporter(temp2);
	}

}
