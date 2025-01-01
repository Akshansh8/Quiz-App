import java.util.NoSuchElementException;
import java.util.Scanner;

public class QuestionService {
	Questions[] questions = new Questions[5];
	String[] selection = new String[5];
	
	public QuestionService()
	{
		questions[0] = new Questions(1, "size of int", "2", "6", "4", "8", "4");
		questions[1] = new Questions(2, "size of double", "2", "6", "4", "8", "8");
        questions[2] = new Questions(3, "size of char", "2", "6", "4", "8", "2");
        questions[3] = new Questions(4, "size of long", "2", "6", "4", "8", "8");
        questions[4] = new Questions(5, "size of boolean", "1", "2", "4", "8", "1");
	}
	
	public void displayQuestions()

	{
		 for(Questions question : questions) {
	            System.out.println(question.toString());
	        }
	}
	
	public void playQuiz()
	{
		int i  = 0;
		for(Questions q: questions)
		{
			System.out.println("Question  "+(i+1)+": ");
			System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println("type the right answer:");
            try {
            Scanner sc = new Scanner(System.in);
            selection[i] = sc.nextLine().trim();
            }
            
            catch (NoSuchElementException e) {
            	System.out.println("Invalid input. Please enter an answer.");
                selection[i] = "9"; // Set a default value or ask again
            }
            
            i++;
		}
	}
	
	public void printScore()
	{
		int score = 0;
		for(String s : selection){
            System.out.println(s);
        }
		
		for (int i = 0; i<5; i++)
		{
			Questions q = questions[1];
			String answer = q.getAnswer();
			String userAnswer = selection[i];
			
			if(answer.equals(userAnswer)) score++;
		}
		System.out.println("result : "+ score);
	}

}
