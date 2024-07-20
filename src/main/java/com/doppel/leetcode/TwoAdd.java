package com.doppel.leetcode;


public class TwoAdd {
    public static void main(String[] args) {
        ListNode first = new ListNode(2, new ListNode(4, new ListNode(9)));
        ListNode second = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        //344 + 465 = 809
        //344 + 46 = 390
        //344 + 6 = 350
        ListNode listNode = addTwoNumbers2(first, second);
        System.out.println("listNode = " + listNode);
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode resultNode = null;
        int remainder = 0;
        while (true) {
            if (l1 == null && l2 == null) {
                if (remainder != 0) {
                    addToTail(resultNode, new ListNode(remainder));
                }
                return resultNode;
            }
            int result = 0;
            result = remainder;
            remainder = 0;
            if (l1 == null) {
                result = result + l2.val;
                l2 = l2.next;

            } else if (l2 == null) {
                result = result + l1.val;
                l1 = l1.next;

            } else {
                result = result + l1.val + l2.val;
                l2 = l2.next;
                l1 = l1.next;
            }
            int current = 0;
            if (result >= 10) {
                current = result % 10;
                remainder = result / 10;
            } else {
                current = result;
            }
            if (resultNode == null) {
                resultNode = new ListNode(current);
            } else {
                addToTail(resultNode, new ListNode(current));
            }
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder(String.valueOf(l1.val));
        ListNode tmpNext1 = l1;
        while (tmpNext1.next != null) {
            sb1.append(tmpNext1.next.val);
            tmpNext1 = tmpNext1.next;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(l2.val));
        ListNode tmpNext2 = l2;
        while (tmpNext2.next != null) {
            sb2.append(tmpNext2.next.val);
            tmpNext2 = tmpNext2.next;
        }

        java.math.BigDecimal result = new java.math.BigDecimal(sb1.reverse().toString()).add(new java.math.BigDecimal((sb2.reverse().toString())));
        String strResult = new StringBuilder(String.valueOf(result)).reverse().toString();
        char[] chars = strResult.toCharArray();
        ListNode listNode = new ListNode(Character.getNumericValue(chars[0]));

        for (int i = 1; i < chars.length; i++) {
            ListNode nxt = new ListNode(Character.getNumericValue(chars[i]));
            addToTail(listNode, nxt);
        }

        return listNode;

    }

    public static ListNode addToTail(ListNode current, ListNode next) {
        if (current.next == null) {
            current.next = next;
        } else {
            addToTail(current.next, next);
        }
        return current;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
