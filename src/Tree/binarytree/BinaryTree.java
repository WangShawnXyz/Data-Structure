package Tree.binarytree;

import java.util.Iterator;

public interface BinaryTree {
    BinaryTreePosition getRoot();
    boolean isEmpty();
    int getSize();
    int getHeight();

    Iterator elementsPreorder();
    Iterator elementsInorder();
    Iterator elementsPostorder();
}
