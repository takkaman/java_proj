package AVLTree;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class AVLTree {
    public AVLNode root;

    public boolean isEmpty() {
        return root==null;
    }

    public int GetHeight(AVLNode root) {
        if (root == null) return 0;
        return root.height;
    }

    public void Insert(int data) {
        this.root = Insert(data, root);
    }
    public AVLNode Insert(int data, AVLNode root) {
        if (root == null) return new AVLNode(data);
        if (root.key > data) {
            root.left = Insert(data, root.left);
//            System.out.printf("Height: left: %d, right: %d\n", GetHeight(root.left), GetHeight(root.right));
            if (GetHeight(root.left) - GetHeight(root.right) > 1) {
                if (root.left.key > data) { // LL rotate
                    return LLRotate(root);
                } else { // LR rotate
                    return LRRotate(root);
                }
            }
        } else {
            root.right = Insert(data, root.right);
//            System.out.printf("Height: left: %d, right: %d\n", GetHeight(root.left), GetHeight(root.right));
            if (GetHeight(root.right) - GetHeight(root.left) > 1) {
                if (root.right.key < data) { // RR rotate
                    return RRRotate(root);
                } else { // RL rotate
                    return RLRotate(root);
                }
            }
        }
        root.height = Math.max(GetHeight(root.left), GetHeight(root.right)) + 1;
        return root;
    }

    public void Erase(int data) {
        this.root = Erase(data, this.root);
    }

    public AVLNode Erase(int data, AVLNode root) {
        return root;
    }

    public AVLNode LLRotate(AVLNode root) {
        AVLNode newRoot = root.left;
        AVLNode tmpNode = newRoot.right;
        newRoot.right = root;
        root.left = tmpNode;
        root.height = Math.max(GetHeight(root.left), GetHeight(root.right)) + 1;
        newRoot.height = Math.max(GetHeight(newRoot.left), GetHeight(root)) + 1;
        return newRoot;
    }

    public AVLNode RRRotate(AVLNode root) {
        AVLNode newRoot = root.right;
        AVLNode tmpNode = newRoot.left;
        newRoot.left = root;
        root.right = tmpNode;
        root.height = Math.max(GetHeight(root.left), GetHeight(root.right)) + 1;
        newRoot.height = Math.max(GetHeight(newRoot.right), GetHeight(root)) + 1;
        return newRoot;
    }

    public AVLNode LRRotate(AVLNode root) {
        root.left = RRRotate(root.left);
        return LLRotate(root);
    }

    public AVLNode RLRotate(AVLNode root) {
        root.right = LLRotate(root.right);
        return RRRotate(root);
    }

    public static void PreOrder(AVLNode root) {
        if (root == null) return;
        System.out.println(root.key);
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public static void main(String args[]) {
        AVLTree avlTree = new AVLTree();
        for (int i = 1; i <8 ; i++) {
            avlTree.Insert(i);
//            System.out.println(avlTree.root.key);
        }

        avlTree.PreOrder(avlTree.root);
        System.out.println("======");
    }
}
