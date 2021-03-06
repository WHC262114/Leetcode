package Problems;

/**
 * @Project: leetcode
 * @Package: Problems
 * @Description: Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list.
 *              A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
 *              If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 * @Author: Wang Haichao
 * @CreateTime: 2020/5/15 18:57
 **/
public class Problem0707 {
}

class MyLinkedList {

    public int size;
    public ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);             //伪头
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode cur_node = head;
        for(int i = 0; i <= index; i++){
            cur_node = cur_node.next;
        }
        return cur_node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size) return;
        if(index < 0 ) index = 0;

        size ++;
        ListNode cur_node = head;
        for(int i = 0; i < index; i++){
            cur_node = cur_node.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = cur_node.next;
        cur_node.next = toAdd;

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index>=size || index < 0) return;
        ListNode cur_node = head;
        size--;
        for(int i = 0; i < index; i++){
            cur_node = cur_node.next;
        }
        cur_node.next = cur_node.next.next;
    }
}

class MyDoublyLinkedList {

    int size;
    // sentinel nodes as pseudo-head and pseudo-tail
    ListNode head, tail;

    /** Initialize your data structure here. */
    public MyDoublyLinkedList() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        // if index is invalid
        if (index < 0 || index >= size) return -1;

        // choose the fastest way: to move from the head
        // or to move from the tail
        ListNode curr = head;
        if (index + 1 < size - index)
            for(int i = 0; i < index + 1; ++i) curr = curr.next;
        else {
            curr = tail;
            for(int i = 0; i < size - index; ++i) curr = curr.prev;
        }

        return curr.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode pred = head, succ = head.next;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode succ = tail, pred = tail.prev;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
// If index is greater than the length,
        // the node will not be inserted.
        if (index > size) return;

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) index = 0;

        // find predecessor and successor of the node to be added
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;
            succ = pred.next;
        }
        else {
            succ = tail;
            for (int i = 0; i < size - index; ++i) succ = succ.prev;
            pred = succ.prev;
        }

        // insertion itself
        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        // if the index is invalid, do nothing
        if (index < 0 || index >= size) return;

        // find predecessor and successor of the node to be deleted
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;
            succ = pred.next.next;
        }
        else {
            succ = tail;
            for (int i = 0; i < size - index - 1; ++i) succ = succ.prev;
            pred = succ.prev.prev;
        }

        // delete pred.next
        --size;
        pred.next = succ;
        succ.prev = pred;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode prev;
    ListNode(int x) { val = x; }
}



/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */