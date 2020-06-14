package ru.job4j.collection;

public class Node<T> {
    T item;
    Node<T> next;
    Node<T> prev;
    public Node(Node<T> prev,  T item, Node<T> next) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}
