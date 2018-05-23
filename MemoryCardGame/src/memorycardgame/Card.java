package memorycardgame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

public class Card {

    public Random rdm = null;
    private String[] store = {"elephant", "tiger", "Dog", "Cat",
        "Bear", "lion", "bird", "wolf",
        "elephant", "tiger", "Dog", "Cat",
        "Bear", "lion", "bird", "wolf"};
    private String[] animal = new String[16];//reset the order of store 
    private static String ranimal[][] = new String[4][4];//put animal in array
    public HashMap<String, ImageIcon> imgMap = new HashMap<>();

    public Card() {
        rdm = new Random();
        imgMap.put("tiger", new ImageIcon("resources/tiger.jpg"));
        imgMap.put("elephant", new ImageIcon("resources/elephant.jpg"));
        imgMap.put("Dog", new ImageIcon("resources/Dog.jpg"));
        imgMap.put("Cat", new ImageIcon("resources/Cat.jpg"));
        imgMap.put("Bear", new ImageIcon("resources/Bear.jpg"));
        imgMap.put("lion", new ImageIcon("resources/lion.jpg"));
        imgMap.put("bird", new ImageIcon("resources/bird.jpg"));
        imgMap.put("wolf", new ImageIcon("resources/wolf.jpg"));
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
