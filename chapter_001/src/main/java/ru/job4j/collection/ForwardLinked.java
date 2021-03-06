package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }
    public void revert() {
        Node<T> current = null;
        while (head != null) {
            Node<T> previous = head.next;
            head.next = current;
            current = head;
            head = previous;
        }
        head = current;
    }

    public T deleteFirst() {
        T res = null;
        if (head != null) {
            Node<T> tail = head;
            res = tail.value;
            head = tail.next;
            tail.next = null;
        } else {
            throw new NoSuchElementException();
        }
        return res;
    }
    private T findLastElementAndDelete() {
        Node<T> tail = head;
            while (tail.next.next != null) {
                tail = tail.next;
            }
            T res = tail.next.value;
            tail.next = null;
            return res;
    }
    public T deleteLast() {
        T res = null;
        if (head != null) {
            if (head.next != null) {
                res = findLastElementAndDelete();
            } else {
                res = deleteFirst();
            }
        } else {
            throw new NoSuchElementException();
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

