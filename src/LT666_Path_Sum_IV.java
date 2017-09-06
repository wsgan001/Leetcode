/*
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.

Tree
 */
public class LT666_Path_Sum_IV {
    //              0
    //      0       1
    //   0   1     2   3
    // 0 1  2 3   4 5  6 7
    // Regardless whether these nodes exist:
    // the position of left child is always parent_pos * 2;
    // the position of right child is alwaysparent_pos * 2 + 1;
    // the position of parent is always child_pos / 2;
    public int pathSum(int[] nums) {
        int[][] m = new int[5][8];
        for (int n : nums) {
            int i = n / 100; // i is 1 based index;
            int j = (n % 100) / 10 - 1; // j used 0 based index;
            int v = n % 10;
            m[i][j] = m[i - 1][j / 2] + v;          //store path sum
        }

        int sum = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 4 || m[i][j] != 0 && m[i + 1][j * 2] == 0 && m[i + 1][j * 2 + 1] == 0){        //only add leaf
                    sum += m[i][j];
                }
            }
        }
        return sum;        
    }
}
