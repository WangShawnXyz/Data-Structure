package tree.binarytree;

import java.util.*;

public class BTNode implements BinaryTreePosition {
    protected Object element;
    protected BinaryTreePosition parent;
    protected BinaryTreePosition lChild;
    protected BinaryTreePosition rChild;
    protected int size;
    protected int height;
    protected int depth;

    public BTNode() {
        this(null, null, true, null, null);
    }

    public BTNode(Object e, BinaryTreePosition p,
                  boolean asLChild, BinaryTreePosition l, BinaryTreePosition r){
        size = 1;
        height = depth = 0;
        parent = lChild = rChild = null;
        element = e;

        if (parent == null){
            if (asLChild) p.attachL(this);
            else p.attachR(this);
        }
        if (l != null) this.attachL(l);
        if (r != null) this.attachR(r);
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public BinaryTreePosition getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreePosition parent) {
        BinaryTreePosition old = this.parent;
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    @Override
    public boolean isLChild() {
        return hasParent() && parent.getLChild() == this;
    }

    @Override
    public boolean hasLChild() {
        return lChild != null;
    }

    @Override
    public BinaryTreePosition getLChild() {
        return this.lChild;
    }

    @Override
    public void setLChild(BinaryTreePosition lChild) {
        this.lChild = lChild;
    }

    @Override
    public boolean isRChild() {
        return hasParent() && parent.getRChild() == this;
    }

    @Override
    public boolean hasRChild() {
        return rChild != null;
    }

    @Override
    public BinaryTreePosition getRChild() {
        return this.rChild;
    }

    @Override
    public void setRChild(BinaryTreePosition rChild) {
        this.rChild = rChild;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void updateSize() {
        int size = 1;
        if (hasLChild()) size += getLChild().getSize();
        if (hasRChild()) size += getRChild().getSize();

        //逐级更新上级节点的规模
        if (hasParent()) parent.updateSize();
    }


    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0;
        if (hasLChild()) height = Math.max(height, 1 + lChild.getHeight());
        if (hasRChild()) height = Math.max(height, 1 + rChild.getHeight());

        if (hasParent()) getParent().updateHeight();
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = hasParent()? 1 + getParent().getDepth() : 0;
        if (hasLChild()) lChild.updateDepth();
        if (hasRChild()) rChild.updateDepth();
    }

    @Override
    public BinaryTreePosition getPrev() {
        //如果有左孩子， 当前节点的前驱就是最左边的孩子
        if (hasLChild()) return findMaxDescendant(this);
        //如果当前节点是右孩子， 那么当前接的前驱就是父亲节点
        if (isRChild()) return getParent();
        //走到这里就是左孩子而且没有左孩子， 那么他的前驱节点就是左链的父亲节点
        BinaryTreePosition v = this;
        while(v.isLChild()){
            v = v.getParent();  //沿着左链一直上升
        }
        //至此v没有父亲节点或者是父节点的左孩子，前驱节点就是v的父亲
        return v.getParent();
    }

    @Override
    public BinaryTreePosition getSucc() {
        //若右子树非空，那么直接后继就是右子树的最左节点
        if (hasRChild()) return findMaxDescendant(this);
        //至此，当前节点没有右子树
        if (isLChild()) return getParent(); //如果当前节点是左孩子， 那么直接后继就是父节点
        //至此， 当前节点右子树为空并且不是左孩子
        BinaryTreePosition v = this;
        while (v.isRChild()){
            v = v.getParent(); //沿着右子链一直上升
        }
        //至此v没有父亲，或者是父亲的左孩子
        return v.getParent();
    }

    @Override
    public BinaryTreePosition secede() {
        if (this.getParent() != null){
            //先切断父亲节点到当前节点的引用
            if (isLChild()) parent.setLChild(null);
            else parent.setRChild(null);

            parent.updateSize();//更新当前节点及其祖先节点的规模
            parent.updateHeight();//更新当前节点及其祖先节点的高度
            parent = null;//切断当前节点到父亲节点的引用
            updateDepth();//更新当前节点以及孩子节点的深度
        }
        return this; //返回当前节点
    }

    @Override
    public BinaryTreePosition attachL(BinaryTreePosition c) {
        if (hasLChild()) getLChild().secede();//摘除之前的左孩子节点

        if (c != null){
            c.secede(); //先摆脱原来的父亲
            //作为左节点
            this.setLChild(c);
            c.setParent(this);

            updateSize();//更新当前节点及其父节点的规模
            updateHeight();//更新当前节点及其父节点的高度

            c.updateDepth();//更新当前节点及其孩子的深度
        }
        return this;
    }

    @Override
    public BinaryTreePosition attachR(BinaryTreePosition c) {
        if (hasRChild()) getRChild().secede();//摘除之前的左孩子节点

        if (c != null){
            c.secede(); //先摆脱原来的父亲
            //作为左节点
            this.setRChild(c);
            c.setParent(this);

            updateSize();//更新当前节点及其父节点的规模
            updateHeight();//更新当前节点及其父节点的高度

            c.updateDepth();//更新当前节点及其孩子的深度
        }
        return this;
    }

    @Override
    public Iterator elementsPreorder() {
        List list = new ArrayList();
        preorder(list, this);
        return list.iterator();
    }

    @Override
    public Iterator elementsInorder() {
        List list = new ArrayList();
        inorder(list, this);
        return list.iterator();
    }

    @Override
    public Iterator elementsPostorder() {
        List list = new ArrayList();
        postorder(list, this);
        return list.iterator();
    }

    @Override
    public Iterator elementsLevelorder() {
        List list = new ArrayList();
        levelorder(list, this);
        return list.iterator();
    }

    @Override
    public Object getElem() {
        return this.element;
    }

    @Override
    public Object setElem(Object e) {
        Object old = this.element;
        this.element = e;
        return old;
    }


    /**************** 辅助方法 *************/
    //在v的后代中找到最小的节点
    protected static BinaryTreePosition findMinDescendant(BinaryTreePosition v){
        if (v != null){
            while (v.hasLChild()){
                v = v.getLChild();
            }
        }
        return v;
    }
    //在v的后代中找到最大的节点
    protected static BinaryTreePosition findMaxDescendant(BinaryTreePosition v){
        if (v != null){
            while (v.hasRChild()){
                v = v.getRChild();
            }
        }
        return v;
    }
    protected static void preorder(List list, BinaryTreePosition v){
        if (v == null) return;
        list.add(v);
        if (v.hasLChild()) preorder(list, v.getLChild());
        if (v.hasRChild()) preorder(list, v.getRChild());
    }
    protected static void inorder(List list, BinaryTreePosition v){
        if (v == null) return;
        if (v.hasLChild()) inorder(list, v.getLChild());
        list.add(v);
        if (v.hasRChild()) inorder(list, v.getRChild());
    }

    protected static void postorder(List list, BinaryTreePosition v){
        if (v == null) return;
        if (v.hasLChild()) postorder(list, v.getLChild());
        if (v.hasRChild()) postorder(list, v.getRChild());
        list.add(v);
    }

    protected static void levelorder(List list, BinaryTreePosition v){
        Queue q = new ArrayDeque();
        q.offer(v);
        while(!q.isEmpty()){
            BinaryTreePosition u = (BinaryTreePosition) q.poll();
            list.add(u);
            if (u.hasLChild()) levelorder(list, u.getLChild());
            if (u.hasRChild()) levelorder(list, u.getRChild());
        }
    }

}
