package roy_207200585_inbal_212053326;

import java.io.Serializable;

public class Answer implements Serializable { 
	private String text;
	private boolean isCorrect;
	public Answer(String t,boolean isCorrect)
	{
		setT(t);
		this.isCorrect=isCorrect;
	}
	public Answer(Answer a)
	{
		this.text = a.text;
		this.isCorrect= a.isCorrect;
		
	}
	// setter
	public void setIfCorrect(boolean isC)
	{
		this.isCorrect=isC;
	}
	public void setT(String t) 
	{
		if(t!=""||t==null)
			text=t;
	}
	//getters
	public String getT()
	{
		return text;
	}
	public boolean isCorrect()
	{
		return isCorrect;
	}
	public String toString()
	{
		if(this.isCorrect)
		return  "\u001B[32m"+this.text+"\u001B[0m";
		else
			return "\u001B[31m"+this.text+"\u001B[0m";
	}
	public String printToFile() {
		return this.text +"--->"+this.isCorrect;
	}
	public String printWithoutA() // prints without indicating the correct answer 
	{
		return this.text;
	}
	}
