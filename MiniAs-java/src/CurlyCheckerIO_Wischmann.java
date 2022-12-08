package src;
import java.io.File;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.EmptyStackException;
/**
 * Name: CurlyCheckerIO_Wischmann 
 * Date: 11/14/2022 Author: Chris Wischmann
 * 
 * This program makes use of a Stack to decide if Curly Braces in a designated
 * Java file are used and also paired accordingly as a psuedo-syntax checker for any Java file.
 */
public class CurlyCheckerIO_Wischmann{
    /**
     * Name Field of file, holds name of file so it is accessible by both methods
     */
    String name;
    /**
     * The getBraces method takes a String, which would be the name of the text file.
     * It reads the file, and returns Char[] that has a copy of every Open and Closed
     * Curly Brace contained in the program.
     * 
     * @return Char[]
     * @param fileName
     */
    public char[] getBraces(String fileName) {
        name = fileName;
        Scanner reader;
        ArrayList<Character> braces = new ArrayList<>();
        String input;
        char[] inputArr;
        char[] returnArr = {};

        try {
            File file = new File(fileName);
            reader = new Scanner(file);

            while (reader.hasNext()) {
                input = reader.nextLine();
                inputArr = input.toCharArray();
                for (char character : inputArr) {
                    if (character == '{') {
                        braces.add('{');
                    } 
                    else if (character == '}') {
                        braces.add('}');
                    }
                }
            }

            returnArr = new char[braces.size()];
            for (int index = 0; index < returnArr.length; index++) {
                returnArr[index] = braces.get(index);
            }
            reader.close();
        } 
        catch (Exception exception) {
            System.out.println("File not found. Exiting program.");
            System.exit(0);
        }
        return returnArr;
    }
    /**
     * The countBraces method takes Char[], and uses a Stack to count the number
     * of braces in the given program. It then, prints if or not the Curly Braces are evenly matched and
     * that the file would run, based upon the pushing and popping values from the stack.
     * 
     * char[]
     * @param arr
     */
    public void countBraces(char[] arr) {
        Stack<Character> stackChecker = new Stack<Character>();
        int openBraceCounter = 0;
        int closeBraceCounter = 0;
        int totalBraces = arr.length;

        // Counts Number of Braces
        for (int index = 0; index < totalBraces; index++) {
            if (arr[index] == '{') {
                openBraceCounter++;
            }
        }
        closeBraceCounter = totalBraces - openBraceCounter;

        // Must be even number of braces for file run
        if ((totalBraces % 2) != 0) {
            System.out.println(name + " contains a total of " + totalBraces + " Braces, with " + openBraceCounter
                    + " Open Braces and " + closeBraceCounter + " Close Braces."
                    + "\nBrace Count is Odd. This means the Braces are Uneven. The file will not run.");
            return;
        }
        // Stack is used to see if or not the braces are balanced in the program.
        for (int index = 0; index < totalBraces; index++) {
            if (arr[index] == '{') {
                stackChecker.push(arr[index]);

            } else if (arr[index] == '}') {
                try {
                    stackChecker.pop();
                } catch (EmptyStackException exception) {
                    // Condition is checked where the pattern of the braces is similar to {}} in the file.
                    System.out.println(name + " contains a total of " + totalBraces + " Braces, with "
                            + openBraceCounter + " Open Braces and " + closeBraceCounter + " Close Braces."
                            + "The Braces are Uneven, and the file will not run, due to missing Open Brace(s)");
                    return;
                }
            }
        }
        /**
         * If the Stack were to be Empty, it would mean that the Braces are Even. 
         * If not its not empty, it would then mean that there is an
         * excess of Open Braces in the program.
         */
        if (stackChecker.isEmpty()) {
            System.out.println(name + " contains a total of " + totalBraces + " Braces, with " + openBraceCounter
                    + " Open Braces, and Close Braces." + "\n The Braces are even and the file " + name + " will run.");
        } else {
            System.out.println(name + " contains a total of " + totalBraces + " Braces, with " + openBraceCounter
                    + " Open Braces and " + closeBraceCounter + " Close Braces."
                    + "\n The braces are not even, and the file will fail to run, due to excess, unpaired, Open Brace(s).");
        }
    }

    /**
     * The main method allows a user to test multiple files.
     * But it will only run the test on Both SportsTeam1.java, and SportsTeam2.java 
     * prior to the interaction from the User.
     */
    public static void main(String args[]) {
        String fileName = "SportsTeam1.java";
        Boolean flag = true;
        Scanner keyboard = new Scanner(System.in);
        CurlyCheckerIO_Wischmann curlyBraces = new CurlyCheckerIO_Wischmann();

        System.out.println("Testing File Checker...");

        char[] braces = curlyBraces.getBraces(fileName);
        curlyBraces.countBraces(braces);

        fileName = "SportsTeam2.java";
        braces = curlyBraces.getBraces(fileName);
        curlyBraces.countBraces(braces);

        while (flag) {
            System.out.println("Do you wish to test another file? Type Yes or No");

            String placeholder = keyboard.nextLine();

            if (placeholder.toUpperCase().equals("YES")) {
                System.out.println(
                        "Enter the name, or fully qualified path, of another file, followed by the .java extension");
                fileName = keyboard.nextLine();
                braces = curlyBraces.getBraces(fileName);
                curlyBraces.countBraces(braces);
            } else {
                System.out.println("Have a Nice Day.");
                flag = false;
            }
        }
        keyboard.close();
    }

}