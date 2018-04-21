import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Solution {
    public static String minWindSubStr(String source, String target) {
        int[] tHash = new int[256];
        int[] sHash = new int[256];
        int sLen = source.length();
        int tLen = target.length();
        int i, start, found = 0, minLen = sLen, begin = -1, end = sLen-1;
        for (i = 0; i < tLen; i++) {
            tHash[target.charAt(i)]++;
        }

        for (i = 0, start = i; i < sLen; i++) {
            sHash[source.charAt(i)]++;
            if(sHash[source.charAt(i)] <= tHash[source.charAt(i)]) {
                found++;
            }
            if (found == tLen) {
                while (start < i && sHash[source.charAt(start)] > tHash[source.charAt(start)]) {
                    sHash[source.charAt(start)]--;
                    start++;
                }
//                System.out.println(start + " " + i);
                if (i - start < minLen) {
                    minLen = i - start + 1;
                    begin = start;
                    end = i;
                }
                sHash[source.charAt(start)]--;
                start++;
                found--;
            }
        }

        if (begin == -1) {return "";}
//        System.out.println(begin + " " + end + " " + minLen);
        return source.substring(begin, end+1);
    }

    public static void main(String args[]) {
        String source = "ADOBECODEBANC";
        String target = "ABC";
        System.out.println(minWindSubStr(source, target));
    }
}
