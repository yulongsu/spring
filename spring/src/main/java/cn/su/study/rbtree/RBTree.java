package cn.su.study.rbtree;

/**
 * @author suyulong
 * @date 2018/5/9 下午4:00
 */
public class RBTree<T extends Comparable> {
    private RBNode<T> root;

    private final static boolean RED = false;
    private final static boolean BLACK = true;

    public RBTree(RBNode<T> root) {this.root = root;}

    private void preOrder(RBNode<T> root) {
        if (root != null) {
            System.out.println(root.getKey());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void preOrder(){
        preOrder(this.root);
    }


}
