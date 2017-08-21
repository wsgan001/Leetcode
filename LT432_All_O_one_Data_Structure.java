import java.util.*;

/*
 * Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.

Design
 */
public class LT432_All_O_one_Data_Structure {
    class Node{
        String key;
        int val;
        public Node(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    /** Initialize your data structure here. */
    HashMap<String, Node> map;
    PriorityQueue<Node> minQ;
    PriorityQueue<Node> maxQ;
    
    public LT432_All_O_one_Data_Structure() {
        map = new HashMap<String, Node>();
        minQ = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });        
        maxQ = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                return b.val - a.val;
            }
        });
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            map.put(key, new Node(key, 1));
            Node node = map.get(key);
            minQ.add(node);
            maxQ.add(node);
        } else {
            Node node = map.get(key);
            minQ.remove(node);
            maxQ.remove(node);
            node.val++;
            map.put(key, node);
            minQ.add(node);
            maxQ.add(node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            if (node.val == 1) {
                map.remove(key);
                minQ.remove(node);
                maxQ.remove(node);
            } else {
                minQ.remove(node);
                maxQ.remove(node);
                node.val--;
                map.put(key, node);
                minQ.add(node);
                maxQ.add(node);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return maxQ.isEmpty() ? "" : maxQ.peek().key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return minQ.isEmpty() ? "" : minQ.peek().key;
    }
}
