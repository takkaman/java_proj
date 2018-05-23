package memorycardgame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MemoryCardGameGUI implements ActionListener {
    // GUI related variables
    JFrame frame = new JFrame("MemoryCardGame");
    JPanel myPanel = new JPanel();
    JButton msg = new JButton("Message:");
    JPanel lowerPanel = new JPanel();// create panel
    Container container = new Container();

    // data structure
    final int row = 4;// row num
    final int col = 4;// col num
    static int usedTime; // track used time
    static JLabel msgLabel = new JLabel();// welcome label
    static JLabel msgLabel1 = new JLabel();// guide label
    static JLabel msgLabel2 = new JLabel();// hint label
    static Timer timer;// timer
    JButton[][] buttons = new JButton[row][col];// define button
    int[][] counts = new int[row][col];// define button num

    public static String[][] animal = new String[4][4];
    public static int clickCnt = 0;
    public static int row1, col1, row2, col2, matchCnt = 0;
    public static Card card;

    public void StartGame() {
        card = new Card();
        animal = card.Random();

        frame.setSize(900, 1000);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // panel setting
        msgLabel.setText("Welcome to Memory Card Game!");
        msgLabel1.setText("Please choose the first card by enter a number from 1-16");
        msgLabel2.setText("...");
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.add(msgLabel, BorderLayout.NORTH);
        lowerPanel.add(msgLabel1, BorderLayout.CENTER);
        lowerPanel.add(msgLabel2, BorderLayout.SOUTH);
        timer = new Timer(1000, new TimerListener());// timer listener
        frame.add(lowerPanel,  BorderLayout.NORTH);
        System.out.println("GUI started.");
        addMsgButton();
        addButtons();
        frame.setVisible(true);
    }
//
//    void addResetButton() {
//        // restart
//        retry.setBackground(Color.white);// button background color
//        retry.setOpaque(true);
//        retry.addActionListener(this);
//        frame.add(retry, BorderLayout.NORTH);// put the button north
//    }

    void addMsgButton() {
        // restart
        msg.setBackground(Color.white);// button background color
        msg.setOpaque(true);
        msg.addActionListener(this);
        //frame.add(msg, BorderLayout.NORTH);// put the button north
    }

    void addButtons() {
        // play card button
        frame.add(container, BorderLayout.CENTER);// put center
        container.setLayout(new GridLayout(row, col));// auto assign container
        int k = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                JButton button = new JButton();
                button.setBackground(Color.white);
                button.setOpaque(true);
                button.addActionListener(this);// add action listener

                buttons[i][j] = button;// define button array
                container.add(button);// add button into container
                buttons[i][j].setText(String.valueOf(k));
                k++;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        clickCnt++;
        int r = 0, c = 0;
        JButton button = (JButton) e.getSource();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (button.equals(buttons[i][j])) {
                    System.out.println(i+" "+j);
                    r = i;
                    c = j;
                    break;
                }
            }
        }

        if (clickCnt % 2 == 1) {//get the first card
            msgLabel1.setText("Please choose the second card by enter a number from 1-16");
            msgLabel2.setText("...");
            row1 = r;
            col1 = c;
        } else {//get the secong card
//          card.printAllCard(animal);
            msgLabel1.setText("Checking for match");
            //msgLabel1.setText("Checking for match");
            row2 = r;
            col2 = c;
            CheckMatch(row1, col1, row2, col2);
        }

    }

    public void CheckMatch(int row1, int column1, int row2, int column2) {
//        int row1 = convertToRow(choice1);//the rows number of chioce1
//        int column1=convertToColumn(choice1,row1);//the columns number of chioce1
//        int row2 = convertToRow(choice2);//the rows number of chioce2
//        int column2=convertToColumn(choice2,row2);//the columns number of chioce2
        card.printAllCard(animal);
        if (animal[row1][column1]==null) {
            msgLabel2.setText("You have matched choice 1");
        } else if (animal[row2][column2]==null) {
            msgLabel2.setText("You have matched choice 2");
        }
        else if(row2==row1&& column2==column1){
            msgLabel2.setText("You can not choose two same card");
        }
        else if (animal[row2][column2].equals(animal[row1][column1])) {
            msgLabel1.setText("Please choose the first card by enter a number from 1-16");
            msgLabel2.setText("choice1 and choice2 is matched. They are " +animal[row2][column2]);
//            animal[row2][column2] = null;
//            animal[row1][column1] = null;
            ImageIcon img = card.imgMap.get(animal[row1][column1]);
            buttons[row1][column1].setEnabled(false);
            buttons[row2][column2].setEnabled(false);
            buttons[row1][column1].setIcon(img);
            buttons[row2][column2].setIcon(img);
            buttons[row1][column1].setDisabledIcon(img);
            buttons[row2][column2].setDisabledIcon(img);
            matchCnt += 2;
            if (matchCnt == 16) {
                msgLabel1.setText("You win!");
            }
        } else {
            msgLabel1.setText("Please choose the first card by enter a number from 1-16");
            msgLabel2.setText("choice1 and choice2 is not matched, choice1 is "+animal[row1][column1]
                    +", and choice2 is "+ animal[row2][column2]);
        }

    }
    // action listener
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("AA");
        }
    }

}
