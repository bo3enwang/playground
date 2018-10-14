package com.bow3n.learn.algorithms.easy;


import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

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

}
