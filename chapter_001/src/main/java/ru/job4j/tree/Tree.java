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
        Optional<Node<E>> parentOptional = findBy(parent);
        Optional<Node<E>> childOptional = findBy(child);
        if (parentOptional.isPresent() && !childOptional.isPresent()) {
            Node<E> parentNode = parentOptional.get();
                parentNode.children.add(new Node<>(child));
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

    public boolean isBinary() {
        boolean res = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            if (node.children.size() > 2) {
                res = false;
                break;
            }
            data.addAll(node.children);
        }
        return res;
    }
}
