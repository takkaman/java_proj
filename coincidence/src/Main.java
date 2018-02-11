import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    String a = input.next();
	    String b = input.next();
		int i, j;
	    int[][] lcs = new int[a.length()+1][b.length()+1];
	    for (i = 0; i <= a.length(); i++) {
	    	lcs[i][0] = 0;
		}
		for (j = 0; j <= b.length(); j++) {
	    	lcs[0][j] = 0;
		}

		for (i = 1; i <= a.length(); i++) {
	    	for (j = 1; j <= b.length(); j++) {
	    		if (a.charAt(i-1) == b.charAt(j-1)) {
	    			lcs[i][j] = lcs[i-1][j-1] + 1;
				} else {
	    			lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}

		System.out.println(lcs[a.length()][b.length()]);
    }
}
