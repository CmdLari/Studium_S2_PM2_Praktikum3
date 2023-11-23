package binarytree;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BinaryTree<T extends Comparable<? super T>> implements Iterable<T> {


    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private Node current = root;
            private final Stack<Node> pending = new Stack<>();

            @Override
            public boolean hasNext() {
                return current != null || !pending.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                if (current == null) {
                    current = pending.pop(); //ist hier möglich, da der STack noch mindestens ein Element enthält
                }
                if (current.right != null) pending.push(current.right);
                T toReturn = current.content;
                current = current.left;
                return toReturn;
            }
        };
    }

    private class Node {
        private final T content;
        private Node left, right;

        private Node(T content) {
            this.content = content;
        }

        private boolean insert(T content) {
            int compare = this.content.compareTo(content);
            if (compare == 0) return false;
            else if (compare > 0) { // insert left
                if (left == null) {
                    left = new Node(content);
                    return true;
                } else
                    return left.insert(content);
            } else { // insert right
                if (right == null) {
                    right = new Node(content);
                    return true;
                } else
                    return right.insert(content);
            }
        }

        @Override
        public String toString() {
            return String.format("%s->[%s%s]", content, left == null ? "_" : left, right == null ? "_" : right);
        }
    }

    public <R> List<R> process(Function<T,R> function){
        List<R> resultList = new ArrayList<>();

        for (T leaf:this) {
            resultList.add(function.apply(leaf));
        };

        return resultList;
    }

    public <R> List<R> process(Function<T, R> function, Predicate<R> predicate){
        List<R> resultList = new ArrayList<>();

        for (T leaf:this) {
            R noob = function.apply(leaf);
            if(predicate.test(noob)){
                resultList.add(noob);
        }
        }

        return resultList;
    }

    public <R> void process(Function<T, R> function, Predicate<R> predicate, Consumer<R> consumer){

        for (T leaf: this){
            R noob = function.apply(leaf);
            if(predicate.test(function.apply(leaf))){
                consumer.accept(noob);
            }
        };
    }

    private final Node root;

    public BinaryTree(T content) {
        this.root = new Node(content);
    }

    public boolean insert(T content) {
        return root.insert(content);
    }

    @Override
    public String toString() {
        return root.toString();
    }

}
