package Task8;
import java.util.*;

public class QuizApp {
    
    static class QuizQuestion {
        String question;
        String[] options;
        int correctAnswer; 

        public QuizQuestion(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
    private static List<QuizQuestion> allQuestions;
    private static List<QuizQuestion> selectedQuestions;
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static final int QUIZ_LENGTH = 5; 
    public static void main(String[] args) {
        initializeAllQuestions();
        selectRandomQuestions();
        displayWelcomeMessage();
        conductQuiz();
        displayResults();
        scanner.close();
    }
    
    private static void initializeAllQuestions() {
        allQuestions = new ArrayList<>();
        allQuestions.add(new QuizQuestion(
            "What is the main method signature in Java?",
            new String[]{"public static void main(String args[])", 
                        "public void main(String[] args)", 
                        "static void main(String[] args)", 
                        "public main(String[] args)"},
            0
        ));

        allQuestions.add(new QuizQuestion(
            "Which of the following is NOT a Java primitive data type?",
            new String[]{"int", "float", "String", "boolean"},
            2
        ));

        allQuestions.add(new QuizQuestion(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", 
                        "Java Variable Method", 
                        "Java Verified Module", 
                        "Java Vector Memory"},
            0
        ));

        
        allQuestions.add(new QuizQuestion(
            "Which loop is guaranteed to execute at least once?",
            new String[]{"for loop", "while loop", "do-while loop", "enhanced for loop"},
            2
        ));

        allQuestions.add(new QuizQuestion(
            "What is the output of: for(int i=0; i<3; i++) System.out.print(i);",
            new String[]{"123", "012", "0123", "321"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "Which keyword is used to exit from a loop?",
            new String[]{"exit", "break", "stop", "return"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "Which concept allows a class to inherit properties from another class?",
            new String[]{"Polymorphism", "Encapsulation", "Inheritance", "Abstraction"},
            2
        ));

        allQuestions.add(new QuizQuestion(
            "What is method overloading?",
            new String[]{"Same method name, different parameters", 
                        "Same method name, same parameters", 
                        "Different method name, same parameters", 
                        "Calling a method multiple times"},
            0
        ));


        allQuestions.add(new QuizQuestion(
            "Which collection allows duplicate elements?",
            new String[]{"Set", "ArrayList", "HashSet", "TreeSet"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "What is the default size of ArrayList in Java?",
            new String[]{"5", "10", "15", "16"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "Which interface does ArrayList implement?",
            new String[]{"Set", "Map", "List", "Queue"},
            2
        ));


        allQuestions.add(new QuizQuestion(
            "Which block is always executed in exception handling?",
            new String[]{"try", "catch", "finally", "throw"},
            2
        ));

        allQuestions.add(new QuizQuestion(
            "What type of exception is NullPointerException?",
            new String[]{"Checked Exception", "Unchecked Exception", "Compile-time Exception", "User-defined Exception"},
            1
        ));

        
        allQuestions.add(new QuizQuestion(
            "What is the size of int data type in Java?",
            new String[]{"16 bits", "32 bits", "64 bits", "8 bits"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "Which access modifier makes a member accessible only within the same package?",
            new String[]{"private", "public", "protected", "default"},
            3
        ));

        allQuestions.add(new QuizQuestion(
            "What does the 'static' keyword mean in Java?",
            new String[]{"Variable cannot be changed", 
                        "Method belongs to class, not instance", 
                        "Variable is private", 
                        "Method is abstract"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "Which method is called when an object is created?",
            new String[]{"main()", "constructor", "finalize()", "init()"},
            1
        ));

        allQuestions.add(new QuizQuestion(
            "What is the parent class of all classes in Java?",
            new String[]{"String", "Class", "Object", "System"},
            2
        ));

        allQuestions.add(new QuizQuestion(
            "Which operator is used for string concatenation in Java?",
            new String[]{"+", "&", "*", "||"},
            0
        ));
    }

    
    private static void selectRandomQuestions() {
        selectedQuestions = new ArrayList<>();
        List<QuizQuestion> tempList = new ArrayList<>(allQuestions);

        
        Collections.shuffle(tempList);

        for (int i = 0; i < Math.min(QUIZ_LENGTH, tempList.size()); i++) {
            selectedQuestions.add(tempList.get(i));
        }
    }

    
    private static void displayWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     RANDOM JAVA QUIZ APPLICATION     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Welcome to the Random Java Programming Quiz!");
        System.out.println("📚 " + QUIZ_LENGTH + " questions will be randomly selected from our question bank");
        System.out.println();
        System.out.println("Instructions:");
        System.out.println("• Answer each question by selecting option 1, 2, 3, or 4");
        System.out.println("• Type your answer and press Enter");
        System.out.println("• Questions are randomly shuffled for each quiz session");
        System.out.println("• Your final score will be displayed at the end");
        System.out.println();
        System.out.print("Press Enter to start your random quiz...");
        scanner.nextLine();
        System.out.println();
    }

    private static void conductQuiz() {
        for (int i = 0; i < selectedQuestions.size(); i++) {
            QuizQuestion currentQuestion = selectedQuestions.get(i);
            displayQuestion(i + 1, currentQuestion);
            int userAnswer = getUserAnswer();
            if (userAnswer == currentQuestion.correctAnswer + 1) {
                score++;
                System.out.println("✓ Correct! Well done!");
            } else {
                System.out.println("✗ Incorrect! The correct answer was: " + 
                                 (currentQuestion.correctAnswer + 1) + ") " + 
                                 currentQuestion.options[currentQuestion.correctAnswer]);
            }

            System.out.println("Current Score: " + score + "/" + (i + 1));
            System.out.println("─────────────────────────────────────");
            System.out.println();
        }
    }

    private static void displayQuestion(int questionNumber, QuizQuestion question) {
        System.out.println("🎯 Question " + questionNumber + "/" + QUIZ_LENGTH);
        System.out.println(question.question);
        System.out.println();
        String[] shuffledOptions = Arrays.copyOf(question.options, question.options.length);
        for (int i = 0; i < shuffledOptions.length; i++) {
            System.out.println((i + 1) + ") " + shuffledOptions[i]);
        }
        System.out.println();
    }

    private static int getUserAnswer() {
        int answer = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Your answer (1-4): ");
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Please enter an answer.");
                    continue;
                }

                answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Please enter a valid number (1, 2, 3, or 4).");
            }
        }

        return answer;
    }

    private static void displayResults() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         QUIZ COMPLETED!              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        double percentage = (double) score / QUIZ_LENGTH * 100;

        System.out.println("FINAL RESULTS:");
        System.out.println("─────────────────");
        System.out.println("Correct Answers: " + score + "/" + QUIZ_LENGTH);
        System.out.println("Percentage: " + String.format("%.1f", percentage) + "%");
        System.out.println();

        
        System.out.print("Performance Level: ");
        if (percentage >= 90) {
            System.out.println("EXCELLENT! Outstanding Java knowledge!");
        } else if (percentage >= 80) {
            System.out.println("VERY GOOD! Strong understanding of Java concepts!");
        } else if (percentage >= 70) {
            System.out.println(" GOOD! Solid foundation in Java programming!");
        } else if (percentage >= 60) {
            System.out.println("FAIR! Keep practicing to improve your skills!");
        } else if (percentage >= 50) {
            System.out.println("NEEDS WORK! Focus on studying Java fundamentals!");
        } else {
            System.out.println("KEEP LEARNING! More practice needed with Java concepts!");
        }

        System.out.println();
        System.out.println("Thank you for taking the Random Java Quiz! 🚀");
    }
}