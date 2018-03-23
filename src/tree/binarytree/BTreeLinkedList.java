package tree.binarytree;

import java.util.Iterator;

public class BTreeLinkedList implements BinaryTree {
    protected BinaryTreePosition root;

    public BTreeLinkedList(){
        this(null);
    }

    public BTreeLinkedList(BinaryTreePosition root) {
        this.root = root;
    }

    @Override
    public BinaryTreePosition getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return getRoot() == null;
    }

    @Override
    public int getSize() {
        return isEmpty()? 0 : root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty()? -1 : root.getHeight();
    }

    @Override
    public Iterator elementsPreorder() {
        return isEmpty()? null : getRoot().elementsPreorder();
    }

    @Override
    public Iterator elementsInorder() {
        return isEmpty()? null : getRoot().elementsInorder();
    }

    @Override
    public Iterator elementsPostorder() {
        return isEmpty()? null : getRoot().elementsPostorder();
    }
}
