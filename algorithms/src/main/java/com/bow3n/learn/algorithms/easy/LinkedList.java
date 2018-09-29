package com.bow3n.learn.algorithms.easy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bowen
 */
public class LinkedList {

    ListNode head;

    ListNode head2;

    @BeforeEach
    private void setUp() {
        int[] data = new int[]{1, 2, 4};
        ListNode cur = null;
        for (int i = 0; i < data.length; i++) {
            ListNode node = new ListNode(data[i]);
            if (cur == null) {
                cur = node;
                this.head = cur;
            } else {
                cur.next = node;
                cur = node;
            }
        }

        int[] data2 = new int[]{1, 3, 4};
        ListNode cur2 = null;
        for (int i = 0; i < data2.length; i++) {
            ListNode node = new ListNode(data2[i]);
            if (cur2 == null) {
                cur2 = node;
                this.head2 = cur2;
            } else {
                cur2.next = node;
                cur2 = node;
            }
        }
    }

    public void deleteNode(ListNode node) {
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }


    @Test
    public void test_deleteNode() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
        deleteNode(new ListNode(1));
        cur = this.head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        int thePosition = n + 1;
        ListNode needDelete = head;
        int needDeletePosition = 0;
        ListNode cur = head;
        int position = 0;
        while (cur != null) {
            if (position - needDeletePosition == thePosition) {
                needDeletePosition++;
                needDelete = cur;
            }
            if (cur.next == null) {
                if (needDelete.next == null) {
                } else {
                    needDelete.next = needDelete.next.next;
                }
            }
            position++;
            cur = cur.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd_a(ListNode head, int n) {
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode cur = head;
        int right = 0;
        int length = 0;
        while (cur != null) {
            if (length - right == n) {
                right++;
            }
            length++;
            cur = cur.next;
        }
        if (right == 0) {
            return this.head.next;
        }
        int position = 0;
        cur = this.head;
        while (cur != null) {
            if (position == right - 1) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
            position++;
        }
        return head;
    }

    @Test
    public void test_removeNthFromEnd() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " , ");
            cur = cur.next;
        }
        System.out.println();
        removeNthFromEnd_a(head, 2);
        cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " , ");
            cur = cur.next;
        }
    }


    public ListNode reverseList(ListNode head) {
        ListNode temp = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = temp;
            temp = cur;
            cur = t;
        }
        return temp;
    }


    @Test
    public void test_reverseList() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " , ");
            cur = cur.next;
        }
        System.out.println();
        cur = reverseList(this.head);
        while (cur != null) {
            System.out.print(cur.val + " , ");
            cur = cur.next;
        }
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        ListNode newNode;
        ListNode newCur;
        if (l1Cur.val < l2Cur.val) {
            newNode = l1;
            l1Cur = l1Cur.next;
        } else {
            newNode = l2;
            l2Cur = l2Cur.next;
        }
        newCur = newNode;
        while (l1Cur != null && l2Cur != null) {
            if (l1Cur.val < l2Cur.val) {
                newCur.next = l1Cur;
                l1Cur = l1Cur.next;
            } else {
                newCur.next = l2Cur;
                l2Cur = l2Cur.next;
            }
            newCur = newCur.next;
        }
        if (l1Cur != null) {
            newCur.next = l1Cur;
        } else {
            newCur.next = l2Cur;
        }
        return newNode;
    }

    @Test
    public void test_mergeTwoLists() {
        ListNode n = mergeTwoLists(this.head, this.head2);
        ListNode cur = n;
        while (cur != null) {
            System.out.print(cur.val + " , ");
            cur = cur.next;
        }
    }


    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        for (int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
        }
        return true;
    }

}
