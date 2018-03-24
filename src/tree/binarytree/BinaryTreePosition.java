package Tree.binarytree;

import Linear.position.Position;

import java.util.Iterator;

public interface BinaryTreePosition extends Position{
    boolean hasParent();
    BinaryTreePosition getParent();
    void setParent(BinaryTreePosition parent);
    boolean isLeaf();
    boolean isLChild();
    boolean hasLChild();
    BinaryTreePosition getLChild();
    void setLChild(BinaryTreePosition lChild);

    boolean isRChild();
    boolean hasRChild();
    BinaryTreePosition getRChild();
    void setRChild(BinaryTreePosition rChild);

    //获取当前节点的后代数量
    int getSize();
    //更新当前节点及其后代的后代数量
    void updateSize();
    int getHeight();
    //当父亲节点发生变化后，更新当前节点机器后代的高度
    void updateHeight();

    //获取当前节点深度
    int getDepth();
    //更新当前节点机器后代的深度
    void updateDepth();

    //断绝父子关系， 将当前节点返回
    BinaryTreePosition secede();

    //将节点c作为当前节点的左孩子
    BinaryTreePosition attachL(BinaryTreePosition c);
    //将节点c作为当前节点的右孩子
    BinaryTreePosition attachR(BinaryTreePosition c);

    //按照中序遍历的次序查找当前节点的直接前驱
    BinaryTreePosition getPrev();

    //按照中序遍历的次序吵着当前节点的直接后继
    BinaryTreePosition getSucc();
    //前序遍历
    Iterator elementsPreorder();
    //中序遍历
    Iterator elementsInorder();
    //后序遍历
    Iterator elementsPostorder();
    //层次遍历
    Iterator elementsLevelorder();

}
