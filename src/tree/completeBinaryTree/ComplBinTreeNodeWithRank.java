package Tree.completeBinaryTree;

import Linear.vector.Vector;
import Tree.binarytree.BTNode;
import Tree.binarytree.BinaryTreePosition;

public class ComplBinTreeNodeWithRank extends BTNode implements BinaryTreePosition {
    private Vector T;//所属的树
    private int rank;
    private Object element;

    public ComplBinTreeNodeWithRank(Vector t, Object element) {
        T = t;
        this.element = element;
        rank = T.getSize();
        T.insertAtRank(rank, this);
    }

    public Object getElement() {
        return element;
    }

    public Object setElement(Object element) {
        Object old = this.element;
        this.element = element;
        return old;
    }

    public boolean hasParent(){
        return rank != 0;
    }

    public BinaryTreePosition getParent(){
        if (! hasParent())
            return null;
        else
            return (BinaryTreePosition) T.getAtRank(rank/2);
    }
    public boolean hasLChild(){
        return rank*2+1 < T.getSize();
    }

    public BinaryTreePosition getLChild(){
        return hasLChild()?(BinaryTreePosition) T.getAtRank(rank*2 + 1): null;
    }

    public boolean hasRChild(){
        return rank*2+2 < T.getSize();
    }

    public BinaryTreePosition getRChild(){
        return hasRChild()?(BinaryTreePosition) T.getAtRank(rank*2+2) : null;
    }

    public int getSize(){
        int size = 1;
        if (hasLChild()) size += getLChild().getSize();
        if (hasRChild()) size += getRChild().getSize();
        return size;
    }

    public int getHeight(){
        int hL = hasLChild() ? getLChild().getHeight() : -1;
        int hR = hasRChild() ? getRChild().getHeight() : -1;
        return 1 + Math.max(hL, hR);
    }

    public int getDepth(){
        return hasParent()? 1 + getParent().getDepth() : 0;
    }
}
