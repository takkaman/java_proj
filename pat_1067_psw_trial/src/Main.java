import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String line = sc.nextLine();
	    String correctPsw = line.split(" ")[0];
	    int maxNum = Integer.valueOf(line.split(" ")[1]);
	    List<String> input = new ArrayList<>();
	    while (sc.hasNext()) {
	        String tmp = sc.nextLine();
	        if (tmp.equals("#")) break;
            input.add(tmp);
        }

        for (String s: input) {
	        if (!correctPsw.equals(s)) {
	            System.out.println("Wrong password: "+s);
	            maxNum--;
                if (maxNum == 0) {
                    System.out.println("Account locked");
                    break;
                }
            } else if (correctPsw.equals(s) && maxNum > 0) {
                System.out.println("Welcome in");
                break;
            }
        }
    }
}
