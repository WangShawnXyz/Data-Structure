package tree.completeBinaryTree;

import tree.binarytree.BinaryTree;
import tree.binarytree.BinaryTreePosition;

public interface CompleteBinaryTree extends BinaryTree{
    BinaryTreePosition addLast(Object e);
    Object delLast();

    //按照层次遍历的顺序返回编号为i的节点， 0 <= i <= size()
    BinaryTreePosition posOfNode(int i);
}
