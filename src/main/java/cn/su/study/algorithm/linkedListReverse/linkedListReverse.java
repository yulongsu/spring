package cn.su.study.algorithm.linkedListReverse;

import cn.su.study.algorithm.component.Node;

/**
 * @author suyulong
 * @date 2018/5/20 13:10
 */
public class linkedListReverse {
    public static void main(String[] args) {
        //init linked list
        Node head = new Node(0);

        Node node1 = new Node(1);
        head.setNext(node1);

        Node node2 = new Node(2);
        node1.setNext(node2);

        Node node3 = new Node(3);
        node2.setNext(node3);

        //origin
        print(head);

        //reverse
        Node newHead = reverse(head);

        //after reverse
        print(newHead);

        Node reHead = reverse_recurse(newHead);

        print(reHead);
    }

    private static Node reverse(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }

        Node cur = head;
        Node next = head.getNext();
        Node tmp;

        while(next != null){
            tmp = next.getNext();
            next.setNext(cur);

            cur = next;
            next = tmp;
        }
        head.setNext(null);
        return cur;
    }

    private static Node reverse_recurse(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }

        Node reHead = reverse_recurse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reHead;
    }

    private static void print(Node head){
        while(head != null){
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
        System.out.print('\n');
    }
}
