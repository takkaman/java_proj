package Tree;
//import java.util.*;
//import java.math.*;

import java.util.*;

public class Tree {
    public TreeNode root;
    int maxSum = -999999;

    public int GetHeight(TreeNode root) {
        if (root == null) return 0;
        int lHeight = GetHeight(root.left);
        int rHeight = GetHeight(root.right);
        return (lHeight == 0 || rHeight == 0)? lHeight + rHeight + 1: Math.max(lHeight, rHeight) + 1;
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

    public static void PreOrder(TreeNode root) {
        if (root == null) return;
        System.out.println(root.key);
        PreOrder(root.left);
        PreOrder(root.right);
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
        t.PreOrder(p1);
        System.out.println("======");
        t.MaxSubTree(t.root);
        System.out.println(t.maxSum);
    }
}