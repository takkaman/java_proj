package LinkList;
import java.util.*;

public class LinkList {

    public ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null) return null;
        Map<Integer, Integer> m = new HashMap<>();
        ListNode p = pHead;
        while (p != null) {
            if (m.containsKey(p.val)) {
                m.put(p.val, m.get(p.val)+1);
            } else {
                m.put(p.val, 1);
            }
            p = p.next;
        }
        p = pHead;
        while (m.get(p.val) > 1 && p != null) {
            p = p.next;
        }
        if (p == null) return null;
        pHead = p;
        p = p.next;
        ListNode q = pHead;
        while (p != null) {
            if (m.get(p.val) == 1) {
                q.next = p;
                q = q.next;
            }
            p = p.next;
        }
        q.next = p;
        return pHead;
    }

    public ListNode GetMeetingNode(ListNode pHead) {
        ListNode p, q;
        if (pHead == null || pHead.next == null) return null;
        p = pHead;
        q = pHead.next;
        while (p != q && q != null) {
            p = p.next;
            q = q.next;
            if (p == q) break;
            q = q.next;
        }
        return p;

    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        int i = 0, n = 0;
        ListNode p, q = pHead;
        ListNode meetingNode = GetMeetingNode(pHead);
        if (meetingNode == null) return null;
        p = meetingNode.next;
        while(p != meetingNode) {
            n++;
            p = p.next;
        }
        p = pHead;
        while (i <= n) {
            i++;
            p = p.next;
        }
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

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
}
