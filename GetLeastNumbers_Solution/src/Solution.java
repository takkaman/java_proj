import java.util.*;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (input.length < k) return result;
        int i, j;
        for (i = 0; i < k; i++) {
            int loc = -1;
            int min = 9999999;
            int tmp;
            for (j = i; j < input.length; j++) {
                if (input[j] < min) {
                    min = input[j];
                    loc = j;
                }
            }
            tmp = input[i];
            input[i] = input[loc];
            input[loc] = tmp;
            result.add(input[i]);
        }
        return result;
    }
}