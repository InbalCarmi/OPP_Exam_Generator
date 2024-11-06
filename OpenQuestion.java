package roy_207200585_inbal_212053326;

import java.io.Serializable;

public class OpenQuestion extends Question implements Serializable {
	private String answer;
	
	public OpenQuestion(String text, Difficult Dif, String answer) {
		super(text, Dif);
		setAnswer(answer); 
	}
	public OpenQuestion(OpenQuestion q)
	{
		super(q.text,q.getDifficult());
		answer=q.answer;
	}
	
	public void setAnswer(String ans) { // cant have a question without a text
		if(ans!=""||ans==null)// 
		answer=ans;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	
	@Override
	public String toString() { // prints with answer
       return "id: "+super.id+"\n"+"Difficulty: "+super.TheDifficult+"\n"+text+"\n"+"\u001B[1m"+answer+"\u001B[0m"+"\n";
	}
	@Override
    public String printWithoutAnswer()
    {
		return super.text+"\n";
    }
	@Override
	public String printToFile()
	{
		return "id: "+super.id+"\n"+"Difficulty: "+super.TheDifficult+"\n"+text+"\n"+answer+"\n";
	}

}
