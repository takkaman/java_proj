import java.util.*;

public class Main{
	public static long GetMaxVal(List<Integer> coup, List<Integer> prod) {
		long val;
		val = Long.valueOf(coup.get(0));
		return val;
	}
	
	public static long GetMaxVal(int[] coup, int[] prod) {
		long val = 0;
		int i=0, j=0;
		Arrays.sort(coup);
//		System.out.println(Arrays.toString(coup));
		Arrays.sort(prod);
//		System.out.println(Arrays.toString(prod));
		while (coup[i] < 0 && prod[j] < 0) {
			val += coup[i++] * prod[j++];
		}
		
		i = coup.length - 1;
		j = prod.length - 1;
		while (coup[i] > 0 && prod[j] > 0) {
			val += coup[i--] * prod[j--];
		}
		
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
		int[] coup = new int[nc];
		for (i = 0; i < nc; i++) {
			coup[i] =sc.nextInt();
		}
		int np = sc.nextInt();
		int[] prod = new int[np];
		for (i = 0; i < np; i++) {
			prod[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(GetMaxVal(coup, prod));
	}
}
