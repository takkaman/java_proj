import java.util.*;

public class Main {
    public static void IsGenPal(long n, long b) {
        long i = 1;
        int j, count = 1;
        String result = "Yes";
        while (i<=n) {
            i *= b;
            count++;
        }
        long[] numArr = new long[count-1];
        for (j = 0; j < numArr.length; j++) {
            int pos = numArr.length - 1 - j;
            numArr[pos] = n % b;
            n = n / b;
        }

        for (j = 0; j <= (numArr.length-1)/2; j++) {
            if (numArr[j] != numArr[numArr.length-1-j]) {
                result = "No";
                break;
            }
        }
        System.out.println(result);
        for (j = 0; j < numArr.length; j++) {
            System.out.print(numArr[j]);
            if (j < numArr.length - 1) {
                System.out.print(" ");
            }
        }

    }
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String line = sc.nextLine();
	    long n = Long.valueOf(line.split(" ")[0]);
	    long b = Long.valueOf(line.split(" ")[1]);
	    IsGenPal(n, b);
    }
}
