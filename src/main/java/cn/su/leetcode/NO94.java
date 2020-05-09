package cn.su.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * /**
 * * Definition for a binary tree node.
 * * public class TreeNode {
 * *     int val;
 * *     TreeNode left;
 * *     TreeNode right;
 * *     TreeNode(int x) { val = x; }
 * * }
 *
 * @author suyulong
 * @date 2019/9/23 4:03 PM
 */
public class NO94 {
    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return list;
    }

    public List<Integer> inorderTraversalV2(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public static void main(String[] args) {
        NO94 no94 = new NO94();
        TreeNode t1 = no94.new TreeNode(1);
        TreeNode t2 = no94.new TreeNode(2);
        TreeNode t3 = no94.new TreeNode(3);
        t1.right = t2;
        t2.left = t3;
        System.out.println(no94.inorderTraversalV2(t1));
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
