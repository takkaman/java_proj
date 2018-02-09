import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        List<String> wordList = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            wordList.add(word.substring(i));
        }
        Collections.sort(wordList);
        for (String str: wordList) {
            System.out.println(str);
        }
    }
}
