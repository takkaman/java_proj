package Tree;
//import java.math.*;

import java.util.*;

public class Tree {
    public TreeNode root;
    int maxSum = -999999;
    public Stack<TreeNode> treePath = new Stack<>();
    private Stack<TreeNode> tmpTreePath = new Stack<>();
    private ArrayList<ArrayList<Integer>> sumPath = new ArrayList<>();
    private Stack<Integer> tmpSumPath = new Stack<>();
    private int tmpSumVal = 0;

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
        System.out.println(root.key+" ");
        DFS(root.left);
        DFS(root.right);

        return;
    }

    public void DeepestPath(TreeNode root) {
        if (root == null) return;
        this.tmpTreePath.push(root);
//        System.out.println(tmpTreePath.size() + " " + treePath.size());
        if (this.tmpTreePath.size() > this.treePath.size()) {
            this.treePath = (Stack<TreeNode>) this.tmpTreePath.clone();
        }
        DeepestPath(root.left);
        DeepestPath(root.right);
        this.tmpTreePath.pop();
        return;
    }

    public void DFSFindPath(TreeNode root, int target) {
        if (root == null) return;
        this.tmpSumPath.push(root.key);
        this.tmpSumVal += root.key;

        DFSFindPath(root.left, target);
        DFSFindPath(root.right, target);

        if (root.left == null && root.right == null) { // leaf node, check sum
            if (this.tmpSumVal == target) {
                this.sumPath.add(new ArrayList<>(tmpSumPath));
            }
        }

        this.tmpSumPath.pop();
        this.tmpSumVal -= root.key;
        return;
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        DFSFindPath(root, target);
        return this.sumPath;
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
    
    public static void PreOrder2(TreeNode root) {
        if (root == null) return;
        System.out.print(root.key+" ");
        PreOrder2(root.left);
        PreOrder2(root.right);
    }

    public static void PreOrder(TreeNode root) { // Non-recursive traverse
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while (p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                System.out.print(p.key+" ");
                p = p.left;
            } else {
                p = s.pop();
                p = p.right;
            }
        }

    }

    public static void InOrder2(TreeNode root) {
        if (root == null) return;
        InOrder2(root.left);
        System.out.print(root.key+" ");
        InOrder2(root.right);
    }

    public static void InOrder(TreeNode root) { // Non-recursive traverse
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while(p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                p = s.pop();
                System.out.print(p.key+" ");
                p = p.right;
            }
        }
    }

    public static void PostOrder2(TreeNode root) {
        if (root == null) return;
        PostOrder2(root.left);
        PostOrder2(root.right);
        System.out.print(root.key+ " ");
    }

    public static void PostOrder(TreeNode root) { // Non-recursive traverse
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root, pre = null;

        while (p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                p = s.peek();
                if (p.right != null && p.right != pre) {
                    p = p.right;
                    s.push(p);
                    p = p.left;
                } else {
                    p = s.pop();
                    System.out.print(p.key+" ");
                    pre = p;
                    p = null;
                }
            }
        }
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

    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
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
//        t.DFSFindPath(p1, 3);
        t.DFS(p1);
//        List<TreeNode> treePath = new ArrayList<>(t.treePath);
//        for (TreeNode tt: treePath) {
//            System.out.println(tt.key);
//        }
//        t.PreOrder2(t.root);
        System.out.println("\n======");
//        t.PreOrder(t.root);
//        t.MaxSubTree(t.root);
//        System.out.println(t.maxSum);
    }
}
