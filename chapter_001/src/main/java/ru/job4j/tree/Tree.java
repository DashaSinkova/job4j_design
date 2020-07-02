package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;
    public Tree(final E root) {
        this.root = new Node<>(root);
    }
    @Override
    public boolean add(E parent, E child) {
        boolean res = false;
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent()) {
            Node<E> node = parentNode.get();
            if (!node.children.contains(child)) {
                node.children.add(new Node<>(child));
                res = true;
            }
        } else {
            Node<E> eNode = new Node<>(parent);
            eNode.children.add(new Node<>(child));
            root.children.add(eNode);
            res = true;
        }
        return res;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> res =  Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
           Node<E> el = data.poll();
           if (Objects.equals(value, el.value)) {
               res = Optional.of(el);
               break;
           }
           data.addAll(el.children);
        }
        return res;
    }
}