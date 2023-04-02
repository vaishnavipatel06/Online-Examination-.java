import java.util.*;

public class Main {
	private static String username;
	private static String password;
	private static String[] mcqs;
	private static String[] answers;
	private static String[] selectedAnswers;
    private static boolean[] isAnswered;
    private static int timeLimit;
    private static Timer timer;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		username="";
		password="";
		mcqs = new String[] {"Question 1","Question 2","Question 3"};
		answers = new String[] {"A","B","C"};
		selectedAnswers = new String[mcqs.length];
		isAnswered = new boolean[mcqs.length];
		timeLimit = 20;
		
		while(true) {
			System.out.println("Welcome to the online examination System");
			System.out.println("1. Login  ");
			System.out.println("2. Update Profile and Password");
			System.out.println("3. Exit");
			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				login();
				break;
			case 2:
				updatedProfile();
				break;
			case 3:
				sc.close();
				System.exit(0);
                break;
              default:
            	  System.out.println("Invalid choice. Please try again.");
            	  break;
			}
		}
	}
	private static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username: ");
		String enteredUsername = sc.nextLine();
		System.out.print("Enter password: ");
		String enteredPassword = sc.nextLine();
		
		if(enteredUsername.equals(username)&&enteredPassword.equals(password)) {
			startExamination();	
		}else {
			System.out.println("Invalid username or password. Please try again.");
		}		
	}

	private static void updatedProfile() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter new username: ");
		username = sc.nextLine();
		System.out.println("Enter new password: ");
		password = sc.nextLine();
		System.out.println("Profile and password updated successfully.");
	}
	
	private static void startExamination() {
		Scanner sc = new Scanner(System.in);
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = timeLimit;

            @Override
            public void run() {
                if (timeLeft > 0) {

                    System.out.println("Time left: " + timeLeft + " seconds");
                    timeLeft--;
                } else {

                    timer.cancel();
                    submitExamination();
                }
            }
        }, 0, 1000);

        for (int i = 0; i < mcqs.length; i++) {
            if (!isAnswered[i]) {
                System.out.println("Q: " + mcqs[i]);
                System.out.print("Enter your answer: ");
                selectedAnswers[i] =sc.nextLine();
                isAnswered[i] = true;
            }
        }
    }

    private static void submitExamination() {
        timer.cancel();
        int score = 0;
        for (int i = 0; i < mcqs.length; i++) {
            if (answers[i].equals(selectedAnswers[i])) {
                score++;
            }
        }
        System.out.println(answers[0]+" "+answers[1]+" "+answers[2]);
        System.out.println("Examination submitted. Your score is: " + score + " out of " + mcqs.length);
        logout();
    }

    private static void logout() {
        username = "";
        password = "";
        selectedAnswers = new String[mcqs.length];
        isAnswered = new boolean[mcqs.length];
        System.out.println("You have been logged out.");
        System.exit(0);
    }
}