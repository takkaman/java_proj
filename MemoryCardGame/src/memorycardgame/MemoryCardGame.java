package memorycardgame;

import java.util.Scanner;

public class MemoryCardGame {

    public int choice1 = 0;
    public int choice2 = 1;
    public  static String[][] animal = new String[4][4];
    Card card = new Card();

    public void StartGame() {
        Card card = new Card();
        animal = card.Random();
        int count = 1;

        System.out.println("This is a Memory Card game");
        System.out.println("you shall use your memory and maybe lucky guess to win this game");
        printPainiting();
        
        do {
            if (count % 2 == 1) {//get the first card
                System.out.println("Please choose the first card by enter a number from 1-16");
                choice1 = enter();//get rifht choice one
            } else {//get the secong card
                System.out.println("Please choose the second card by enter a number from 1-16");
                choice2 = enter();//get rifht choice two
                card.printAllCard(animal);
                System.out.println("Checking for match");
               CheckMatch(choice1, choice2);
            }
            count++;
        } while (true);
    }

    public int enter() {//ensure the data is integer 
        Scanner scan = new Scanner(System.in);
        String scanin = scan.next();
        int choice = 0;
        try {
            choice = Integer.parseInt(scanin);
        } catch (NumberFormatException e) {
            System.out.println("not a number  "+e);
        }

        while (choice < 1 || choice > 16) {//ensure the integer is between 1-16
            System.out.println("please enter a number from 1-16");
            scanin = scan.next();
            try {
                choice = Integer.parseInt(scanin);
            } catch (NumberFormatException e) {
                System.out.println("not a number");
            }
        }

        return choice;
    }

    public void CheckMatch(int choice1, int choice2) {
        int row1 = convertToRow(choice1);//the rows number of chioce1 
        int column1=convertToColumn(choice1,row1);//the columns number of chioce1 
        int row2 = convertToRow(choice2);//the rows number of chioce2 
        int column2=convertToColumn(choice2,row2);//the columns number of chioce2
        
        if (animal[row1][column1]==null) {
            System.out.println("You have matched choice 1");
        } else if (animal[row2][column2]==null) {
            System.out.println("You have matched choice 2");
        } 
        else if(row2==row1&& column2==column1){
            System.out.println("You can not choose two same card");
        }
        else if (animal[row2][column2].equals(animal[row1][column1])) {
            System.out.println("choice1 and choice2 is  matched"
                    + "\nAnd they are " +animal[row2][column2]);
            animal[row2][column2] = null;
            animal[row1][column1] = null;
        } else {
            System.out.println("choice1 and choice2 is not matched"
                    + "\nchoice1 is "+animal[row1][column1]
                    +", and choice2 is "+ animal[row2][column2]);
        }
        
    }
    public void printPainiting(){
        System.out.println("The numbers are arranged like this:\n"
                + "  1 |  2 |  3 |  4 \n"
                + "  5 |  6 |  7 |  8 \n"
                + "  9 | 10 | 11 | 12 \n"
                + " 13 | 14 | 15 | 16 \n");
    }
    public int convertToRow(int choice){
        int row = 0;
        if (choice > 4 && choice <= 8) {
            row = 1;
        } else if (choice > 8 && choice <= 12) {
            row = 2;
        } else if (choice > 12 && choice <= 16) {
            row = 3;
        } else {
            choice--;//As array is start with [0][0]
            row = 0;
        }
        return row;
    }

    public int convertToColumn(int choice,int row){
        int column = 0;
        column=choice-row*4-1;
        return column;
    }
    
}
