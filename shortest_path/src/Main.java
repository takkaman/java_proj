import java.math.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        BigInteger BASE = new BigInteger("2");
        BigInteger MOD = new BigInteger("100000");
        int i,j, m, n, a, b, k;
	    Scanner input = new Scanner(System.in);
	    n = input.nextInt();
	    m = input.nextInt();
        BigInteger[][] Graph = new BigInteger[n][n];
        String INF = "";
        for (i = 0;i < 160;i++) {
            INF += "9";
        }
        // init Graph
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j) {
                    Graph[i][j] = new BigInteger(INF);
                } else {
                    Graph[i][j] = new BigInteger("0");
                }
            }
        }

        // build graph
        for (i = 0; i < m; i++) {
            a = input.nextInt();
            b = input.nextInt();
            if(!Graph[a][b].toString().equals(INF)) continue; // duplicated case handling
            Graph[a][b] = new BigInteger(BASE.pow(i).toString());
            Graph[b][a] = new BigInteger(BASE.pow(i).toString());
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (Graph[i][j].compareTo(Graph[i][k].add(Graph[k][j])) > 0) {
                        Graph[i][j] = Graph[i][k].add(Graph[k][j]);
                    }
                }
            }
        }

        for (i = 1; i < n; i++) {
            if (Graph[0][i].toString().equals(INF)) {
                System.out.println("-1");
            } else {
                System.out.println(Graph[0][i].mod(MOD));
            }
        }
    }
}
