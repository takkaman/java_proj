import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    while (input.hasNext()) {
			String line = input.nextLine();
			char[] strArr = new char[line.length()];
			for (int i = 0; i < line.length(); i++) {
				strArr[i] = line.charAt(i);
			}

			Arrays.sort(strArr);
			String newStr = new String(strArr);
			System.out.println(newStr);
		}
    }
}
