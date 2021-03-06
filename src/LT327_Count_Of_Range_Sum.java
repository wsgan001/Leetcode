/*
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ? j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

Divide and Conquer, BST
 */
public class LT327_Count_Of_Range_Sum {
    // https://discuss.leetcode.com/topic/34108/summary-of-the-divide-and-conquer-based-and-binary-indexed-tree-based-solutions/3
    // Naive O(n^2) with presum
    public int countRangeSum(int[] nums, int lower, int upper) {
	int n = nums.length;
	long[] sums = new long[n + 1];
	for (int i = 0; i < n; ++i)
	    sums[i + 1] = sums[i] + nums[i];
	int ans = 0;
	for (int i = 0; i < n; ++i)
	    for (int j = i + 1; j <= n; ++j)
		if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
		    ans++;
	return ans;
    }

    // Merge sort
    // 首先预处理数组的前缀和，保存到数组 sum 中。然后用归并排序对数组 sum 进行排序，归并排序中有一步调用 merge 函数，将有序的左数组和右数组进行合并，而这时的右数组中的任一元素在 sum 数组中的位置正是在左数组任一元素之后！利用这，我们可以在 merge 前，对 left 数组和 right 数组满足条件的元素进行求解。

    // https://discuss.leetcode.com/topic/33738/share-my-solution/2
    public int countRangeSum2(int[] nums, int lower, int upper) {
	int n = nums.length;
	long[] sums = new long[n + 1];
	for (int i = 0; i < n; ++i)
	    sums[i + 1] = sums[i] + nums[i];
	return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
	if (end - start <= 1)
	    return 0;
	int mid = (start + end) / 2;
	int count = countWhileMergeSort(sums, start, mid, lower, upper)
		+ countWhileMergeSort(sums, mid, end, lower, upper);
	int j = mid, k = mid, t = mid;
	long[] cache = new long[end - start];
	// 左半边[start, mid) 和右半边 [mid, end) 排序了
	// 当我们遍历左半边，对于每个i，我们需要在右半边找出k和j，使其满足：
	// j是第一个满足 sums[j] - sums[i] > upper 的下标
	// k是第一个满足 sums[k] - sums[i] >= lower 的下标
	// 那么在[lower, upper]之间的区间的个数是j - k
	// 同时我们也需要另一个下标t，用来拷贝所有满足sums[t] < sums[i]到一个寄存器Cache中以完成混合排序的过程
	for (int i = start, r = 0; i < mid; ++i, ++r) {
	    while (k < end && sums[k] - sums[i] < lower)
		k++;
	    while (j < end && sums[j] - sums[i] <= upper)
		j++;
	    while (t < end && sums[t] < sums[i])
		cache[r++] = sums[t++];
	    cache[r] = sums[i];
	    count += j - k;
	}
	System.arraycopy(cache, 0, sums, start, t - start);
	return count;
    }
}
