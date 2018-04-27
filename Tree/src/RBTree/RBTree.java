package RBTree;

public class RBTree {
    public class RBTreeNode {
        boolean color;
        int key;
        RBTreeNode left = null;
        RBTreeNode right = null;
        RBTreeNode parent = null;

        public RBTreeNode(boolean color, int key) {
            this.color = color;
            this.key = key;
        }
    }

    public static void main(String[] args) {
        RBTree t = new RBTree();

    }
}
