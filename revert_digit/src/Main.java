import java.util.*;
import java.lang.String;

public class Main {
    public static int RevertString(String num) {
        char[] revert_num = new char[num.length()];
        for (int i = 0; i < num.length(); i++) {
            revert_num[i] = num.charAt(num.length()-1-i);
        }
//        System.out.print(revert_num);
        return Integer.parseInt(String.valueOf(revert_num));
    }

    public static int RevertInt(int num) {
        return RevertString(String.valueOf(num));
    }

    public static int IsRevert(String num1, String num2) {
        if (RevertInt(Integer.parseInt(num1) + Integer.parseInt(num2)) == (RevertString(num1) + RevertString(num2))) {
            return Integer.parseInt(num1) + Integer.parseInt(num2);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
	// write your code here
        String num_list, num1, num2;
        Scanner input  = new Scanner(System.in);
        num_list = input.nextLine();
        num1 = num_list.split(" ")[0];
        num2 = num_list.split(" ")[1];
        int result = IsRevert(num1, num2);
        if (result == -1) {
            System.out.print("NO");
        } else {
            System.out.print(result);
        }
    }
}
