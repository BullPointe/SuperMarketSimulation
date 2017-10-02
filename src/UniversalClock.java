import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class UniversalClock {

	private Timer uniClock;
	private int seconds;
	
	public UniversalClock() 
	{
		// TODO Auto-generated constructor stub\
		
		ActionListener uniTimerActionListener = 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						uniTimerActionPerformed(event);
					}
				};
		
		uniClock = new Timer(1000, uniTimerActionListener);
	}

	private void uniTimerActionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		seconds++;
	}
	
	public int getSeconds()
	{
		return seconds;
	}
	
	public void timerStart(){
		uniClock.start();
	}
	
	public void timerStop(){
		uniClock.stop();
	}
	
	public void timerRestart(){
		uniClock.restart();
	}
	
	public String getDisplayTime()
	{
		String tempTime;
		int temphours;
		int tempminutes = 0;
		int tempseconds = 0;
		if(seconds%60 == 0)
		{
			tempminutes = seconds/60;
		}
		else
		{
			tempminutes = seconds/60;
			tempseconds = seconds%60;
		}
		if(tempminutes%60== 0)
		{
			temphours = tempminutes/60;
		}
		else
		{
			temphours = tempminutes/60;
			tempminutes = tempminutes%60;
		}
		
		
		//FORMATING
		
		if(temphours<10)
		{
			tempTime = "0"+temphours +":";
		}
		else
		{
			tempTime = String.valueOf(temphours) + ":";
		}
		if(tempminutes<10)
		{
			tempTime += "0"+tempminutes + ":";	
		}
		else
		{
			tempTime += tempminutes + ":";
		}
		if(tempseconds<10)
		{
			tempTime += "0"+tempseconds;
		}
		else
		{
			tempTime += tempseconds;
		}
		
		
		return tempTime;
		
	}

}
