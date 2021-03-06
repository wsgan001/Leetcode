import java.util.*;

/*
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

DP
 */
public class LT403_Frog_Jump {
    // Use map to represent a mapping from the stone (not index) to the steps that can be taken from this stone.
    // so this will be
    // [0,1,3,5,6,8,12,17]
    // {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}
    // Notice that no need to calculate the last stone.
    // On each step, we look if any other stone can be reached from it, if so, we update that stone's steps by adding step, step + 1, step - 1. If we can reach the final stone, we return true. No need
    // to calculate to the last stone.
    public boolean canCross(int[] stones) {
	if (stones.length == 0) {
	    return true;
	}

	HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
	map.put(0, new HashSet<Integer>());
	map.get(0).add(1);
	for (int i = 1; i < stones.length; i++) { // map<stonePos, setOfPossibleStep>
	    map.put(stones[i], new HashSet<Integer>());
	}

	for (int i = 0; i < stones.length - 1; i++) {
	    int stone = stones[i]; 			// loop all possible step of curStone
	    for (int step : map.get(stone)) { 		// map.get(stone) can be emptySet. as one stone could be NOT able to reach.
		int reach = step + stone; 		// find possible reach.
		if (reach == stones[stones.length - 1]) { // if reach the last stone. return true
		    return true;
		}

		if (map.containsKey(reach)) { 		// set could be null when reach is not a stone.
		    map.get(reach).add(step);
		    if (step - 1 > 0)
			map.get(reach).add(step - 1);
		    map.get(reach).add(step + 1);
		}
	    }
	}

	return false;
    }

    // dp ?
    public boolean canCross2(int[] stones) {
	HashMap<Integer, Set<Integer>> map = new HashMap<>();
	for (int i = 0; i < stones.length; i++) {
	    map.put(stones[i], new HashSet<Integer>());
	}
	map.get(0).add(0);
	for (int i = 0; i < stones.length; i++) {
	    for (int k : map.get(stones[i])) {
		for (int step = k - 1; step <= k + 1; step++) {
		    if (step > 0 && map.containsKey(stones[i] + step)) {
			map.get(stones[i] + step).add(step);
		    }
		}
	    }
	}
	return map.get(stones[stones.length - 1]).size() > 0;
    }
    
    public boolean canCross3(int[] stones) {
        int k = 0;
        return helper(stones, 0, k);
    }

    private boolean helper(int[] stones, int index, int k) {
        //目前已经达到了
        if (index == stones.length - 1) return true;
        //选择k的步伐，范围k-1到k
        for (int i = k - 1; i <= k + 1; i++) {
            int nextJump = stones[index] + i;
            //看接下来有没有合适的石头可以跳过去，从接下来的位置中查找有没有nextJump的位置，有的话返回石头的编号
            int nextPosition = Arrays.binarySearch(stones, index + 1, stones.length, nextJump);
            if (nextPosition > 0) {
                if (helper(stones, nextPosition, i)) return true;
        }
    }

    return false;
}
}
