import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt(), count=0;
        input.nextLine();
        String[] numArrS = input.nextLine().split(" ");
        double[] numArr = new double[length];
        List<Double> pivotL = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            numArr[i] = Double.valueOf(numArrS[i]);
        }
        double max=0, min=0;
        double[] minL = new double[length];
        double[] maxL = new double[length];
        for (int i = 0; i < length; i++) {
            if (i==0) {
                max = numArr[i];
            } else {
                if (numArr[i] > max) {
                    max = numArr[i];
                }
            }
            maxL[i] = max;
        }

        for (int j = length-1; j >= 0; j--) {
            if (j==length-1) {
                min = numArr[j];
            } else {
                if (numArr[j] < min) {
                    min = numArr[j];
                }
            }
            minL[j] = min;
        }

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                if (numArr[i] <= minL[i+1]) {
                    pivotL.add(numArr[i]);
                    count++;
                }
            } else if (i == length-1) {
                if (numArr[i] >= maxL[i-1]) {
                    pivotL.add(numArr[i]);
                    count++;
                }
            } else {
                if (numArr[i] >= maxL[i-1] && numArr[i] <= minL[i+1]) {
                    pivotL.add(numArr[i]);
                    count++;
                }
            }
        }

        //DecimalFormat df = new DecimalFormat("######0");
        System.out.println(count);
        for (double e: pivotL) {
            int out = (int) e;
            System.out.print(out);
            count--;
            if (count > 0) {
                System.out.print(" ");
            }
        }
    }
}
