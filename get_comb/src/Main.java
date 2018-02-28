import java.util.*;

public class Main{
	public static void HasComb(float targ, List<Float> numArrOrig, List<Float> prevOrig) {
		List<Float> numArr = new ArrayList<>();
		List<Float> prev = new ArrayList<>();
		for (Float tmp: numArrOrig) {
			numArr.add(tmp);
		}
		for (Float tmp: prevOrig) {
			prev.add(tmp);
		}

		if (targ < 0) return;
		if (numArr.size() == 0 && targ > 0) return;
		if (numArr.size() == 0 && targ == 0) {
			for (float tmp: prevOrig) {
				System.out.print(tmp+" ");
			}
			System.out.println("\n");
			return;
		}
		float f = numArr.get(0);
		prev.add(f);
		numArr.remove(0);
		HasComb((float)(Math.round((targ-f)*100))/100,numArr, prev);
		HasComb((float)(Math.round(targ*100))/100, numArr, prevOrig);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		float targ = sc.nextFloat();
		int count = sc.nextInt();
		List<Float> numArr = new ArrayList<>();
		while(count-->0) {
			float tmp = sc.nextFloat();
			if (tmp >= 0) {
				numArr.add(tmp);
			} else {
				break;
			}
		}
		sc.close();
		Collections.sort(numArr, new Comparator<Float>() {
			public int compare(Float a, Float b) {
				return b.compareTo(a);
			}
		});

		List<Float> prev = new ArrayList<>();
		HasComb(targ, numArr, prev);
	}
}