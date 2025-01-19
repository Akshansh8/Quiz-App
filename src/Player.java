
public class Player implements Runnable {
	int score ;
	public Player(int num) {
		System.out.println("Player number "+num);
	}
	
	public void run() {
		QuestionService service = new QuestionService();
		  service.playQuiz();
		  score = service.printScore();
	}
	
	public int getScore() {
		return score;
	}
	

}
