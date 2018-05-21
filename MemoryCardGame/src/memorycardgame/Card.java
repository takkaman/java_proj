package memorycardgame;

import java.util.Random;

public class Card {

    public Random rdm = null;
    private String[] store = {"elephant", "tiger", "Dog", "Cat",
        "Bear", "lion", "bird", "wolf",
        "elephant", "tiger", "Dog", "Cat",
        "Bear", "lion", "bird", "wolf"};
    private String[] animal = new String[16];//reset the order of store 
    private static String ranimal[][] = new String[4][4];//put animal in array

    public Card() {
        rdm = new Random();

    }

    public String[][] Random() {
//reset the order of store and put themin animal without repeat
        for (int i = 0; i < 16; i++) {
            animal[i] = store[rdm.nextInt(15)];
            for (int j = 0; j < i; j++) {
                if (animal[i].equals(animal[j])) {
                    for (int k = j + 1; k < i; k++) {
                        if (animal[i].equals(animal[k])) {
                            i--;
                        }
                    }
                }
            }
        }
        
//Change String array to 2 dimensional String array
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ranimal[i][j] = animal[k];
                k++;
            }
        }
        return ranimal;
    }

    public void printAllCard(String ranimal[][]){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(ranimal[i][j]+" ");
            }
            System.out.println("");
        }
    }

  
}
