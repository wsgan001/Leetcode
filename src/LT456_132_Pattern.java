import java.util.Stack;

/*
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

Stack
 */
public class LT456_132_Pattern {
    //O(n^2)
    public boolean find132pattern(int[] nums) {
	for (int j = 0, min = Integer.MAX_VALUE; j < nums.length; j++) {
	    min = Math.min(nums[j], min);
	    if (min == nums[j])
		continue;

	    for (int k = j + 1; k < nums.length; k++) {
		if (min < nums[k] && nums[k] < nums[j])
		    return true;
	    }
	}

	return false;
    }

    // O(n)
    // https://leetcode.com/problems/132-pattern/solution/#approach-4-using-stack-accepted
    public boolean find132pattern2(int[] nums) {
	if (nums.length < 3)
	    return false;
	Stack<Integer> stack = new Stack<>();
	int[] min = new int[nums.length];
	min[0] = nums[0];
	for (int i = 1; i < nums.length; i++)
	    min[i] = Math.min(min[i - 1], nums[i]);		//track min on the left of current number.min[p]≤min[q] for every p>q.
	for (int j = nums.length - 1; j >= 0; j--) {
	    if (nums[j] > min[j]) {				
		while (!stack.isEmpty() && stack.peek() <= min[j])		//stack.peek<nums[j]. stack.peek>min[j]
		    stack.pop();
		if (!stack.isEmpty() && stack.peek() < nums[j])
		    return true;
		stack.push(nums[j]);
	    }
	}
	return false;
    }
}
