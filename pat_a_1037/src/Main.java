import java.util.*;

public class Main{
	public static long GetMaxVal(List<Integer> coup, List<Integer> prod) {
		long val;
		val = Long.valueOf(coup.get(0));
		return val;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i;
		int nc = sc.nextInt();
		List<Integer> coup = new ArrayList<>();
		for (i = 0; i < nc; i++) {
			coup.add(sc.nextInt());
		}
		int np = sc.nextInt();
		List<Integer> prod = new ArrayList<>();
		for (i = 0; i < np; i++) {
			prod.add(sc.nextInt());
		}
		long val = GetMaxVal(coup, prod);
		System.out.println(val);
		sc.close();
	}
}