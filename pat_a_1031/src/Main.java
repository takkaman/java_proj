import java.util.*;

public class Main{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		int n = input.length()+2;
		int c = 0;
		while(n%3 != 0) {
			n--;
			c++;
		}
		int n1, n2, n3, i, j;
		n1 = n/3;
		n3 = n/3;
		n2 = n/3+c;
//		System.out.println(n1+" "+n2+" "+n3);
		char[][] output = new char[n1][n2];
		for (i = 0; i < n1; i++) {
			for (j = 0; j < n2; j++) {
				output[i][j] = ' ';
			}
		}
		j = 0;
		c = 0;
		for (i = 0; i < n1; i++) {
			output[i][j] = input.charAt(c);
			c++;
		}
		i--;
		for (j = 1; j < n2; j++) {
			output[i][j] = input.charAt(c);
			c++;
		}
		j--;
		for (i = n3-2; i >= 0; i--) {
			output[i][j] = input.charAt(c);
			c++;
		}
		for (i = 0; i < n1; i++) {
			for (j = 0; j < n2; j++) {
				System.out.print(output[i][j]);
			}
			if (i<n1-1) {
				System.out.print("\n");
			}
		}
	}
}