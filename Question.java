package roy_207200585_inbal_212053326;

import java.io.Serializable;

public abstract class Question  implements Serializable {
	protected String text;
	public enum Difficult {Hard,Medium,Easy};
	protected Difficult TheDifficult;
	private static int idGen=1;
	protected int id;
	
	public Question(String text,Difficult Dif)
	{
		this.text=text;
		TheDifficult=Dif;
		id=idGen++;
	}
	
	public void setText(String text)
	{
		if(text!=""&&text!=null)
			this.text=text;
	}
	//getters
	
	public int getId()
	{
		return id;
	}
	public Difficult getDifficult()
	{
		return TheDifficult;
	}
	public String getText()
	{
		return text;
	}
	public void setDif(Difficult D)
	{
		TheDifficult=D;
	}
	public abstract String toString();
	public abstract String printWithoutAnswer();
	public abstract String printToFile();
	
	

}
