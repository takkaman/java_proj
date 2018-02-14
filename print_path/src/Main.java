import java.util.*;

public class Main {
    public static void PrintPath(String path, String prev) {
        String[] dirList = path.split("\\\\");
        if (prev == "") {
            for (int i = 0; i < dirList.length; i++) {
                for (int j = 0; j < i; j++) {
                    System.out.print(" ");
                }
                System.out.println(dirList[i]);
            }
        } else {
            String[] prevList = prev.split("\\\\");
            for (int i = 0; i < dirList.length; i++) {
                if (!dirList[i].equals(prevList[i])) {
                    for (int j = 0; j < i; j++) {
                        System.out.print(" ");
                    }
                    System.out.println(dirList[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int line_num;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            line_num = Integer.valueOf(sc.nextLine());
            if (line_num == 0) break;
            List<String> pathList = new ArrayList<>();
            while (line_num > 0) {
                String input = sc.nextLine();
                pathList.add(input);
                line_num--;
            }
            Collections.sort(pathList);
            String prev = "";
            for (String path: pathList) {
//                System.out.println("Scanning "+path+" "+prev);
                PrintPath(path, prev);
                prev = path;
            }
        }
    }
}