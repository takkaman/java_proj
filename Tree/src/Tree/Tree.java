package Tree;
//import java.util.*;
//import java.math.*;

import java.util.*;

public class Tree {
    public TreeNode root;
    int maxSum = -999999;
    public Stack<TreeNode> treePath = new Stack<>();
    private Stack<TreeNode> tmpTreePath = new Stack<>();

    public int CountNode(TreeNode root) {
        if (root == null) return 0;
        int lNum = CountNode(root.left);
        int rNum = CountNode(root.right);
        return lNum + rNum + 1;
    }

    public int GetHeight(TreeNode root) {
        if (root == null) return 0;
        int lHeight = GetHeight(root.left);
        int rHeight = GetHeight(root.right);
        return (lHeight == 0 || rHeight == 0)? lHeight + rHeight + 1: Math.max(lHeight, rHeight) + 1;
    }

    public void DFS(TreeNode root) {
        if (root == null) return;
        this.tmpTreePath.push(root);
//        System.out.println(tmpTreePath.size() + " " + treePath.size());
        if (this.tmpTreePath.size() > this.treePath.size()) {
            this.treePath = (Stack<TreeNode>) this.tmpTreePath.clone();
        }
        DFS(root.left);
        DFS(root.right);
        this.tmpTreePath.pop();
        return;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        int lHeight = GetHeight(root.left);
        int rHeight = GetHeight(root.right);
        return (Math.abs(lHeight-rHeight) <= 1) && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public boolean IsSymmetrical(TreeNode root) {
        if (root == null) return true;
//        if (root.left == null && root.right == null) return true;
//        if (root.left == null || root.right == null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while(!q.isEmpty()) {
            TreeNode lNode = q.poll();
            TreeNode rNode = q.poll();
            if (lNode == null && rNode == null) continue;
            if (lNode == null || rNode == null) return false;
            if (lNode.key != rNode.key) return false;
            q.offer(lNode.left);
            q.offer(rNode.right);
            q.offer(lNode.right);
            q.offer(rNode.left);
        }
        return true;
    }

    public int MaxSubTree(TreeNode root) {
        if (root == null) return 0;
        int leftSum = MaxSubTree(root.left);
        int rightSum = MaxSubTree(root.right);
        int sum = root.key + leftSum + rightSum;
//        System.out.println(sum + " " + this.maxSum);
        this.maxSum = Math.max(this.maxSum, sum);
        return sum;
    }

    public TreeNode ReConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        int i;
        for (i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) break;
        }
        root.left = ReConstructBinaryTree(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i));
        root.right = ReConstructBinaryTree(Arrays.copyOfRange(pre, 1+i, pre.length), Arrays.copyOfRange(in, i+1, in.length));

        return root;
    }
    
    public static void PreOrder(TreeNode root) {
        if (root == null) return;
        System.out.println(root.key);
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public boolean IsSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.key == root2.key) {
            return IsSubtree(root1.left, root2.left) && IsSubtree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return IsSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public TreeNode BSTKthNode(TreeNode pRoot, int k)
    {
        if (pRoot == null) return null;
        if (k <= 0) return null;
        int lHeight = CountNode(pRoot.left);
        if (lHeight > k - 1) return BSTKthNode(pRoot.left, k);
        if (lHeight == k - 1) return pRoot;
        return BSTKthNode(pRoot.right, k-lHeight-1);
    }

    public Tree(TreeNode root) {
        this.root = root;
    }

    public static void main(String args[]) {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(-3);
        TreeNode p4 = new TreeNode(-4);
        TreeNode p5 = new TreeNode(0);
        TreeNode p6 = new TreeNode(5);
        TreeNode p7 = new TreeNode(-1);
        p1.left = p2;
        p1.right = p3;
        p2.left =  p4;
        p2.right = p5;
        p3.left = p6;
        p3.right = p7;

        Tree t = new Tree(p1);
        // Print first deepest path
        t.DFS(p1);
        List<TreeNode> treePath = new ArrayList<>(t.treePath);
        for (TreeNode tt: treePath) {
            System.out.println(tt.key);
        }

        System.out.println("======");

        t.MaxSubTree(t.root);
        System.out.println(t.maxSum);
    }
}
