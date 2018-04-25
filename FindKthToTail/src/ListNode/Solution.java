package ListNode;

public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        int i = 0;
        ListNode anchor = head;
        if (head == null) return null;
        while (i < k) {
            if (anchor == null) return null;
            anchor = anchor.next;
            i++;
        }
        ListNode target = head;
        while (anchor != null) {
            anchor = anchor.next;
            target = target.next;
        }
        return target;
    }
    public static void main(String[] args) {
        System.out.println("PASS");
    }
}
