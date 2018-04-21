package AVLTree;

public class AVLTree {
    public AVLNode root;

    public boolean isEmpty() {
        return root==null;
    }

    public static void main(String args[]) {
        AVLTree avlTree = new AVLTree();
        System.out.println(avlTree.isEmpty());
        System.out.println("======");
    }
}
