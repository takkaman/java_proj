import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String str = sc.nextLine();
		Long num1 = Long.valueOf(str.split(" ")[0]);
		Long num2 = Long.valueOf(str.split(" ")[1]);
		String result = String.valueOf(num1+num2);
		int length;
		if (num1+num2 >= 0) {
			length = result.length() + (result.length()-1) / 3;
		} else {
			length = result.length() + (result.length()-2) / 3;
		}
		char[] output = new char[length];
		int count = 0;
		int j = result.length() - 1;
		for (int i = output.length - 1; j >= 0; i--) {
			if (count > 0 && count % 3 == 0 && result.charAt(j) != '-') {
				output[i] = ',';
				i--;
			}
			output[i] = result.charAt(j);
			j--;
			count++;

//			System.out.println(output[i]);
		}

		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i]);
		}
    }
}
