import java.util.*;

public class Main {
    public static class Rcd {
        public String name;
        public String in;
        public String out;
    }

    public static int TimeComp(String a, String b) {
        String[] time1 = a.split(":");
        String[] time2 = b.split(":");

        if (Integer.valueOf(time1[0]) > Integer.valueOf(time2[0])) {
            return 1;
        } else if (Integer.valueOf(time1[0]) < Integer.valueOf(time2[0])) {
            return -1;
        } else {
            if (Integer.valueOf(time1[1]) > Integer.valueOf(time2[1])) {
                return 1;
            } else if (Integer.valueOf(time1[1]) < Integer.valueOf(time2[1])) {
                return -1;
            } else {
                if (Integer.valueOf(time1[2]) > Integer.valueOf(time2[2])) {
                    return 1;
                } else if (Integer.valueOf(time1[2]) < Integer.valueOf(time2[2])) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    List<Rcd> result = new ArrayList<>();
	    int lineNum = Integer.valueOf(sc.nextLine());
	    for (int i = 0; i < lineNum; i++) {
	        String[] input = sc.nextLine().split(" ");
            Rcd tmp = new Rcd();
            tmp.name = input[0];
            tmp.in = input[1];
            tmp.out = input[2];
            result.add(tmp);
        }

        Collections.sort(result, new Comparator<Rcd>(){
            public int compare(Rcd a, Rcd b) {
                return TimeComp(a.in, b.in);
            }
        });

	    System.out.print(result.get(0).name);
	    System.out.print(" ");

        Collections.sort(result, new Comparator<Rcd>(){
            public int compare(Rcd a, Rcd b) {
                return TimeComp(b.out, a.out);
            }
        });
        System.out.print(result.get(0).name);
    }
}
