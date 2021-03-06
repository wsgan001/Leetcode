import common.TreeNode;

/*
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Tree, DFS
 */
public class LT110_Balanced_Binary_Tree {
    // 1. Using depth counting.
    public boolean isBalanced(TreeNode root) {
	if (root == null)
	    return true;

	if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
	    return false;
	}
	return isBalanced(root.left) && isBalanced(root.right);

    }

    // 帮助方法，返回树的高度
    private int depth(TreeNode root) {
	if (root == null) {
	    return 0;
	}
	return 1 + Math.max(depth(root.left), depth(root.right));
    }

    // 2. When counting depth, has already checked if balance. can improve. bottom up
    private static final int UNBALANCED = -99;

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeight(root) != UNBALANCED;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (l == UNBALANCED || r == UNBALANCED || Math.abs(l-r) > 1) {
            return UNBALANCED;
        }
        return 1 + Math.max(l,r);
    }
}
