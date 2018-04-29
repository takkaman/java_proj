import java.util.*;

public class HeapSort {
    public static void heapAdjust(int[] a, int pos, int end) {
        int i, temp;
        temp = a[pos];
        for (i = pos*2+1; i <= end; i = i*2+1) {
            if (i < end && a[i] > a[i+1]) {
                i++;
            }
            if (temp < a[i]) break;
            a[pos] = a[i];
            pos = i;
        }
        a[pos] = temp;
    }

    public static void heapSort(int[] a) {
        int i, temp;
        for (i = a.length/2-1; i>=0; i--) {
            heapAdjust(a, i, a.length-1);
        }
        for (i = a.length-1; i > 0; i--) {
            temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            heapAdjust(a, 0, i-1);
        }
    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
