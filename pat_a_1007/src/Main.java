import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt(), i, j = 0, start = 0, end = 0, start_tmp = 0, end_tmp = 0;
        long sum = 0, sum_tmp = 0;
        long[] numArr = new long[num];
        for (i = 0; i < num; i++) {
            numArr[i] = sc.nextInt();
        }

        while(start_tmp < num && numArr[start_tmp] < 0) {
            start_tmp++;
        }
        if (start_tmp == num) {
            System.out.print("0"+" "+numArr[0]+" "+numArr[start_tmp-1]);
        } else {
            for (i = start_tmp; i < num; i++) {
                if (sum_tmp <= 0) {
                    sum_tmp = 0;
                    start_tmp = i;
                }
                sum_tmp += numArr[i];
                if (sum_tmp > sum) {
                    start = start_tmp;
                    end_tmp = i;
                    end = end_tmp;
                    sum = sum_tmp;
                }
            }

            System.out.print(sum + " " + numArr[start] + " " + numArr[end]);
        }
    }
}
