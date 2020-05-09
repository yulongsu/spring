package cn.su.leetcode;

import java.util.LinkedList;
import java.util.Stack;

import apple.laf.JRSUIUtils.Tree;
import javafx.util.Pair;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 *
 * @author suyulong
 * @date 2019/9/23 8:33 PM
 */
public class NO111 {
    /**
     * 递归
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int depth = Integer.MAX_VALUE;

        if(root.left != null){
            depth = Math.min(minDepth(root.left),depth);
        }

        if(root.right != null){
            depth = Math.min(minDepth(root.right),depth);
        }

        return depth + 1;
    }

    /**
     * 深度，所以用栈
     * @param root
     * @return
     */
    public static int minDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<Pair<TreeNode,Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root,1));
        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()){
            Pair<TreeNode,Integer> pair = stack.pop();
            TreeNode current = pair.getKey();
            Integer depth = pair.getValue();
            if(current.left == null && current.right ==null){
                minDepth = Math.min(minDepth,depth);
            }
            if(current.left!=null){
                stack.push(new Pair<>(current.left,depth+1));
            }
            if(current.right!=null){
                stack.push(new Pair<>(current.right,depth+1));
            }
        }

        return minDepth;
    }

    /**
     * 广度，所以需要用队列
     * @param root
     * @return
     */
    public static int minDepthV3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<Pair<TreeNode,Integer>> list = new LinkedList<>();
        list.add(new Pair<>(root,1));
        int minDepth=0;
        while (!list.isEmpty()){
            Pair<TreeNode,Integer> pair = list.poll();
            TreeNode current = pair.getKey();
            minDepth = pair.getValue();
            if(current.left == null && current.right ==null){
                break;
            }
            if(current.left!=null){
                list.addLast(new Pair<>(current.left,minDepth+1));
            }
            if(current.right!=null){
                list.addLast(new Pair<>(current.right,minDepth+1));
            }
        }

        return minDepth;
    }

    public static void main(String[] args) {
        System.out.println(minDepth(TreeNode.otherTree()));
        System.out.println(minDepthV2(TreeNode.otherTree()));
        System.out.println(minDepthV3(TreeNode.otherTree()));
    }
}
