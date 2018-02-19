import java.util.*;

public class Main {
    public static boolean AbovTol(Long[][] map, int i, int j, int row, int col, long tol) {
        boolean result = true;
        List<Long> delta = new ArrayList<>();
        if (i-1>=0 && j-1>=0) {
            delta.add(Math.abs(map[i][j]-map[i-1][j-1]));
        }
        if (i-1>=0) {
            delta.add(Math.abs(map[i][j]-map[i-1][j]));
        }
        if (i-1>=0 && j+1<=col-1) {
            delta.add(Math.abs(map[i][j]-map[i-1][j+1]));
        }
        if (j-1>=0) {
            delta.add(Math.abs(map[i][j]-map[i][j-1]));
        }
        if (j+1<=col-1) {
            delta.add(Math.abs(map[i][j]-map[i][j+1]));
        }
        if (i+1<=row-1 && j-1>=0) {
            delta.add(Math.abs(map[i][j]-map[i+1][j-1]));
        }
        if (i+1<=row-1) {
            delta.add(Math.abs(map[i][j]-map[i+1][j]));
        }
        if (i+1<=row-1 && j+1<=col-1) {
            delta.add(Math.abs(map[i][j]-map[i+1][j+1]));
        }

        for (Long val: delta) {
            if (val <= tol) {
                result = false;
                break;
            }
        }

        return result;
    }
    public static void main(String[] args) {
        int i, j;
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        int row = sc.nextInt();
        long tol = sc.nextInt();
        int count = 0;
        String result = "";
        Long[][] map = new Long[row][col];
        Map<Long, Long> colCnt = new HashMap<>();
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                map[i][j] = Long.valueOf(sc.nextInt());
//                System.out.println(map[i][j]);
                if (colCnt.containsKey(map[i][j])) {
                    colCnt.put(map[i][j], colCnt.get(map[i][j])+1);
                } else {
                    colCnt.put(map[i][j], Long.valueOf(1));
                }
            }
        }
//        System.out.println(colCnt.get(map[1][3]));

        OUT:
        for (i = 1; i < row-1; i++) {
            for (j = 1; j < col-1; j++) {
//                System.out.println(i+" "+j);
                if(AbovTol(map, i, j, row, col, tol) && colCnt.get(map[i][j])==1) {
                    if (count == 0 ) {
//                        System.out.println(i+" "+j+" "+map[i][j]);
                        result = "("+String.valueOf(j+1)+", "+String.valueOf(i+1)+"): "+String.valueOf(map[i][j]);
                        count++;
                    } else {
                        System.out.println("Not Unique");
//                        System.out.println(i+" "+j+" "+map[i][j]);
                        count++;
                        break OUT;
                    }
                }
            }
        }

        if (count == 1) {
            System.out.println(result);
        } else if (count == 0) {
            System.out.println("Not Exist");
        }
    }
}
