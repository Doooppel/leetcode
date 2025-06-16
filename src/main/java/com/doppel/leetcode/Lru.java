package com.doppel.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Lru<K, V> {
    // 双向链表节点
    class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node> cache;
    private final Node head; // 虚拟头节点
    private final Node tail; // 虚拟尾节点

    public Lru(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        Node node = cache.get(key);
        // 将访问的节点移动到链表头部
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            // 如果key已存在，更新value并移动到头部
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // 如果key不存在，创建新节点
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            // 如果超过容量，移除尾部节点
            if (cache.size() > capacity) {
                Node tailNode = removeTail();
                cache.remove(tailNode.key);
            }
        }
    }

    // 将节点添加到链表头部
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 从链表中移除节点
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 将节点移动到链表头部
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // 移除链表尾部节点
    private Node removeTail() {
        Node tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }

    // 打印缓存内容（用于测试）
    public void printCache() {
        Node current = head.next;
        System.out.print("LRU Cache: ");
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Lru<Integer, String> cache = new Lru<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.printCache(); // [1=One] [2=Two] [3=Three]

        cache.get(1);
        cache.printCache(); // [2=Two] [3=Three] [1=One]

        cache.put(4, "Four"); // 会移除最久未使用的2
        cache.printCache(); // [3=Three] [1=One] [4=Four]


        LinkedList<Object> objects = new LinkedList<>();
    }
}