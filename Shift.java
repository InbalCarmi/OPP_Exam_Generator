package roy_207200585_inbal_212053326;

import java.sql.Date;
import java.util.Comparator;

 class CompareByDate implements Comparator<Shift>
{

	@Override
	public int compare(Shift o1, Shift o2) {
		return o1.getD().compareTo(o2.getD());
	}
	
}


public class Shift implements Comparable <Shift>{
//private HairDresser[] h;
private int numOfHairDresser;
private Date d;
private double startHour;
private double endHour;
//private Treatments[] treatmentsForShift;
private int numOfTreatment;

public Shift(int numHairD,Date date,int start,int end,int numOfT)//HairDresser[] h1,Treaments[] t)
		{
	     setStartHour(start);
	     setEndHour(end);
	     d=date;
	     setNumOfHair(numHairD);
	     setNumOfTreat(numOfT);	     
		}
public void setStartHour(double h) {
	if(h>0&&h<24) 
		startHour=h;
}
public void setEndHour(double h) {
	if(h>0&&h<24) 
		endHour=h;
	}
  public void setNumOfHair(int num)
  {
	  if(num>0)
		  numOfHairDresser=num;
  }
  public void setNumOfTreat(int num)
  {
	  if(num>0)
		  numOfTreatment=num;
  }
public double getStartHour () {
	return startHour;
}
public double getEndHour () {
	return endHour;
}

public int getNumOfHairDresser() {
	return numOfHairDresser;
}
public Date getD() {
	return d;
}
public void setD(Date d) {
	this.d = d;
}
public int getNumOfTreatment() {
	return numOfTreatment;
}
// public void addTreatment(Treatment t)
// public void addHairDresesr( HairDresser d)

@Override
public int compareTo(Shift s) { // by hour
	if(this.startHour<s.startHour) return -1;
	else if(this.startHour>s.startHour) return 1;
	else return 0;
}
public  int showProfit()
{
	int profit=0;
	//for(int i=0;i<treatmentsForShift.length;i++) 
	//{
	// if(treatmentsForShift.getClient instanceof ClientVIP)
	//        profit-=10;
	//	profit+=treatmentsForShift[i].getPrice;
	//}
	
	// for (int j=0;i<h.length;j++)
	//	if(h[j] instanceof seniorHairDresser)
	//	    profit-=10;
	//
	return profit;
	
	
}



}

