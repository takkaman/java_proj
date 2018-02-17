import java.util.*;

public class Main {

    public static void main(String[] args) {
        int start=0, end=0;
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        int ttlNum = Integer.valueOf(line.split(" ")[0]);
        Double p =  Double.valueOf(line.split(" ")[1]);
        String numArr_tmp = input.nextLine();
        Double[] numArr = new Double[ttlNum];
        String [] numArrS = numArr_tmp.split(" ");
        for (int i = 0; i < ttlNum; i++) {
            numArr[i] = Double.valueOf(numArrS[i]);
        }
        Arrays.sort(numArr);
        while(end<ttlNum) {
//            System.out.println(numArr[start] +" "+ numArr[end]);
            if (numArr[end] <= p*numArr[start]) {
                end++;
            } else {
                start++;
            }
        }
        System.out.println(end-start);
    }
}
