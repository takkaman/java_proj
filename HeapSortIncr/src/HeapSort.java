import java.util.*;

public class HeapSort {
    public static void heapAdjust(int[] a, int pos, int len) {
//        System.out.println(len);
        int temp, j;
        temp = a[pos];
        for (j = pos*2+1; j <= len; j=j*2+1) {
//            System.out.println(a[j] + " " + a[len]);
            if (j < len && a[j] < a[j+1]) {
                j++;
            }
            if (temp > a[j]) break;
            a[pos] = a[j];
            pos = j;
        }
        a[pos] = temp;
    }

    public static void heapSort(int[] a) {
        int i;
        for (i = a.length/2 - 1; i >= 0; i--) {
            heapAdjust(a, i, a.length-1);
        }
        for (i = a.length-1; i > 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapAdjust(a, 0, i-1);
        }
    }

    public static void main(String[] args) {
        int a[] = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
//        int[] a = {1,3,2,5,7,6,9,4,10};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
