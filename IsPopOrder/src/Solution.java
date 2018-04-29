import java.util.*;

public class Solution {

public static boolean IsPopOrder(int [] pushA,int [] popA) {
    if(pushA.length == 0 || popA.length == 0)
        return false;
    Stack<Integer> s = new Stack<Integer>();
    int popIndex = 0;
    for(int i = 0; i< pushA.length;i++){
        s.push(pushA[i]);
        while(!s.empty() &&s.peek() == popA[popIndex]){
           s.pop();
           popIndex++;
        }
    }
    return s.empty();
}

    public static void main(String[] args) {
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        int[] popB = {4,3,5,1,2};
//        System.out.println(Arrays.asList(pushA));
        System.out.println(IsPopOrder(pushA, popA));
        System.out.println(IsPopOrder(pushA, popB));
    }
}
