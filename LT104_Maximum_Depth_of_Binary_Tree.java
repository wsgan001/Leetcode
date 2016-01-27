import java.util.LinkedList;

/*
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Tree, DFS
 */
public class LT104_Maximum_Depth_of_Binary_Tree {
	//recursive
	public int maxDepth(TreeNode root) {
		if(root==null) return 0;
		return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }
	
	//iterative. level order
	public int maxDepth2(TreeNode root) {
        if(root==null) return 0;
        
        LinkedList<TreeNode> queue = new LinkedList<>();
		int depth = 0;
        queue.add(root);
        int curLevel = 1;
        int nextLevel = 0;
        while(!queue.isEmpty()){
        	for(int i=0;i<curLevel;i++){
	        	TreeNode cur = queue.poll();
	        	if(cur.left!=null){
	        		queue.add(cur.left);
	        		nextLevel++;
	        	}
	        	if(cur.right!=null){
	        		queue.add(cur.right);
	        		nextLevel++;
	        	}
        	}
        	curLevel = nextLevel;
        	nextLevel = 0;
        	depth++;
        }
        
        return depth;
    }
}
