import java.util.PriorityQueue;

/*
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

 */
public class LT480_Sliding_Window_Median {
    public double[] medianSlidingWindow(int[] nums, int k) {
        //similar as Meadian of DS. Using two priority queue. 
        //O(nk). The drawback is remove function of priorityQueue takes O(k) time.
        double[] res = new double[nums.length - k + 1];
        int idx = 0;
        boolean useBoth = k % 2 == 0;
        PriorityQueue<Integer> small = new PriorityQueue<>((a, b)->{return (int)((double)b-a);});
        PriorityQueue<Integer> big = new PriorityQueue<>();
        for(int i = 0; i<nums.length; i++){                         //right
            if(small.size() + big.size()==k){
                Integer toRemove = new Integer(nums[i-k]);          //left
                if(toRemove <= small.peek()) {
                    small.remove(toRemove);
                }else {
                    big.remove(toRemove);
                }
            }
            
            //always keep small.size() == big.size() or small.size() == big.size()+1
            if(small.size()<=big.size()) {
                small.add(nums[i]);
            }else{ 
                big.add(nums[i]);
            }
            
            if(big.size()>0){                                       //balance two heap to have small = big or small = big+1
                while(small.peek()>big.peek()){
                    big.add(small.poll());
                    small.add(big.poll());
                }
            }
            
            if(small.size() + big.size()==k){
                if(useBoth) {
                    res[idx++] = ((double)small.peek() + big.peek())/2.0;
                }else {
                    res[idx++] = (double)small.peek();              //only take small. guaranteed by previous lines
                }
            }
        }
        return res;
    }
    
    //https://discuss.leetcode.com/topic/79642/short-and-clear-o-nlogk-java-solutions
    //To overcome priorityQueue's remove O(k) problem and make our solution O(nlogk), we can replace head/priorityQueue to BST


}
