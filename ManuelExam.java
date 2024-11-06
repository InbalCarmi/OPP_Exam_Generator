package roy_207200585_inbal_212053326;

import java.util.Scanner;

public class ManuelExam implements Examable {

	@Override
	public Test createExam(Question[] poll) {
		Scanner s = new Scanner(System.in);
		int numOfQ = 0;
		Test test =null;
		boolean check = true;
		while(check)
		{
           System.out.println("Enter number of Question (pick between 1 - 10)");
           numOfQ=s.nextInt();
           try
           {
        	   test= new Test(numOfQ); 
        	   check = false;
           }
           catch(TestException e)
           {
        	   System.out.println(e.getMessage());
           }
		}
		
		int numOfQuestionToBeAddedToTheTest;
		boolean[] arrayCountQuestions = new boolean[poll.length+1]; // the slots 1-poll.length indicates true - if question is in the test already, false if not.
		
		for(int i=0;i<numOfQ;i++)
		{
			do{
			System.out.println("Enter question number to be added to the test"+"(pick between 1-"+poll.length+")");
			numOfQuestionToBeAddedToTheTest=s.nextInt();}
			while(numOfQuestionToBeAddedToTheTest<=0||numOfQuestionToBeAddedToTheTest>poll.length); // checks if num is valid
			
			if(!(arrayCountQuestions[numOfQuestionToBeAddedToTheTest])) // checks if the questions is in the test
			{
				try {
				test.addQuestion(poll[numOfQuestionToBeAddedToTheTest-1]);
				arrayCountQuestions[numOfQuestionToBeAddedToTheTest]=true;
				}
				catch(TestException t)
				{
				   System.out.println(t.getMessage());
				}
			
			}
			else
				System.out.println("cant add same question twice");
		}
		System.out.println("Test created successfully");
		return test;

	}

}
