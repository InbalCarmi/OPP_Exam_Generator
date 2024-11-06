package roy_207200585_inbal_212053326;

import java.io.Serializable;

public class MultiQuestion extends Question implements Serializable {
	//private static final int MAX_ANSWERS=10;	// no need because the length of the answer array is 10 so if we need to use this feature we use answer.length  
	private Answer[] answers = new Answer[10]; /*
	                                           we can make the array with length ten because it's the maximum answers a question can receive, 
	                                           and the nulls in the array doesn't affect us because we know how many answers we have
	                                           */
	private int numOfAnswers;
	//constructors
	public MultiQuestion(String t,Difficult D)
	{
		super(t,D);
		numOfAnswers=0;
	}
	public MultiQuestion(MultiQuestion q)
	{
		super(q.text,q.getDifficult());
		for(int i=0;i<q.getNumOfAnswers();i++)
			this.addAnswer(q.getAnswers()[i]);
		numOfAnswers=q.numOfAnswers;
	}
	// setters
	public boolean addAnswer(Answer a)
	{
		if(numOfAnswers<8||a.getT().equals("More than one answer is correct")||a.getT().equals("No answers are correct")) /*
		                                                                                                                     less then 8 because we always need to add 2 answers according to the question
		                                                                                                                     and if we have more than 8 we can only add the answers that are writing
		                                                                                                                    */
		{
			answers[numOfAnswers++] = a;
			return true;
		}
		else
			return false;	
	}
	// getters
	public int getNumOfAnswers()
	{
		return numOfAnswers;
	}
	public Answer[] getAnswers()
	{
		return answers;
	}
	public void deleteAnswer(int index)  throws MultiQuestionException // instead of checking that the user is not picking a question with less then 3 asnwer, we are not allowing him to have a question like that from the first place
	{
			
		if(numOfAnswers>=index-1&&index>=1&&numOfAnswers>2) /* index-1 - because if the user picked question number 1, in the array its numbered 0
		                                                        checks (index-1) positive
		                                                        we need more than 2 answers in order to delete one because we cant have a question with one answer
		                                                        checks (index-1) in range of array
		                                                        the swaps itself - takes the answer at the end, and swaps the answer at (index-1) then making the last answer null and decreasing the number of answers by 1
		                                                     */ 
		{
	    Answer temp = new Answer(answers[numOfAnswers-1]);
		answers[index-1]=temp;
		answers[numOfAnswers-1]=null;
		numOfAnswers--;
		}
			else
				throw new MultiQuestionException("Cant have less then  2 answer\\ index not valid");
	}
	public int getCorrectAnswers() // checks how many corrects answer there are.
	{
		int count =0;
		for(int i=0;i<numOfAnswers;i++)
		{
			if(answers[i].isCorrect())
				count++;
		}
		return count;
	}
	@Override
	public String toString() // prints the question with the solutions 
	{
		StringBuffer res = new StringBuffer("id: "+super.id+"\n"+"Difficulty: "+super.TheDifficult+"\n"+this.text+"\n"+"Answers:"+"\n");
		for(int i=0;i<numOfAnswers;i++)
			res.append((i+1) +"."+ answers[i].toString() +"\n");
		return res.toString();
	}
	@Override
	public String printWithoutAnswer() // prints the question without the solutions
	{
		StringBuffer res = new StringBuffer(this.text+"\n"+"Answers:"+"\n");
		for(int i=0;i<numOfAnswers;i++)
			res.append((i+1)+"."+answers[i].printWithoutA()+"\n");
		return res.toString();
	}
	@Override
	public String printToFile()//print the test with answers to a file without color 
	{
		StringBuffer res = new StringBuffer("id: "+super.id+"\n"+"Difficulty: "+super.TheDifficult+"\n"+this.text+"\n"+"Answers:"+"\n");
		for(int i=0;i<numOfAnswers;i++)
			res.append((i+1) +"."+ answers[i].printToFile() +"\n");
		return res.toString();
	}
}