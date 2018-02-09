import java.util.*;

public class Main {
    public static class Student {
        public String name;
        public int score;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int num = Integer.valueOf(input.nextLine());
            //	    System.out.println(num);
            int order = Integer.valueOf(input.nextLine());
            List<Student> infoList = new ArrayList<Student>();
            while (num > 0) {
                //	    	System.out.println(num);
                String name_score = input.nextLine();
                //	        System.out.println(name_score);
                Student s = new Student();
                s.name = name_score.split(" ")[0];
                //	        System.out.println(s.name);
                s.score = Integer.valueOf(name_score.split(" ")[1]);
                infoList.add(s);
                num--;
            }

            if (order == 1) {
                Collections.sort(infoList, new Comparator<Student>() {
                    public int compare(Student o1, Student o2) {
                        return o1.score - o2.score;
//                        return o1.score.compareTo(o2.score);
                    }
                });
            } else {
                Collections.sort(infoList, new Comparator<Student>() {
                    public int compare(Student o1, Student o2) {
                        return o2.score - o1.score;
//                        return o2.score.compareTo(o1.score);
                    }
                });
            }

            for (Student s : infoList) {
                System.out.println(s.name + " " + s.score);
            }
        }
    }
}
