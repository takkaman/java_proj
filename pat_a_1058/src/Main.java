import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String a = input.split(" ")[0];
        String b = input.split(" ")[1];
        Long[] result = new Long[3];
        Long carry = Long.valueOf("0");
        Long[] div = {Long.valueOf(10000000), Long.valueOf(17), Long.valueOf(29)};
        for (int i = 2; i >= 0; i--) {
            Long tmp = Long.valueOf(a.split("[.]")[i])+Long.valueOf(b.split("[.]")[i])+carry;
            result[i] = tmp%div[i];
            carry = tmp/div[i];
        }

        for(int i = 0; i < 3; i++) {
            System.out.print(result[i]);
            if (i<2) {
                System.out.print(".");
            }
        }
    }
}
