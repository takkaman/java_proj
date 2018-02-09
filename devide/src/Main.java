import java.util.*;
import java.math.BigInteger;

public class Main {
    public static int GetExp(int n, int a) {
        BigInteger fn = GetFactory(n);
        int i = 1;
        BigInteger mult = BigInteger.valueOf(a);
        while (mult.compareTo(fn) <= 0) {
//            System.out.println(mult);
            mult = mult.multiply(BigInteger.valueOf(a));
            i++;
        }
        return i-1;
    }

    public static BigInteger GetFactory(int n) {
        if (n == 1) return BigInteger.valueOf(1);
        return BigInteger.valueOf(n).multiply(GetFactory(n-1));
    }

    public static BigInteger CountExp(int a, int k) {
        BigInteger result = new BigInteger("1");
        for (int i = k; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(a));
        }
        return result;
    }

    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        String n_a = input.nextLine();
        int n = Integer.valueOf(n_a.split(" ")[0]);
        int a = Integer.valueOf(n_a.split(" ")[1]);
        BigInteger fn = GetFactory(n);
//        System.out.print(fn);
        int k = GetExp(n, a);
//        System.out.println(k);
//        System.out.println(CountExp(a,k));
        while (fn.mod(CountExp(a,k)).compareTo(new BigInteger("0")) != 0) {
            k--;
        }
        System.out.print(k);
    }
}