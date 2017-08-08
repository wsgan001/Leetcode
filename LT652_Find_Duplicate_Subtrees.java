import java.util.*;

/*
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

Tree
 */
public class LT652_Find_Duplicate_Subtrees {
    //do preorder serialzation of all nodes. check if the serialization string exist already
    HashSet<String> list = new HashSet<>();
    List<TreeNode> res = new ArrayList<>();
    HashSet<String> done = new HashSet<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) return res;
        
        if (root != null) {
            StringBuilder sb = new StringBuilder();
            Serialize(root, sb);
            String s = sb.toString();
            
            if (!list.contains(s)) {                   
                list.add(s);                
            } else {       
                if (!done.contains(s)) {            //save in result only once
                    res.add(root);
                    done.add(s);  
                }                    
            }
            
            findDuplicateSubtrees(root.left);
            findDuplicateSubtrees(root.right);
        }
        
        return res;
        
    }
    
    public void Serialize(TreeNode root, StringBuilder sb) {       
        String del = ",";
        sb.append(((root == null)? "null": root.val) + del);
        if (root != null) {
            Serialize(root.left, sb);
            Serialize(root.right, sb);
        }        
    } 
}