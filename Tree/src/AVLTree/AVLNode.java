package AVLTree;


public class AVLNode {
    int key;
    int height;
    AVLNode left = null;
    AVLNode right = null;

    AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }


}
