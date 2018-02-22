import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner sc  = new Scanner(System.in);
	    int i, j = 0;
	    List<Character> result = new ArrayList<>();
	    String std = sc.nextLine();
	    String act = sc.nextLine();
        for (i = 0; i < std.length(); i++) {
            char tmp = std.charAt(i);
            if (j >= act.length() || tmp != act.charAt(j)) {
                if (tmp >= 'a' && tmp <= 'z') {
                    tmp = Character.toUpperCase(tmp);
                }
                if (!result.contains(tmp)) {
                    result.add(tmp);
                }
            } else {
                j++;
            }
        }

        for (Character c: result) {
            System.out.print(c);
        }
    }
}
