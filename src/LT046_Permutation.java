
/*
 Given a collection of distinct numbers, return all possible permutations.
 For example,
 [1,2,3] have the following permutations:
 [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
/*
 * Backtracking
 */
import java.util.*;

public class LT046_Permutation {
    public List<List<Integer>> permute(int[] nums) {
	List<List<Integer>> res = new ArrayList<>();
	helper(res, new ArrayList<>(), nums);
	return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> elm, int[] nums) {
	if (elm.size() == nums.length) {
	    res.add(new ArrayList<>(elm));
	} else {
	    for (int i = 0; i < nums.length; i++) { // start from 0. at each
						    // position put all possible
						    // num
		if (elm.contains(nums[i]))
		    continue; // element already exists, skip
		elm.add(nums[i]);
		helper(res, elm, nums);
		elm.remove(elm.size() - 1);
	    }
	}
    }
}
