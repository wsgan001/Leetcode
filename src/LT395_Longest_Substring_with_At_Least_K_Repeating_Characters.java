/*
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LT395_Longest_Substring_with_At_Least_K_Repeating_Characters {
    public int longestSubstring(String s, int k) {
	// divide and conquer. 0-s.length. cannot satisfy k. find a point to cut so that satisfy
	char[] str = s.toCharArray();
	return helper(str, 0, s.length(), k);
    }

    private int helper(char[] str, int start, int end, int k) {
	if (end - start < k)
	    return 0; // substring length shorter than k. stop early
	int[] count = new int[26];
	for (int i = start; i < end; i++) {
	    int idx = str[i] - 'a';
	    count[idx]++;
	}
	for (int i = 0; i < 26; i++) {
	    if (count[i] < k && count[i] > 0) { // count[i]=0 => i+'a' does not exist in the string, skip it.
		for (int j = start; j < end; j++) {
		    if (str[j] == i + 'a') { // exclude j.
			int left = helper(str, start, j, k);
			int right = helper(str, j + 1, end, k);
			return Math.max(left, right);
		    }
		}
	    }
	}
	return end - start; // if substring satisfy k. then return end-start
    }
}
