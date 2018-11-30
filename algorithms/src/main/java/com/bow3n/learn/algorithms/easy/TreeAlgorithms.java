package com.bow3n.learn.algorithms.easy;


import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeAlgorithms {
    private TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        return trave(root);
    }


    /*
    思路1 对每一个节点，检测其值是否大于左子树节点，是否小于右子树节点。（错误）
    思路2 对每个节点，检测其值是否大于左子树的最大值，是否小于右子树的最小值。
    思路3 中序遍历判断是否是升序数组
     */
    public boolean trave(TreeNode treeNode) {
        if (treeNode != null) {
            if (!trave(treeNode.left)) {
                return false;
            }
            if (prev != null && treeNode.val <= prev.val) {
                return false;
            }
            prev = treeNode;
            if (!trave(treeNode.right)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_isValidBST() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(20);

        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
//        treeNode3.left = treeNode4;
//        treeNode3.right = treeNode5;
        System.out.println(isValidBST(treeNode1));


    }


    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isTreeSymmetric(root.left, root.right);
    }


    public static boolean isTreeSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null) {
            return false;
        }
        if (right == null) {
            return false;
        }
        return (left.val == right.val) && isTreeSymmetric(left.left, right.right) && isTreeSymmetric(left.right, right.left);
    }

    public static void preOrder(TreeNode treeNode, List<Integer> list) {

        if (treeNode != null) {
            list.add(treeNode.val);
            preOrder(treeNode.left, list);
            preOrder(treeNode.right, list);
        }
    }

    public static void postOrder(TreeNode treeNode, List<Integer> list) {
        if (treeNode != null) {
            postOrder(treeNode.left, list);
            postOrder(treeNode.right, list);
            list.add(treeNode.val);
        }
    }

    @Test
    public void test_isSymmetric() {
        TreeNode root = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(20);

        root.left = treeNode2;
        root.right = treeNode3;

        System.out.println(isSymmetric(root));
    }

}
