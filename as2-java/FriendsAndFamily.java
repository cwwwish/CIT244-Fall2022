package Extra;

import java.util.Scanner;

public class FriendsAndFamily {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name;
        int dc;
        int size;

        // Validate the user types in a positive size
        do {
            System.out.print("How many family");
            size = scan.nextInt ();
        }while(size <= 0);

        //Create an array of Family objects, with the size of the size variable
        Family[] families = new Family [size];

        // loop through array and create a fsmily in each index
        for (int i = 0; i < families.length; i++) {
            // Clear the buffer before we move on 
            scan.nextLine ();
            System.out.print("Enter name of family member: ");
            name = scan.nextLine ();
            System.out.print("Enter the date" + name + "was first constructed: ");
            dc = scan.nextInt ();

            // To be sure to assign each index (iTh pos) to a family object
            families[i] = new Family(name, dc);
        }
        // Prove the array is loaded with Objects
        System.out.print("/nHere are the families: ");
        for (Family family : families) {
            System.out.print(family);
        }

        // Start by assigning variable to the 0th index of the array so we can compare the rest

        int oldest = families[0].getDateConstructed();

        // A for loop to compare the rest
        for (int i = 0; i < families.length; i++) {
            // Compare each elements date
            if(families[i].getDateConstructed() < oldest) {
                oldest = families[i].getDateConstructed();
            }
        }
        System.out.println("\nThe oldest family in the array is: " + oldest);
        
    }
}