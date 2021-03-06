/*
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

Sort
 */
public class LT324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
	// various way:
	// https://discuss.leetcode.com/topic/71990/summary-of-the-various-solutions-to-wiggle-sort-for-your-reference
	int n = nums.length, mid = (n + 1) >> 1;
	int median = findKth(nums, 0, n - 1, mid);

	for (int i = 0, j = 0, k = n - 1; j <= k;) {
	    if (nums[A(j, n)] > median) {
		swap(nums, A(i++, n), A(j++, n));
	    } else if (nums[A(j, n)] < median) {
		swap(nums, A(j, n), A(k--, n));
	    } else {
		j++;
	    }
	}
    }

    private int A(int i, int n) {
	return (2 * i + 1) % (n | 1);
    }

    public static int findKth(int[] nums, int start, int end, int k) {
	if (start > end)
	    return Integer.MAX_VALUE;
	int left = partition(nums, start, end); // find left from start to end.
	if (left == k)// Found kth smallest number
	    return nums[left];
	else if (left < k)// Check right part
	    return findKth(nums, left + 1, end, k); // restrict range
	else // Check left part
	    return findKth(nums, start, left - 1, k);
    }

    public static int partition(int[] nums, int start, int end) {
	int pivot = nums[end];// Take A[end] as the pivot,
	int left = start;
	for (int i = start; i < end; i++) {
	    if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
		swap(nums, left++, i);
	}
	swap(nums, left, end);// Finally, swap A[end] with A[left]
	return left;
    }

    private static void swap(int[] nums, int i, int j) {
	int t = nums[i];
	nums[i] = nums[j];
	nums[j] = t;
    }
    
    public static void main(String[] args){
	int[] nums = {1, 5, 1, 1, 6, 4};
	System.out.println(findKth(nums, 0, 5, 3));
    }

    // 若原数组长度为偶数，则为小->大->小。。。->大的形式，小的数从后往前(len - 2)寻找位置插入，大的数从前往后(1)寻找位置插入
    // 若原数组长度为奇数，则为大->小->大。。。->小的形式，小的数从前往后(0)寻找位置插入，大的数从后往前(len - 2)寻找位置插入
    // dumber insert.
    public void wiggleSort2(int[] nums) {
	// Write your code here
	if (nums == null || nums.length <= 1) {
	    return;
	}

	int n = nums.length, mid = (n + 1) >> 1;
	int avg = findKth(nums, 0, n - 1, mid);
	int[] ans = new int[nums.length];
	for (int i = 0; i < nums.length; i++) {
	    ans[i] = avg;
	}

	int l, r;
	if (nums.length % 2 == 0) {
	    l = nums.length - 2;
	    r = 1;
	    for (int i = 0; i < nums.length; i++) {
		if (nums[i] < avg) {
		    ans[l] = nums[i];
		    l -= 2;
		} else if (nums[i] > avg) {
		    ans[r] = nums[i];
		    r += 2;
		}
	    }
	} else {
	    l = 0;
	    r = nums.length - 2;
	    for (int i = 0; i < nums.length; i++) {
		if (nums[i] < avg) {
		    ans[l] = nums[i];
		    l += 2;
		} else if (nums[i] > avg) {
		    ans[r] = nums[i];
		    r -= 2;
		}
	    }
	}

	for (int i = 0; i < nums.length; i++) {
	    nums[i] = ans[i];
	}
    }

}
