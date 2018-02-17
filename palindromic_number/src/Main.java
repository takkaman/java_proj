import java.math.BigInteger;
import java.util.*;

public class Main {
    public static boolean IsPalin(String num) {
        boolean result = true;
        for (int i = 0; i <= num.length()/2 - 1; i++) {
//            System.out.print("Checking "+num.charAt(i)+" "+num.charAt(num.length()-1-i));
            if (num.charAt(i) != num.charAt(num.length()-1-i)) {
                result = false;
            }
        }
//        System.out.println(result);
        return result;
    }

    public static BigInteger Revert(String num) {
        char[] tmp = new char[num.length()];
        for (int i = 0; i < num.length(); i++) {
            tmp[i] = num.charAt(num.length()-1-i);
        }
        return new BigInteger(String.valueOf(tmp));
    }

    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    String num = input.next();
	    BigInteger m;
	    int count = 10;
//	    System.out.println(count);
	    while(!IsPalin(num) && count>0) {
	        count--;
            String tmp = num;
	        m = Revert(num);
	        num = String.valueOf(new BigInteger(num).add(m));
            System.out.println(tmp+" + "+m+" = "+num);
        }

        if (count > 0) {
	        System.out.println(num+" is a palindromic number.");
        } else {
	        System.out.println("Not found in 10 iterations.");
        }

    }
}
