package Tree.binarytree.BinarySearchTree;

import PriorityQueue.entry.Entry;
import Tree.binarytree.BTNode;
import Tree.binarytree.BinaryTreePosition;

public class BSTreeNode extends BTNode implements BinaryTreePosition, Entry{
    public BSTreeNode() {
        super();
    }

    public BSTreeNode(
            Object e, BinaryTreePosition p,
            boolean asLChild, BinaryTreePosition l,
            BinaryTreePosition r) {
        super(e, p, asLChild, l, r);
    }

//--------------------实现Entry接口方法--------------------
    @Override
    public Object getKey() {
        return ((Entry)getElem()).getKey();
    }

    @Override
    public Object setKey(Object k) {
        return ((Entry)getElem()).setKey(k);
    }

    @Override
    public Object getValue() {
        return ((Entry)getElem()).getValue();
    }

    @Override
    public Object setValue(Object v) {
        return ((Entry)getElem()).setValue(v);
    }
}
