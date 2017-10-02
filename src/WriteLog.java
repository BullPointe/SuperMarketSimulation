import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {

	private String fileName;
	private String totaltoWrite = new String();
	private FileWriter stream;
	private BufferedWriter out ;
	private WriteTable tableWrite;
	public WriteLog(String fileName) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName + ".txt";
		try {
			stream = new FileWriter(this.fileName);
			out = new BufferedWriter(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableWrite = new WriteTable("LOGTABLE");
		 
	}
	
	void writetoCollect(Object[] recievedData)
	{
		for(int i=0; i<recievedData.length-1; i++)
		{
			totaltoWrite += String.valueOf(recievedData[i]) + "        |        ";
		}
	}
	
	void writetoFile(Object[] recievedData)
	{
		System.out.println("PRINTING TO FILE :))");
		try {
			
			
			String hallo = new String();
			for(int i=0; i<recievedData.length; i++)
			{
				switch(i)
				{
					case 0: hallo+= "Customer ID: " + recievedData[i]+"   ";
					break;
					case 1:	hallo+= "Gender: " + recievedData[i]+"   ";
					break;
					case 2:hallo+= "Name: " + recievedData[i]+" "+recievedData[i+1] +"   ";
					break;
					case 4:hallo+= "Shopping Time: " + recievedData[i]+"   ";
					break;
					case 5:hallo+= "Queue Time: " + recievedData[i]+"   ";
					break;
					case 6:hallo+= "Service Time: " + recievedData[i]+"   ";
					break;
					case 7:hallo+= "Review: " + recievedData[i];
					break;
				}
				
			}
			out.newLine();
			out.write(hallo);
			out.newLine();
			
			this.tableWrite.writetoFile(recievedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void reportToFile(String s)
	{
		try {
			out.newLine();
			out.write(s);
			out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void writeHeader()
	{
		Object[] temp = {"Customer ID     ","Gender  ","First Name         ","Last Name       ","Shopping Time      ","Time in Queue          ","Service Time            ","Review Ratings           "};
//		try {
//			out.write("Customer ID|");
//			out.write("          Gender          |");
//			out.write("          First Name          |");
//			out.write("          Last Name           |");
//			out.write("          Shopping Time          |");
//			out.write("          Time in Queue          |");
//			out.write("          Service Time          |");
//			out.write("          Review Ratings          |");
//			out.newLine();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		tableWrite.writetoFile(temp);
	}
	
	void closeWrite()
	{
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableWrite.closeWrite();
	}

}
