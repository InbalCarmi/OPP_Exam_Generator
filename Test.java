package roy_207200585_inbal_212053326;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Test  implements Serializable {
    private Question[] question;
    private int numOfQuestion;
    
    public Test(int Questions) throws TestException
    {
    	if(Questions<=10)
    	{
    	this.question = new Question[Questions];
    	numOfQuestion=0;
    	}
    	else
    		throw new TestException("Too much Questions");
    }
    
    public void addQuestion(Question q) throws TestException
                                        /* 
                                           if we want to add another question and we are *full* in the array,(we didn't learn lists yet)
                                            so we make another array with length+1 as length, copy the entire array and add the question required at the end.
                                        */
    
    {
    	if(numOfQuestion<10)
    	{
    	if(numOfQuestion>=question.length)
    	{
    		Question[] res = Arrays.copyOf(question,numOfQuestion+1);
    		res[numOfQuestion++]=q;
    		question = res;
    	}
    	else
    	{
    		question[numOfQuestion++]=q;
    	}
    	}
    	else
    		throw new TestException("Too much questions");
    }
    public boolean deleteQuestion(int index) // because the order of the questions is important, we made the index of the question null, and we *push* it to the end
    {
    	if(numOfQuestion>1&&numOfQuestion>=index&&index>0)
    	{
    		question[index-1]=null;
    	for(int i=index-1;i<numOfQuestion-1;i++) {
    		question[i]=question[i+1];
    		}
    	numOfQuestion--;
    	return true;
    	}
    	else
    		return false;
    	
    }
    public Question getQuestion(int index) // didnt do index-1 because we as the coders interact with this function and not the user
    {
    	return question[index];
    }
    public int getNumOfQuestions()
    {
    	return numOfQuestion;
    }
    public void printTestToText() throws FileNotFoundException
    {
		LocalDate today = LocalDate.now();
		LocalDateTime ldt= LocalDateTime.now();
		String LocationFile = "./";   // Location to write
	    String LocationFileToWrite = LocationFile+"\\solution_"+today.getYear()+"_"+today.getMonthValue()
	                           +"_"+today.getDayOfMonth()+"_"+ldt.getHour()+"_"+ldt.getMinute()+".txt"; // prints according to the pattern in the task
	    File f = new File(LocationFileToWrite);
		PrintWriter pw = new PrintWriter(f);
		for(int i=0;i<numOfQuestion;i++)
			pw.println((i+1)+"."+question[i].printToFile());
		pw.close();
		String LocationFile2 = LocationFile+"\\exam_"+today.getYear()+"_"+today.getMonthValue()
		                       +"_"+today.getDayOfMonth()+"_"+ldt.getHour()+"_"+ldt.getMinute()+".txt";
		f = new File(LocationFile2);
	    pw= new PrintWriter(f);
		for(int i=0;i<numOfQuestion;i++)
			     pw.println((i+1)+"."+question[i].printWithoutAnswer());
		pw.close();
    }
}

