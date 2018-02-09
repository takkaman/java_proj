import java.util.*;

public class Main {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        String len_m_n = input.nextLine();
        String arr_m = input.nextLine();
        String arr_n = input.nextLine();
        String a_b_c_d = input.nextLine();
        int m = Integer.valueOf(len_m_n.split(" ")[0]);
        int n = Integer.valueOf(len_m_n.split(" ")[1]);
        String[] arr1 = arr_m.split(" ");
        String[] arr2 = arr_n.split(" ");
        int a = Integer.valueOf(a_b_c_d.split(" ")[0]);
        int b = Integer.valueOf(a_b_c_d.split(" ")[1]);
        int c = Integer.valueOf(a_b_c_d.split(" ")[2]);
        int d = Integer.valueOf(a_b_c_d.split(" ")[3]);
        int half = (b-a+1+d-c+1+1)/2;
//        System.out.println(a+" "+b+" "+c+" "+d);
//        System.out.println(c+half-(b-a+1)-1);
        if (((b-a) <= (d-c)) && half <= (b-a+1)) {
            System.out.print(arr1[a-1+half-1]);
        } else {
            System.out.print(arr2[c-1+half-(b-a+1)-1]);
        }
    }
}