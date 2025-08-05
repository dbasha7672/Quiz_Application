package com.Gqt.Java.Project;
import java.util.*;
/**
 * @author Basha
 * @category Quiz app
 * @description This is Console-based Quiz Application 
 */
class Rules{
	void DisplyRules() {
		System.out.println("**********Welcome to the GAME**********");
	    System.out.println("********** GAME RULES **********");
	    System.out.println("1. You will be asked some questions.");
	    System.out.println("2. Each correct answer earns you 1000 rupees.");
	    System.out.println("3. If you answer incorrectly, the game ends.");
	    System.out.println("4. If you answer is incorrect between 1-5 you don't get any amount.");
	    System.out.println("5. If you answer is incorrect between 6-7 you will get sum of amount till 5th question.");
	    System.out.println("6. If you answer is incorrect between 8-10 you will get sum of amount till 7th question.");
	    System.out.println("7. You have two lifelines: '50-50' and 'Call a Friend'.");
	    System.out.println("8. Each lifeline can be used only once.");
	    System.out.println("9. If you select quit option then whatever you earned previously will be given.");
	    System.out.println("10. If you answer all questions correctly, you win the game.");
	    System.out.println("*********************************");
	}
}
class Question {
    Options o = new Options();

    boolean askQuestion(int qcount, GResult r) {
        switch (qcount) {
            case 1:
                return o.showQuestion(qcount, r, 'b',
                        "What is the capital of India?",
                        new String[]{"a. Hyderabad", "b. Delhi", "c. Bengaluru", "d. Chennai"});
            case 2:
                return o.showQuestion(qcount, r, 'b',
                        "What is the national animal of India?",
                        new String[]{"a. Lion", "b. Tiger", "c. Elephant", "d. Leopard"});
            case 3:
                return o.showQuestion(qcount, r, 'c',
                        "Who is the President of India (2025)?",
                        new String[]{"a. Ram Nath Kovind", "b. Narendra Modi", "c. Droupadi Murmu", "d. Rahul Gandhi"});
            case 4:
                return o.showQuestion(qcount, r, 'a',
                        "What is the currency of India?",
                        new String[]{"a. Rupee", "b. Dollar", "c. Euro", "d. Yen"});
            case 5:
                return o.showQuestion(qcount, r, 'b',
                        "Which is the largest state in India by area?",
                        new String[]{"a. Maharashtra", "b. Rajasthan", "c. Uttar Pradesh", "d. Madhya Pradesh"});
            case 6:
                return o.showQuestion(qcount, r, 'c',
                        "Which of these is NOT a classical dance form of India?",
                        new String[]{"a. Bharatanatyam", "b. Kathak", "c. Salsa", "d. Odissi"});
            case 7:
                return o.showQuestion(qcount, r, 'd',
                        "What is the name of the Indian space agency?",
                        new String[]{"a. NASA", "b. ESA", "c. JAXA", "d. ISRO"});
            case 8:
                return o.showQuestion(qcount, r, 'c',
                        "In which city is the Taj Mahal located?",
                        new String[]{"a. Mumbai", "b. Delhi", "c. Agra", "d. Jaipur"});
            case 9:
                return o.showQuestion(qcount, r, 'a',
                        "What is the name of the Indian Parliament?",
                        new String[]{"a. Sansad", "b. Congress", "c. Duma", "d. Reichstag"});
            case 10:
                return o.showQuestion(qcount, r, 'b',
                        "Which of these is a major festival celebrated in India?",
                        new String[]{"a. Halloween", "b. Diwali", "c. Punakha Drubchen", "d. Dashain"});
        }
        return false;
    }
}
class Options {
    Scanner s = new Scanner(System.in);
    boolean lifeline50 = false, lifelineCF = false; // lifeline flags

    boolean showQuestion(int qcount, GResult r, char correct, String ques, String[] opts) {
        System.out.println("\nQ" + qcount + ". " + ques);
        for (String op : opts) System.out.println(op);

        while (true) {
            System.out.println("\n\u001B[34m Select any one of the option\u001B[0m ");
            System.out.println("1. Answer a question");
            System.out.println("2. Quit the game");
            if (!lifeline50) System.out.println("3. 50-50");
            if (!lifelineCF) System.out.println("4. Call a friend");

            int choice = s.nextInt();

            switch (choice) {
                case 1: // answer
                    System.out.print("\u001B[34mSelect your answer (a/b/c/d):\u001B[0m  ");
                    char ans = s.next().charAt(0);
                    return r.res(qcount, ans, correct);

                case 2: // quit
                    System.out.println("\u001B[33mYou quit the game with " + r.amt + " rupees.\u001B[0m");
                    return false;

                case 3: // 50-50
                    if (!lifeline50) {
                        lifeline50 = true;
                        System.out.println("\u001B[33m50-50 Lifeline: Correct + one wrong option:\u001B[0m");
                        System.out.println(opts[correct - 'a']);
                        for (String op : opts) {
                            if (op.charAt(0) != correct) {
                                System.out.println(op);
                                break;
                            }
                        }
                        System.out.print("\u001B[34mSelect your answer (a/b/c/d):\u001B[0m  ");
                        char ans50 = s.next().charAt(0);
                        return r.res(qcount, ans50, correct);
                    } else {
                        System.out.println("50-50 already used!");
                    }
                    break;

                case 4: // Call a Friend
                    if (!lifelineCF) {
                        lifelineCF = true;
                        System.out.println("\u001B[33mYour friend suggests option: " + correct + "\u001B[0m");
                        System.out.print("\u001B[34mSelect your answer (a/b/c/d):\u001B[0m  ");
                        char ansCF = s.next().charAt(0);
                        return r.res(qcount, ansCF, correct);
                    } else {
                        System.out.println("Call a Friend already used!");
                    }
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
class GResult {
    int amt = 0;

    boolean res(int qcount, char ans, char correct) {
        if (ans == correct) {
            amt += 10000;
            // ✅ Green congratulations message
            System.out.println("\u001B[32mCongratulations! Correct Answer. You won 10000 rupees.\u001B[0m");
            System.out.println("\u001B[33mTill now you have won " + amt + " rupees.\u001B[0m");

            // If last question also correct → full win in Purple
            if (qcount == 10) {
                System.out.println("\u001B[35m🎉 Congratulations! You answered all questions correctly and WON the game! 🎉\u001B[0m");
                System.out.println("\u001B[32mTotal Amount Won: " + amt + " rupees.\u001B[0m");
                return false; // end game
            }
            return true;
        } else {
            // milestone fallback
            if (qcount <= 5) amt = 0;
            else if (qcount <= 7) amt = 50000;
            else amt = 70000;

            // ❌ Red sorry message
            System.out.println("\u001B[31mSorry! Wrong Answer.\u001B[0m");
            System.out.println("\u001B[31mGame Over. You take home " + amt + " rupees.\u001B[0m");
            return false;
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Rules rl = new Rules();
        Question q = new Question();
        GResult r = new GResult();
     
        int qcount = 1;
        boolean gameOn = true;

        rl.DisplyRules();
        
        while (gameOn && qcount <= 10) {
            gameOn = q.askQuestion(qcount, r);
            if (gameOn) qcount++;
        }
        s.close();
    }
}




