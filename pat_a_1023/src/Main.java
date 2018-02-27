import java.math.BigInteger;
import java.util.*;

public class Main {
    public static boolean SameDigit(String a, String b) {
        boolean result = true;
        Map<Character, Integer> bitCount = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char tg = a.charAt(i);
            if (bitCount.containsKey(tg)) {
                bitCount.put(tg, bitCount.get(tg)+1);
            } else {
                bitCount.put(tg, 1);
            }
        }
        for (int i = 0; i < b.length(); i++) {
            char tg = b.charAt(i);
            if (bitCount.containsKey(tg)) {
                bitCount.put(tg, bitCount.get(tg)-1);
            } else {
                return false;
            }
        }

        for (char k: bitCount.keySet()) {
            if (bitCount.get(k) != 0) {
                result = false;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String num = sc.nextLine();
        BigInteger a = new BigInteger(num);
        BigInteger b = a.multiply(new BigInteger("2"));
        String comp = b.toString();
        boolean same = SameDigit(num, comp);
        if(same) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        System.out.println(comp);
    }
}
