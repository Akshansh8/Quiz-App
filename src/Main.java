
public class Main {
	public static void main(String[] args) throws InterruptedException
	{
	  Player p1 = new Player(1);
	  Player p2 = new Player(2);
	  
	  Thread  t1 = new Thread(p1);
	  Thread t2 = new Thread(p2);
	  
	  t1.start();
	  
	  t1.join();
	  
	  t2.start();
	  
	  t2.join();
	  
	  if(p1.getScore()>p2.getScore()) {
		  System.out.println("Player 1 is the winner");
	  }
	  else {
		System.out.println("Player 2 is the winner");
	}
	}

}
