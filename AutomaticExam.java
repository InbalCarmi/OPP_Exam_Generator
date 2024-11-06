package roy_207200585_inbal_212053326;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class AutomaticExam implements Examable {

	@Override
	public Test createExam(Question[] poll) {
		Scanner s = new Scanner(System.in);
		int numOfQ = 0;
		Test test =null;
		boolean check = true;
		while(check)
		{
           System.out.println("Enter number of Question (pick between 1 - 10)"); //checking number is in range
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
		Random rnd = new Random();
		boolean[] arrayCountQuestions = new boolean[poll.length+1]; // every index of boolean array says if the question is in the test
		int num=0;
		for(int i=0;i<numOfQ;i++)
		{
			do
				{
				 num = rnd.nextInt(0, poll.length);
				}
			while(arrayCountQuestions[num]); // if we added the question already to the test we want to continue to random it until next question
			if(poll[num] instanceof OpenQuestion)
			{
				try
				{
				test.addQuestion(poll[num]);
				arrayCountQuestions[num]=true;
				}
				catch (TestException e){
					System.out.println(e.getMessage());
				
				}
			}
			else
			{
				MultiQuestion temp = (MultiQuestion) poll[num];
				check = true;
				for(int j=0;j<temp.getNumOfAnswers();j++) 
			{
					if (temp.getAnswers()[j].getT().equals("More than one answer is correct")&&temp.getAnswers()[j].isCorrect()) { // we cant add a question with more than one correct answer
						                                                                                                           // so we dont do i++ and we dont get in the while loop
						i--;
						check = false;
				}
		    }
				boolean[] arrayCountAnswers= new boolean[temp.getNumOfAnswers()]; //same logic as arraycountQuestions
				int num2=0;
				boolean checkAnswer=false;
				while(check)
				{
					MultiQuestion temp2 = new MultiQuestion(temp.getText(),temp.getDifficult());
					for(int j=0;j<4;j++)
					{
					do{
						 num2 = rnd.nextInt(0, temp.getNumOfAnswers());
					}
					while(arrayCountAnswers[num2]);
					if(temp.getAnswers()[num2].getT().equals("No answers are correct"))
					{
						checkAnswer=true;
					}
					
					temp2.addAnswer(temp.getAnswers()[num2]);
					arrayCountAnswers[num2]= true;
					}
					if(!(checkAnswer)) // checks if we put the answer no answers are correct, if not we add it according to number of correct answers
							{
						        if(temp2.getCorrectAnswers()==0)
						        {
						        	Answer a = new Answer("No answers are correct",true);
						        	temp2.addAnswer(a);
						        }
						        else
						        {
						        	Answer a = new Answer("No answers are correct",false);
						        	temp2.addAnswer(a);
						        }
							}
					else
					{
						if(temp2.getCorrectAnswers()==0) // if we put it we need to check if we got a correct answer, if we dont have we set the answer to be true
						{
						for(int d=0;d<temp2.getNumOfAnswers();d++)
						{
							if(temp2.getAnswers()[d].getT().equals("No answers are correct"))
									{
								temp2.getAnswers()[d].setIfCorrect(true);
									}
						}
					}
					}
					try {
					test.addQuestion(temp2);
					arrayCountQuestions[num]=true;
					check =false;
					}
					catch (TestException e)
					{
						System.out.println(e.getMessage());
					}
				}
				
				
		  }
		
	   }
		
		try {
			test.printTestToText();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return test;
	}
}


