import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.*;

public class QuestionService {
    private final Questions[] questions = new Questions[5];
    private final String[] selection = new String[5];
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public QuestionService() {
        try {
            questions[0] = new Questions(1, "Size of int", "2", "6", "4", "8", "3");
            questions[1] = new Questions(2, "Size of double", "2", "6", "4", "8", "4");
            questions[2] = new Questions(3, "Size of char", "2", "6", "4", "8", "1");
            questions[3] = new Questions(4, "Size of long", "2", "6", "4", "8", "3");
            questions[4] = new Questions(5, "Size of boolean", "1", "2", "4", "8", "1");
        } catch (IllegalArgumentException e) {
            System.err.println("Error initializing questions: " + e.getMessage());
        }
    }

    public void playQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            Questions q = questions[i];
            System.out.println("Question " + (i + 1) + ": ");
            System.out.println(q.getQuestion());
            System.out.println("1. " + q.getOpt1());
            System.out.println("2. " + q.getOpt2());
            System.out.println("3. " + q.getOpt3());
            System.out.println("4. " + q.getOpt4());
            System.out.println("Type the right answer (You have 10 seconds):");
            
            Future<String> future = executor.submit(() -> {
                try {
                    return scanner.nextLine().trim();
                } catch (NoSuchElementException e) {
                    return ""; // Default for invalid input
                }
            });
        // Schedule a timeout task
        Future<?> timeoutTask = executor.schedule(() -> {
            if (!future.isDone()) {
                System.out.println("Time's up!");
                future.cancel(true); // Cancel input task
            }
        }, 10, TimeUnit.SECONDS);

        try {
            String userAnswer = future.get(10, TimeUnit.SECONDS); // This will return if input is provided within 10 seconds
            timeoutTask.cancel(true); // Cancel timeout if user answered on time

            if (userAnswer.isEmpty() || !userAnswer.matches("[1-4]")) {
                System.out.println("Invalid or no answer provided.");
                selection[i] = "9"; // Default for invalid or no answer
            } else {
                selection[i] = userAnswer;
            }
        } catch (TimeoutException e) {
            System.out.println("Time's up!");
            future.cancel(true); // Cancel the task
            selection[i] = "9";
        }catch (CancellationException | ExecutionException e) {
            System.out.println("No answer provided.");
            selection[i] = "9"; // Default for no answer
        } catch (InterruptedException e) {
            System.err.println("Unexpected error: " + e.getMessage());
            Thread.currentThread().interrupt();
            selection[i] = "9"; // Default for errors
        }

        System.out.println(); // Add a blank line for spacing after each question
    }

    scanner.close(); // Safely close the Scanner
    executor.shutdown();
    }

    public int printScore() {
        int score = 0;

        System.out.println("Your answers:");
        for (String s : selection) {
            System.out.println(s);
        }

        for (int i = 0; i < questions.length; i++) {
            Questions q = questions[i]; // Correctly access the question for index `i`
            String correctAnswer = q.getAnswer();
            if (correctAnswer.equals(selection[i])) {
                score++;
            }
        }

        System.out.println("Your final score is: " + score + "/" + questions.length);
        System.out.println();
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) { // Wait for 5 seconds
                System.out.println("Forcing shutdown...");
                executor.shutdownNow(); // Force shutdown if tasks are not completed
            }
        } catch (InterruptedException e) {
            System.err.println("Error during shutdown: " + e.getMessage());
            executor.shutdownNow();
        }

        System.out.println("Quiz completed. Goodbye!");
        return score;
    }
}
