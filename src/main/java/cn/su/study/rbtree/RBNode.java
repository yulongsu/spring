package cn.su.study.rbtree;

/**
 * @author suyulong
 * @date 2018/5/9 下午5:53
 */
public class RBNode<T extends Comparable> {
    private boolean color;
    private T key;
    private RBNode<T> left;
    private RBNode<T> right;
    private RBNode<T> parent;

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.key = key;
        this.color = color;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public RBNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBNode<T> left) {
        this.left = left;
    }

    public RBNode<T> getRight() {
        return right;
    }

    public void setRight(RBNode<T> right) {
        this.right = right;
    }

    public RBNode<T> getParent() {
        return parent;
    }

    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }
}
