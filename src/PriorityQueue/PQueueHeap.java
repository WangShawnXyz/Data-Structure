package PriorityQueue;

import Linear.list.List;
import PriorityQueue.comparator.Comparator;
import PriorityQueue.comparator.ComparatorDefault;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;
import Tree.binarytree.BinaryTree;
import Tree.binarytree.BinaryTreePosition;
import Tree.completeBinaryTree.ComplBinTreeVector;
import Tree.completeBinaryTree.CompleteBinaryTree;

public class PQueueHeap implements PQueue {
    private CompleteBinaryTree H;//完全二叉树形式的堆
    private Comparator comp;//比较器
    public PQueueHeap(List list){
        this(list, new ComparatorDefault());
    }
    public PQueueHeap(Comparator comp){
        this(null, comp);
    }
    public PQueueHeap(List list, Comparator comp) {
        H = new ComplBinTreeVector();
        this.comp = comp;
        if (!H.isEmpty()){
            for (int i = H.getSize()/2-1; i >= 0; i--)//自下而上
                percolateDown(H.posOfNode(i));//逐个节点进行下滤
        }
    }

    @Override
    public int getSize() {
        return H.getSize();
    }

    @Override
    public boolean isEmpty() {
        return H.isEmpty();
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        //若Q非空，则返回其中的最小条目（并不删除） ;否则，报错
        if (isEmpty()) throw new ExceptionPQueueEmpty("");
        return (Entry) H.getRoot().getElem();
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        //将对象obj与关键码k合成一个条目，将其插入Q中，并返回该条目
        checkKey(key);
        Entry e = new EntryDefault(key, value);
        percolateDown(H.addLast(e));
        return e;
    }

    private void checkKey(Object key) throws ExceptionKeyInvalid{
        try {
            comp.compare(key, key);
        }catch (ExceptionKeyInvalid e){
            throw new ExceptionKeyInvalid("");
        }
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        //若Q非空，则从其中摘除关键码最小的条目，并返回该条目；否则，报错
        if (isEmpty()) throw new ExceptionPQueueEmpty("");
        Entry min = (Entry) H.getRoot().getElem();
        if (getSize() == 1)
            H.delLast();
        else {
            H.getRoot().setElem(((BinaryTreePosition)H.delLast()).getElem());
            percolateDown(H.getRoot());
        }
        return min;
    }


    //算法方法
    protected void percolateUp(BinaryTreePosition v) {
        BinaryTreePosition root = H.getRoot();
        while (v != root){
            BinaryTreePosition p = v.getParent();
            if (comp.compare(key(p), key(v)) > 0){
                swapParentChild(p, v);
                v = p;
            }
        }
    }
    protected void percolateDown(BinaryTreePosition v) {
        while (!v.isLeaf()){
            BinaryTreePosition smaller = comp.compare(key(v.getLChild()), key(v.getRChild()))>0? v.getLChild():v.getRChild();
            if (comp.compare(key(v), key(smaller)) > 0) break;
            else{
                swapParentChild(v, smaller);
                v = smaller;
            }
        }
    }
    protected void swapParentChild(BinaryTreePosition p, BinaryTreePosition v) {
        Object tmp = p.getElem();
        p.setElem(v.getElem());
        v.setElem(tmp);
    }

    private Object key(BinaryTreePosition p) {
        return ((Entry)p.getElem()).getKey();
    }
}
