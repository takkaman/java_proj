import java.util.*;

public class Main {
    public static int CalcEquation (String line, double value[]) {
        int result = 0;
        int a = 0, b = 0, c = 0;
        int equal = line.indexOf('=');
        int prev = -1;
        int m, n, symble;
        String tmp;
        for (int i = 0; i < line.length(); i++) {
            char item = line.charAt(i);
            if (item == '+' || item == '-' || item == '=' || i==line.length()-1) {
                if (i != 0 && (i != equal+1 || (i == line.length()-1 && prev == equal))) {
                    if (prev == -1) {
                        tmp = line.substring(0,i);
                        if (tmp.length() >=3 && tmp.charAt(tmp.length()-2)=='^' && tmp.charAt(tmp.length()-1)=='2') {
                            if (tmp.charAt(0) == 'x') {
                                a += 1;
                            } else {
                                a += Integer.valueOf(tmp.substring(0,tmp.length()-3));
                            }
                        } else if (tmp.charAt(tmp.length()-1) == 'x') {
                            if (tmp.charAt(0) == 'x') {
                                b += 1;
                            } else {
                                b += Integer.valueOf(tmp.substring(0, tmp.length()-2));
                            }
                        } else {
                            c += Integer.valueOf(tmp);
                        }
                    } else {
                        if (i != line.length() -1) {
                            tmp = line.substring(prev + 1, i);
                        } else {
                            tmp = line.substring(prev + 1);
                        }
//                        System.out.println(tmp+" "+prev+" "+i);
                        if (line.charAt(prev) == '+' || line.charAt(prev) == '=') {
                            m = 1;
                        } else {
                            m = -1;
                        }
                        if (i <= equal) {
                            n = 1;
                        } else {
                            n = -1;
                        }
                        symble = m * n;
                        if (tmp.length() >=3 && tmp.charAt(tmp.length()-2)=='^' && tmp.charAt(tmp.length()-1)=='2') {
                            if (tmp.charAt(0) == 'x') {
                                a += symble;
                            } else {
                                a += Integer.valueOf(tmp.substring(0,tmp.length()-3)) * symble;
                            }
                        } else if (tmp.charAt(tmp.length()-1) == 'x') {
                            if (tmp.charAt(0) == 'x') {
                                b += symble;
                            } else {
//                                System.out.println(tmp.substring(0, tmp.length()-1));
                                b += Integer.valueOf(tmp.substring(0, tmp.length()-1)) * symble;
                            }
                        } else {
                            c += Integer.valueOf(tmp) * symble;
                        }
                    }
                }
                prev = i;
            }
        }
//        System.out.printf("%d, %d, %d\n", a, b, c);
        if (b*b-4*a*c >= 0) {
            value[0] = (-1*b-Math.sqrt(b*b-4*a*c))/(2*a);
            value[1] =(-1*b+Math.sqrt(b*b-4*a*c))/(2*a);
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        double[] value = new double[2];
        int result = CalcEquation(line, value);
        if (result==1)  {
            if (value[0] <= value[1]) {
                System.out.print(String.format("%.2f", value[0]) + " " + String.format("%.2f", value[1]));
            } else {
                System.out.print(String.format("%.2f", value[1]) + " " + String.format("%.2f", value[0]));
            }
        } else {
            System.out.print("No Solution");
        }
    }
}
