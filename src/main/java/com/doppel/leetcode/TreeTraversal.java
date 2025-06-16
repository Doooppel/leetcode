package com.doppel.leetcode;

import java.util.*;

public class TreeTraversal {
    public static void main(String[] args) {
        TreeTraversal treeTraversal = new TreeTraversal();
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> integers = treeTraversal.postorderTraversalIteration(treeNode);
        System.out.println("integers = " + integers);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    public List<Integer> inorderTraversalIteration(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> que = new Stack<>();
        while (root != null || !que.isEmpty()) {
            if (root != null) {
                que.push(root);
                root = root.left;
            } else {
                TreeNode first = que.pop();
                list.add(first.val);
                root = first.right;
            }
        }
        return list;
    }

    public List<Integer> preorderTraversalRecursion(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        list.addAll(preorderTraversalRecursion(root.left));
        list.addAll(preorderTraversalRecursion(root.right));
        return list;
    }

    public List<Integer> preorderTraversalIteration(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                list.add(root.val);
                stack.push(root.right);
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return list;
    }

    public List<Integer> postorderTraversalRecursion(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.addAll(preorderTraversalRecursion(root.left));
        list.addAll(preorderTraversalRecursion(root.right));
        list.add(root.val);
        return list;
    }


    public List<Integer> postorderTraversalIteration(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                if (root.left != null) {
                    prev = root;
                }
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                if (prev != null) {
                    root = prev.right;
                    list.add(root.val);
                }
                root = stack.pop();
                if (root != null) {
                    list.add(root.val);
                }
            }
        }
        return list;
    }

}
