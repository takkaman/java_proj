package Tree;

public class Solution {
    public TreeLinkNode GetMostLeft(TreeLinkNode pNode) {
        while (pNode.left != null) {
            pNode = pNode.left;
        }
        return pNode;
    }
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode.right != null) {
            return GetMostLeft(pNode.right);
        }
        while (pNode.next != null) {
            // right child, continue find father
            if (pNode.next.right == pNode) {
                pNode = pNode.next;
            } else {
                return pNode.next;
            }
        }
        return null;
    }
}
