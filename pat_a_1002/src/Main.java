import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		String[] numArr1 = sc.nextLine().split(" ");
		String[] numArr2 = sc.nextLine().split(" ");
		Map<Integer, Double> result = new HashMap<>();
		for (int i = 0; i < Integer.valueOf(numArr1[0]); i++) {
//			System.out.println(numArr1[i*2+2]);
			result.put(Integer.valueOf(numArr1[i*2+1]), Double.valueOf(numArr1[i*2+2]));
		}
		for (int i = 0; i < Integer.valueOf(numArr2[0]); i++) {
//			System.out.println(numArr2[i*2+2]);
			if (result.containsKey(Integer.valueOf(numArr2[i*2+1]))) {
				result.put(Integer.valueOf(numArr2[i*2+1]), result.get(Integer.valueOf(numArr2[i*2+1])) + Double.valueOf(numArr2[i*2+2]));
			} else {
				result.put(Integer.valueOf(numArr2[i*2+1]), Double.valueOf(numArr2[i*2+2]));
			}
		}
		List<Integer> expList = new ArrayList<>();
		for (int k: result.keySet()) {
			expList.add(k);
		}
		Collections.sort(expList, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b-a;
			}
		});
		System.out.print(result.size()+" ");
		for (int i = 0; i < expList.size(); i++) {
			if (result.get(expList.get(i)) != 0) {
				String str = String.format("%.1f", result.get(expList.get(i)));
				System.out.print(expList.get(i) + " " + str);
				if (i < expList.size() - 1) {
					System.out.print(" ");
				}
			}
		}
    }
}
