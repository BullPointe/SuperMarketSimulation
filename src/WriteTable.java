import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTable {

	private String fileName;
	private String totaltoWrite = new String();
	private FileWriter stream;
	private BufferedWriter out ;
	public WriteTable(String fileName) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName + ".csv";
		try {
			stream = new FileWriter(this.fileName);
			out = new BufferedWriter(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	void writetoFile(Object[] recievedData)
	{
		System.out.println("PRINTING TO FILE :))");
		String tempString = "";
		try {
			for(int i=0; i<recievedData.length; i++)
			{
				tempString+= recievedData[i] +",";
			}
			out.write(tempString);
			out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void closeWrite()
	{
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
