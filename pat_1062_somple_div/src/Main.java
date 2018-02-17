import java.util.*;

public class Main {
    public static double TransStr(String str) {
        int a = Integer.valueOf(str.split("/")[0]);
        int b = Integer.valueOf(str.split("/")[1]);
        return Double.valueOf(a)/Double.valueOf(b);
    }
    public static boolean HasCommDiv(int a, int b) {
        int i;
        boolean result = false;
        for (i = 2; i <= b/2; i++) {
            if (a%i==0 && b%i==0) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String tmp1 = line.split(" ")[0];
        String tmp2 = line.split(" ")[1];
        int k = Integer.valueOf(line.split(" ")[2]);
//        System.out.println(k);
        int j;
        double num1 = TransStr(tmp1);
        double num2 = TransStr(tmp2);
        double tmp;
        if (num1 > num2) {
            tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        List<String> result = new ArrayList<>();
        for (int i = 1; i < k - 1; i++) {
            double val = Double.valueOf(i)/Double.valueOf(k);
//            System.out.print(val);
            if (val > num1 && val < num2 && !HasCommDiv(i,k)) {
//                System.out.println(i+" "+k);
                result.add(i+"/"+k);
            }
        }

        j = result.size();
        for (String s: result) {
            System.out.print(s);
            j--;
            if (j>0) {
                System.out.print(" ");
            }
        }
    }
}
