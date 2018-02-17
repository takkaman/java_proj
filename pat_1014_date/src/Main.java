import java.util.*;

public class Main {

    public static void main(String[] args) {
        int i,j;
        int count = 0;
	    Scanner input = new Scanner(System.in);
	    String line1 = input.nextLine();
        String line2 = input.nextLine();
        String line3 = input.nextLine();
        String line4 = input.nextLine();
        String date_tmp="", hour_tmp="", tmp;
        int hour;
        String date="";
        int length1 = Math.min(line1.length(), line2.length());
        int length2 = Math.min(line3.length(), line4.length());
        // get date, hour
        for (j = 0; j < length1; j++) {
            if (line1.charAt(j) == line2.charAt(j)) {
                tmp = String.valueOf(line1.charAt(j));
                if (count==0 && tmp.matches("[A-G]")) {
                    date_tmp = tmp;
                    count++;
                } else if (count==1 && tmp.matches("[0-9A-N]")) {
                    hour_tmp =tmp;
                    break;
                }
            }
        }


        switch (date_tmp) {
            case "A": date = "MON"; break;
            case "B": date = "TUE"; break;
            case "C": date = "WED"; break;
            case "D": date = "THU"; break;
            case "E": date = "FRI"; break;
            case "F": date = "SAT"; break;
            case "G": date = "SUN"; break;
        }

        if (hour_tmp.matches("[A-N]")) {
            hour = hour_tmp.toCharArray()[0] - 'A' + 10;
        } else {
            hour = hour_tmp.toCharArray()[0] - '0';
        }
        // get minute
        for (i = 0; i < length2; i ++) {
            if (line3.charAt(i) == line4.charAt(i) && Character.isLetter(line3.charAt(i))) break;
        }
//        System.out.println(Integer.valueOf('a'));
        if (i<10) {
            if (hour >= 10) {
                System.out.println(date + " " + hour + ":0" + i);
            } else {
                System.out.println(date + " 0" + hour + ":0" + i);
            }
        } else {
            if (hour >= 10) {
                System.out.println(date + " " + hour + ":" + i);
            } else {
                System.out.println(date + " 0" + hour + ":" + i);
            }
        }
    }
}
