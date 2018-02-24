import java.util.*;

public class Main {
    public static String IsPrimeReverse(long n, int d) {
        if (!IsPrime(n)) return "No";
        long val = 0;
        int i = 1, count = 1;
        while (i <= n) {
            i *= d;
            count++;
        }
        long[] num = new long[count-1];
        i = 0;
        while (n != 0) {
            num[i] = n % d;
            n = n / d;
//            System.out.println(num[i]);
            i++;
        }

        for (i = 0; i < num.length; i++) {
//            System.out.println(num[i]);
            val += num[i] * Math.pow(d, num.length - 1 - i);
        }
//        System.out.println(val);
        if (IsPrime(val)) return "Yes";
        return "No";
    }

    public static boolean IsPrime(long n) {
        if (n==1) return false;
        boolean result = true;
        int i;
        for (i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    List<String> result = new ArrayList<>();
	    while(true) {
	        int n = sc.nextInt();
	        if (n < 0) break;
	        int d = sc.nextInt();
//	        System.out.println(n+" "+d);
	        result.add(IsPrimeReverse(n, d));
        }

        for (String r: result) {
	        System.out.println(r);
        }
    }
}
