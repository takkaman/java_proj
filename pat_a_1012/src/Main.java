import java.util.*;
import java.text.DecimalFormat;

public class Main {
    public static class Student {
        int id;
        int score_c, score_a, score_m, score_e;
        int rank_c, rank_m, rank_e, rank_a;
        Student(int id, int score1, int score2, int score3) {
            this.id = id;
            this.score_c = score1;
            this.score_m = score2;
            this.score_e = score3;
            this.score_a = (int) Math.round(Double.valueOf(score1+score2+score3)/3);
        }

        public int GetHighestRank() {
            return Math.min(Math.min(rank_c, rank_e), Math.min(rank_a, rank_m));
        }

        public String GetHighestRankItem() {
            int r = this.GetHighestRank();
            if (rank_a == r) {
                return "A";
            } else if (rank_c == r) {
                return "C";
            } else if (rank_m == r) {
                return "M";
            } else {
                return "E";
            }
        }
    }
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int i, prev, j;
	    int infoNum = sc.nextInt();
	    int chkNum = sc.nextInt();
	    List<Student> resultList = new ArrayList<>();
	    List<Integer> checkList = new ArrayList<>();
	    Map<Integer, Student> map = new HashMap<>();
	    sc.nextLine();
        for(i = 0; i < infoNum; i++) {
            String[] line = sc.nextLine().split(" ");
            Student s = new Student(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2]), Integer.valueOf(line[3]));
//            System.out.println(s.id+" "+s.score_c+" "+s.score_m+" "+s.score_e+" "+s.score_a);
            map.put(Integer.valueOf(line[0]), s);
            resultList.add(s);
        }

        // get c rank
        Collections.sort(resultList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.score_c - s1.score_c;
            }
        });
        i = 1;
        j = 1;
        prev = 0;
        for (Student s: resultList) {
//            System.out.println(s.id);
            if (s.score_c != prev) {
                j = i;
                s.rank_c = j;
            } else {
                s.rank_c = j;
            }
            prev = s.score_c;
            i++;
        }

        // get m rank
        Collections.sort(resultList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.score_m - s1.score_m;
            }
        });
        i = 1;
        j = 1;
        prev = 0;
        for (Student s: resultList) {
//            System.out.println(s.id);
            if (s.score_m != prev) {
                j = i;
                s.rank_m = j;
            } else {
                s.rank_m = j;
            }
            prev = s.score_m;
            i++;
        }

        // get e rank
        Collections.sort(resultList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.score_e - s1.score_e;
            }
        });
        i = 1;
        j = 1;
        prev = 0;
        for (Student s: resultList) {
//            System.out.println(s.id);
            if (s.score_e != prev) {
                j = i;
                s.rank_e = j;
            } else {
                s.rank_e = j;
            }
            prev = s.score_e;
            i++;
        }

        // get a rank
        Collections.sort(resultList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.score_a - s1.score_a;
            }
        });
        i = 1;
        j = 1;
        prev = 0;
        for (Student s: resultList) {
//            System.out.println(s.id);
            if (s.score_a != prev) {
                j = i;
                s.rank_a = j;
            } else {
                s.rank_a = j;
            }
//            System.out.println(s.rank_a);
            prev = s.score_a;
            i++;
        }

        for (i = 0; i < chkNum; i++) {
            int id = Integer.valueOf(sc.nextLine());
            checkList.add(id);
        }
        for (i = 0; i < chkNum; i++) {
            int id = checkList.get(i);
            if (map.containsKey(id)) {
                System.out.println(map.get(id).GetHighestRank()+" "+map.get(id).GetHighestRankItem());
            } else {
                System.out.println("N/A");
            }
        }
//        System.out.println(Math.round(Double.valueOf(82+87+94)/3));

    }
}
