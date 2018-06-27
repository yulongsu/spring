package cn.su.study.algorithm.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author suyulong
 * @date 2018/6/21 19:44
 */
public class BinaryTree {
    /**
     * @author  二叉树的先序中序后序排序
     *
     *        6
     *    3      9
     *  1   5  7
     *   2 4     8
     *
     * 先序遍历
     * 631254978
     * 中序遍历
     * 123456789
     * 后序遍历
     * 214538796
     *
     * 最大深度
     * 4
     *
     * 最小深度
     * 2
     *
     * 层次遍历
     * 639157248
     *
     *
     */
    public Node init() {
        //注意必须逆序建立，先建立子节点，再逆序往上建立，
        //因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;   //返回根节点
    }

    public void printNode(Node node) {
        System.out.print(node.getData());
    }

    public void theFirstTraversal(Node root) {  //先序遍历
        printNode(root);
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {  //递归遍历右孩子
            theFirstTraversal(root.getRightNode());
        }
    }

    public void theInOrderTraversal(Node root) {  //中序遍历
        if (root.getLeftNode() != null) {
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if (root.getRightNode() != null) {
            theInOrderTraversal(root.getRightNode());
        }
    }

    public void thePostOrderTraversal(Node root) {  //后序遍历
        if (root.getLeftNode() != null) {
            thePostOrderTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }

    /**
     * 二叉树的最大深度是距根节点路径最长的某一树叶节点的深度。
     * 二叉树的深度等于二叉树的高度，也就等于根节点的高度。根节点的高度为左右子树的高度较大者+1。
     * 由此思想可用递归求解，其实也就是后序遍历二叉树的算法。
     * @param root
     * @return
     */
    public  int maxDepth(Node root){
        int depth = 0;
        if(root != null){
            int ldepth = maxDepth(root.getLeftNode());
            int rdepth = maxDepth(root.getRightNode());
            depth = (ldepth > rdepth) ? ldepth + 1:rdepth +1;
        }
        return depth;
    }

    /**
     * 二叉树的最小深度，等于左右子树深度较小者+1。
     * 这里需要注意的问题是：求最大深度的时候，只需要比较左右子树的深度，取较大者+1就行了；
     * 但是求最小深度的时候，需要区分双子树与单子树，双子树时，深度较小者+1，
     * 单子树时（即左右子树有一颗为空时）为深度较大者+1。
     * 主要思想仍然是后序递归遍历。
     * @param root
     * @return
     */
    public int minDepth(Node root) {
        int depth = 0;
        if(root != null){
            int l = minDepth(root.getLeftNode());
            int r = minDepth(root.getRightNode());
            if(l == 0 || r == 0) {
                depth =  l+r+1;
            }
            depth = (l<r ? l : r) + 1;
        }
        return depth;
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public void level_Iterator(Node root){
        if(root == null){
            return;
        }

        LinkedList<Node> ll = new LinkedList<>();
        Node current = null;
        ll.offer(root);

        while(!ll.isEmpty()){
            current = ll.poll();
            System.out.print(current.getData());
            if(current.getLeftNode() != null){
                ll.offer(current.getLeftNode());
            }
            if(current.getRightNode() != null){
                ll.offer(current.getRightNode());
            }
        }
    }

    /**
     * 层次遍历
     * 按层输出
     *
     * @param root
     */
    public void level_Iterator_2(Node root){
        if(root == null){
            return;
        }

        LinkedList<Node> ll = new LinkedList<>();
        Node current = null;
        ll.offer(root);

        //两个变量curLevelCount和nextLevelCount来分别保存当前层和下一层的结点数。
        int curLevelCount = 1, nextLevelCount = 0;

        while(!ll.isEmpty()){
            current = ll.poll();
            System.out.print(current.getData());
            curLevelCount --;
            if(current.getLeftNode() != null){
                ll.offer(current.getLeftNode());
                nextLevelCount ++;
            }
            if(current.getRightNode() != null){
                ll.offer(current.getRightNode());
                nextLevelCount ++;
            }
            if(curLevelCount == 0){
                System.out.println();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }

        }

    }

    /**
     * 深度遍历
     *
     * 压栈的时候，先将改结点的右子树压栈，然后再左，根据栈的特点，就会变成深度遍历
     *
     * @param root
     */
    public void depthFirstTravel(Node root){
        //继承了vector 默认大小10
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.getData());
            if(node.getRightNode() != null){
                stack.push(node.getRightNode());
            }
            if(node.getLeftNode() != null){
                stack.push(node.getLeftNode());
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = tree.init();

        System.out.println("先序遍历");
        tree.theFirstTraversal(root);
        System.out.println("");

        System.out.println("中序遍历");
        tree.theInOrderTraversal(root);
        System.out.println("");

        System.out.println("后序遍历");
        tree.thePostOrderTraversal(root);
        System.out.println("");

        System.out.println("最大深度");
        System.out.println(tree.maxDepth(root));
        System.out.println("");


        System.out.println("最小深度");
        System.out.println(tree.minDepth(root));
        System.out.println("");


        System.out.println("层次遍历");
        tree.level_Iterator(root);
        System.out.println("");


        System.out.println("层次遍历,按层输出");
        tree.level_Iterator_2(root);
        System.out.println("");


        System.out.println("深度遍历");
        tree.depthFirstTravel(root);
        System.out.println("");
    }
}
