import common.ListNode;

/*
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Linkedlist
 */
public class LT206_Reverse_Linked_List {

    // 1.recursive
    public ListNode reverseList(ListNode head) {
	// What is the reverse of null (the empty list)? null.
	// What is the reverse of a one element list? the element.
	// What is the reverse of an n element list? the reverse of the second element on followed by the first element.

	if (head == null || head.next == null)
	    return head;
	ListNode next = reverseList(head.next);
	head.next.next = head;
	head.next = null;
	return next;
    }

    // 2. iterative. recheck
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
