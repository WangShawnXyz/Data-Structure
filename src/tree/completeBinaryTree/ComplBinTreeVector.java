package tree.completeBinaryTree;

import linearStructure.vector.Vector;
import linearStructure.vector.VectorExtendableArray;
import tree.binarytree.BTreeLinkedList;
import tree.binarytree.BinaryTreePosition;

public class ComplBinTreeVector extends BTreeLinkedList implements CompleteBinaryTree {
    private Vector T;
    public ComplBinTreeVector() {
        T = new VectorExtendableArray();
        root = null;
    }
    public ComplBinTreeVector(Vector vector) {
        this();
        if (vector != null)
            while (!vector.isEmpty()){
                addLast(vector.removeAtRank(0));
            }
    }

    @Override
    public BinaryTreePosition getRoot() {
        return T.isEmpty()? null : posOfNode(0);
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty();
    }

    @Override
    public int getSize() {
        return T.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty()? -1:getRoot().getHeight();
    }

    @Override
    public BinaryTreePosition addLast(Object e) {
        BinaryTreePosition node = new ComplBinTreeNodeWithRank(T, e);
        root = (BinaryTreePosition) T.getAtRank(0);
        return node;
    }

    @Override
    public Object delLast() {
        if (isEmpty())return null;
        BinaryTreePosition old = (BinaryTreePosition) T.getAtRank(T.getSize()-1);
        return old;
    }

    @Override
    public BinaryTreePosition posOfNode(int i) {
        return (BinaryTreePosition) T.getAtRank(i);
    }
}
