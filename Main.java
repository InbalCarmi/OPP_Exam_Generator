package roy_207200585_inbal_212053326;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Scanner;

import roy_207200585_inbal_212053326.Question.Difficult;

public class Main {
	public static Question[] readPoll(String filename) throws ClassNotFoundException, IOException
	{
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(filename));
		Question[] poll2 = (Question[])inFile.readObject();
		inFile.close();
		return poll2;
	}
	public static void writePoll(String filename,Question[] poll) throws FileNotFoundException, IOException
	{
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(filename));
		outFile.writeObject(poll);
		outFile.close();
	}
    public static void modiftyAnswersForTest(Question[] poll) // when he wants to create a test we modify the questions according to the task, only for multi Questions
    {
		 
	    for(int i=0;i<poll.length;i++)
	  {
	    	if(poll[i] instanceof MultiQuestion)
	    	{
	    		MultiQuestion temp = (MultiQuestion) poll[i];
		  int count = temp.getCorrectAnswers();
		  if(count>1)
		  {
		  for(int j=0;j<temp.getNumOfAnswers();j++)
			  temp.getAnswers()[j].setIfCorrect(false);
			Answer a1 = new Answer("More than one answer is correct",true);
			temp.addAnswer(a1);
			Answer a2 = new Answer("No answers are correct",false);
			temp.addAnswer(a2);
		  }
		  if(count==0)
		  {
				Answer a1 = new Answer("More than one answer is correct",false);
				temp.addAnswer(a1);
				Answer a2 = new Answer("No answers are correct",true);
				temp.addAnswer(a2);
		  }
		  if(count==1)
		  {
				Answer a1 = new Answer("More than one answer is correct",false);
				temp.addAnswer(a1);
				Answer a2 = new Answer("No answers are correct",false);
				temp.addAnswer(a2);
		  }
	    	}
	  }
    }
    
    public static void modifyQuestion(Question[] poll, int numOfQuestionToBeEdit)
    {
    	
    	Scanner s = new Scanner(System.in);
 		if(poll[numOfQuestionToBeEdit-1] instanceof MultiQuestion) // checks what type is the question
{
		System.out.println("Do you want to delete any answers?");        // for multiQuestion you can delete answers and add
		MultiQuestion temp = (MultiQuestion)poll[numOfQuestionToBeEdit-1];
		boolean delete = s.nextBoolean();
		if(delete) {
			System.out.println("Enter the number of answer you wish to delete");
			int numOfA=s.nextInt();
			
			try
			{
				temp.deleteAnswer(numOfA);
			}
			catch(MultiQuestionException e){
				System.out.println(e.getMessage());
			}
			
			System.out.println("The current question and the answer:"); //printing the question after the change
			System.out.println(poll[numOfQuestionToBeEdit-1]);
			
	            	}
		System.out.println("Do you want to add answers?");
		boolean add = s.nextBoolean();
		if(add)
		{
			System.out.println("Enter the text of the answer");
			String text = s.next();
			String text2 = s.nextLine();
			System.out.println("Enter True or False for the answer");
			boolean status = s.nextBoolean();
			Answer a = new Answer(text+text2,status);
			if((!temp.addAnswer(a)))
				System.out.println("Cant add more than 8 answers"); // Can't have more than 8 answers added
			else									//print the question after the edit
			{
			System.out.println("the currest question and the answer's:");
			System.out.println(poll[numOfQuestionToBeEdit-1]);
			}
		}
		System.out.println("Do you want to Change Difficult?");
		boolean change = s.nextBoolean();
		if(change)
		{
			System.out.println("Enter Difficult (Hard,Easy,Medium)");
			String dif =s.next();
			if(dif.equals("Hard")||dif.equals("Easy")||dif.equals("Medium"))
			{
				 Difficult d = Difficult.valueOf(dif);
				poll[numOfQuestionToBeEdit-1].setDif(d);
			}
			else
				System.out.println("Invalid input");
		}
 	}
 		else 													    // if the question is open you can only replace the text
 		{
 			System.out.println("Do you want to replace the answer?");
 			boolean replace = s.nextBoolean();
 			if(replace)
 			{
 				System.out.println("enter the new answer text");
				String text = s.next();
				String text2 = s.nextLine();
				OpenQuestion temp = (OpenQuestion)poll[numOfQuestionToBeEdit-1];
				temp.setAnswer(text+text2);
 				
 			}
 			System.out.println("Do you want to change difficult?");
 			boolean change = s.nextBoolean();
 			if(change)
 			{
 				System.out.println("Enter Difficult (Hard,Easy,Medium)");
 				String dif =s.next();
 				if(dif.equals("Hard")||dif.equals("Easy")||dif.equals("Medium"))
 				{
 					 Difficult d = Difficult.valueOf(dif);
 					poll[numOfQuestionToBeEdit-1].setDif(d);
 				}
 				else
 					System.out.println("Invalid input");
 			}
 			
 		}
    }
	public static void main(String[] args) throws FileNotFoundException,IOException,ClassNotFoundException {
		
		
		// Shift is in the project, not by hand
		
		/*
		   We talk with victor about the functions DeleteQuestion and AddQuestion, we thought it was more reasonable
		   to delete\add question from the test and not from the poll because we wrote the poll and if we wanted to delete\add
		   questions from the poll we would do it manually,victor approved and said to write how we understood the task.
		   Please change the location of Poll in line 150, preferably project location
		   If you want to print the text to other location, it is located in class text line 63, else it will write to the project file
		 */
		Question[][] polls = new Question[2][]; // polls length is the number of profession needed, named poll 0-length, if you want to add another
		                                        // poll you add another file with poll-i- to the poll file, so you can have unlimited amount of profession just need to update the polls length
		for(int i=0;i<polls.length;i++)
		{
			polls[i] = readPoll(".\\Poll"+i+".txt"); // number of Poll-i- is important for the program to work, please dont change the polls name
		}
        Scanner s = new Scanner(System.in);
        System.out.println("enter 0 for Trivia, enter 1 for Math");
        int index = s.nextInt();
		Question[] poll= polls[index]; // enter the location of the poll here we have made it to be in the project library

		System.out.println("\u001B[33mPlease answer True\\False to any yes no questions");
		System.out.println("Please create a test only after you made any changes necessary to the questions\u001B[0m");
		boolean fContinue = true;
		while(fContinue)                  
		{
			System.out.println("\u001B[30m\u001B[1mEnter 1 to view all the questions from the poll");
			System.out.println("Enter 2 to modify an answer from a question");
			System.out.println("Enter 3 to create a test");
			System.out.println("Enter 4 to create a automatic test");
			System.out.println("Enter 5 to exit\u001B[0m");
			                                               
			                                               //  The user can change the questions how many times he would like, when he enters 3 he cannot go back and re edit the questions.
			                                               
			int choice1 = s.nextInt();
			switch(choice1)
			{
			case 1:
				for(int i=0;i<poll.length;i++)
				{
					System.out.println((i+1)+"."+poll[i]);
					try {
						Thread.sleep(500);                // Prints the questions slower
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				break;
				
			case 2:
				int numOfQuestionToBeEdit;
				do
				{
				  System.out.println("Pick the number of question you want to edit  (pick between 1-"+poll.length+")");
				  numOfQuestionToBeEdit=s.nextInt();
				}
				while(numOfQuestionToBeEdit<=0||numOfQuestionToBeEdit>poll.length); // Checks number is valid, if not he'll keep getting the same message
				System.out.println(poll[numOfQuestionToBeEdit-1]);
			     modifyQuestion(poll,numOfQuestionToBeEdit);
			     writePoll(".\\Poll"+index+".txt",poll); // we save the changes to the poll
						break;
						
			case 3:
				modiftyAnswersForTest(poll); // modify the multi question answers.
				ManuelExam m = new ManuelExam();
				Test test = m.createExam(poll);
				boolean[] arrayCountQuestions = new boolean[poll.length+1];
				for(int i=0;i<test.getNumOfQuestions();i++)
				{
					arrayCountQuestions[test.getQuestion(i).getId()]= true; // setting the array of questions
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				while(fContinue)
			{
					System.out.println("\u001B[30m\u001B[1mEnter 1 to delete a question from the test");
					System.out.println("Enter 2 to add a question to the test");
					System.out.println("Enter 3 to print the test to a file, soultion and exam");
					System.out.println("Enter 4 to print the test with the solution");
					System.out.println("Enter 5 to print the test without the solution");
					System.out.println("Enter 6 To exit\u001B[0m");
				
					int choice2 =s.nextInt();
					switch(choice2) {
					case 1:
						System.out.println("Enter number of question to delete");
						int numQuestionToDelete = s.nextInt();
						if(!(test.deleteQuestion(numQuestionToDelete)))
							System.out.println("Either there is only one question, or number of question is not valid");
						else
						{
						   arrayCountQuestions[numQuestionToDelete]=false;
						   System.out.println("Question deleted successfully");
						}
					break;
					
					case 2:
						System.out.println("Enter num of question to add");
						int numOfQuestionToAdd=s.nextInt();
						if(numOfQuestionToAdd>0&&numOfQuestionToAdd<=poll.length&&!(arrayCountQuestions[numOfQuestionToAdd])) // checks if the question is in the test all ready, or number of question is not valid
						{
							try {
								test.addQuestion(poll[numOfQuestionToAdd-1]);
								arrayCountQuestions[numOfQuestionToAdd]=true;
								System.out.println("Question added successfully");
								}
								catch(TestException t)
								{
								   System.out.println(t.getMessage());
								}
						}
						else
							System.out.println("Not a valid number \\ cant add the same question");
						break;
					
					case 3:
                              test.printTestToText();
                              System.out.println("Test printed");
							break;
							
					case 4: 
						for(int i=0;i<test.getNumOfQuestions();i++)
						{
							System.out.println((i+1)+"."+test.getQuestion(i));
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						break;
						
					case 5:
						for(int i=0;i<test.getNumOfQuestions();i++) 
						{
							System.out.println((i+1)+"."+test.getQuestion(i).printWithoutAnswer());
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						break;
						
					case 6:
						fContinue = false;
						System.out.println("Bye :)");
						break;
						default: System.out.println("Not a valid number");
						
				}
			}	
				break; //for case 3
				
			case 4:
				AutomaticExam e = new AutomaticExam();
				
				modiftyAnswersForTest(poll);
				Test test2 = e.createExam(poll);
				System.out.println("Test Created, bye :)");
				fContinue = false;
				break;
				
			case 5:
				fContinue = false;
				break;
				
				default: System.out.println("Not valid");
				
				
		  }
		}
		s.close();
		
       }
}