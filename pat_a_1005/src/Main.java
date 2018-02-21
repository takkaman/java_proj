import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int result = 0;
        for (int i = 0; i < num.length(); i++) {
            result += num.charAt(i) - '0';
        }
//        System.out.print(result);
        if (result < 1000) {
            String output = String.valueOf(result);
            for (int i = 0; i < output.length(); i++) {
                switch (output.charAt(i)) {
                    case '1':
                        System.out.print("one");
                        break;
                    case '2':
                        System.out.print("two");
                        break;
                    case '3':
                        System.out.print("three");
                        break;
                    case '4':
                        System.out.print("four");
                        break;
                    case '5':
                        System.out.print("five");
                        break;
                    case '6':
                        System.out.print("six");
                        break;
                    case '7':
                        System.out.print("seven");
                        break;
                    case '8':
                        System.out.print("eight");
                        break;
                    case '9':
                        System.out.print("nine");
                        break;
                    case '0':
                        System.out.print("zero");
                        break;
                }
                if (i < output.length() - 1) {
                    System.out.print(" ");
                }
            }
        }
    }
}