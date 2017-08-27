import java.util.List;

import common.NestedInteger;

/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

DFS
 */

public class LT364_Nested_List_Weight_Sum_II {
    public int depthSumInverse(List<NestedInteger> nestedList) {
	// similar as I. but need the calculate depth first
	if (nestedList == null || nestedList.size() == 0)
	    return 0;
	int h = helper(nestedList);
	int res = getSum(nestedList, h);
	return res;
    }

    private int getSum(List<NestedInteger> l, int layer) {
	int sum = 0;
	if (l == null || l.size() == 0)
	    return sum;
	for (NestedInteger n : l) {
	    if (n.isInteger())
		sum += n.getInteger() * layer;
	    else
		sum += getSum(n.getList(), layer - 1);
	}
	return sum;
    }

    private int helper(List<NestedInteger> l) {
	if (l == null || l.size() == 0)
	    return 0;
	int max = 0;
	for (NestedInteger n : l) {
	    if (n.isInteger())
		max = Math.max(max, 1);
	    else
		max = Math.max(max, helper(n.getList()) + 1);
	}
	return max;
    }
}
