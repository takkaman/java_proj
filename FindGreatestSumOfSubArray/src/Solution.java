import java.util.*;

public class Solution {
    public static int FindGreatestSumOfSubArray(int[] array) {
        int max = -99999999, i = 0, tmpSum = 0;
        for (i = 0; i < array.length; i++) {
            tmpSum += array[i];
            if (tmpSum < 0) {
                if (tmpSum > max) {
                    max = tmpSum;
                }
                tmpSum = 0;
            } else if (tmpSum > max) {
                max = tmpSum;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = {-4, -2, -3, -1, -6};
        int result = FindGreatestSumOfSubArray(arr);
        System.out.println(result);
    }
}
