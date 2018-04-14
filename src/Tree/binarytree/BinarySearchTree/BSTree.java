package Tree.binarytree.BinarySearchTree;

import Map.Dictionary.Dictionary;
import PriorityQueue.comparator.Comparator;
import PriorityQueue.comparator.ComparatorDefault;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;
import Tree.binarytree.BTreeLinkedList;
import Tree.binarytree.BinaryTreePosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BSTree extends BTreeLinkedList implements Dictionary{
//---------------------实例变量--------------------
    protected Comparator C;
    protected BinaryTreePosition lastV; //最后操作过的节点， 以便AVL树/伸展树重平衡
//---------------------构造方法---------------------

    public BSTree(BinaryTreePosition root) {
        this(root, new ComparatorDefault());
    }

    public BSTree() {
        this(null, new ComparatorDefault());
    }

    public BSTree(BinaryTreePosition r, Comparator c){
        C = c;
        root = r;
    }
//----------------------实现词典方法----------------------------
    @Override
    public Entry find(Object key) {
        if (isEmpty()) return null;
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        return 0 == C.compare(key, u.getKey())? (Entry)u.getElem() : null;
    }

    @Override
    public Iterator findAll(Object key) {
        List s = new ArrayList();
        if (isEmpty()) return s.iterator();
        findAllNodes((BSTreeNode) root, key, s, C);
        return s.iterator();
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);
        if (isEmpty()){ //原树为空的情况
            root = new BSTreeNode(e, null, true, null, null);
            lastV = root;
        }else { //一般情况
            BSTreeNode u = (BSTreeNode) root;
            boolean asLeftChild = false;
            while (true){//不停地
                u = binSearch(u, key, C);//查找关键码为key的节点，直到
                if (C.compare(key, u.getKey()) < 0){//查找失败于左子树
                    asLeftChild = true;
                    break;
                }else if (C.compare(key, u.getKey()) > 0){//查找失败于右子树
                    asLeftChild = false;
                    break;
                }else if (!u.hasLChild()){  //查找成功可以作为左孩子插入
                    asLeftChild = true;
                    break;
                }else if (!u.hasRChild()){  //查找成功可以作为右孩子插入
                    asLeftChild = false;
                    break;
                }else{
                    u = (BSTreeNode) u.getLChild();
                }
            }
            lastV = new BSTreeNode(e, u, asLeftChild, null, null);
        }
        return e;
    }

    @Override
    public Entry remove(Object key) {
        if (isEmpty()) return null;
        BinaryTreePosition v = binSearch((BSTreeNode) root, key, C);
        if (0 != C.compare(key,((BSTreeNode)v).getKey())){    //查找失败
            return null;
        }
        //现在是查找成功的情况
        if (v.hasLChild()){//左子树非空的情况
            BinaryTreePosition w = v.getPrev(); //找到v的左子树中找v的直接前驱
            w.setElem(v.setElem(w.getElem()));//交换w和v的值
            v = w; //相当于删除了w
        }
        //现在v最多只有一个孩子
        //删除v，用其孩子取代他
        lastV = v.getParent();
        BinaryTreePosition u = v.hasLChild()? v.getLChild():v.getRChild();
        if (null == lastV){//v恰好是树根
            if (u != null){
                u.secede(); root = u;
            }
        }else {
            if (v.isLChild()){//如果v是左孩子
                //将v拿出来， 将u作为p的左孩子
                v.secede();
                lastV.attachL(u);
            }else{
                //将v拿出来， 将u作为p的右孩子
                v.secede();
                lastV.attachR(u);
            }
        }
        return (Entry)v.getElem();
    }

    @Override
    public Iterator entries() {
        List list = new LinkedList();
        concatenate(list, (BSTreeNode) root);
        return list.iterator();
    }

//------------------------辅助方法--------------------------
    //在树中进行二分查找, 如果找到就返回找到的节点，
    //否则返回最后一个访问的节点， 上层方法要再确认一次关键码
    protected static BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c){
        BSTreeNode u = v;
        while (true){
            int comp = c.compare(key, u.getKey());
            if (comp < 0){//左子树中找
                if (u.hasLChild()){
                    u = (BSTreeNode) u.getLChild();
                }else {
                    return u;//终止于没有左孩子的节点
                }
            }else if (comp > 0){//右子树中找
                if (u.hasRChild()){
                    u = (BSTreeNode) u.getRChild();
                }else{
                    return u;//终止于没有右孩子的节点
                }
            }else{//命中
                return u;
            }
        }
    }

    //在以v为根节点的树中， 递归查找出所有以key为关键码的所有节点//List形式
    protected static void findAllNodes(BSTreeNode v, Object k, List s, Comparator c){
        if (v == null) return; //空树， 直接返回
        int comp = c.compare(k, v.getKey());
        if (0 >= comp) findAllNodes((BSTreeNode) v.getLChild(), k, s, c);//递归查找左子树
        if (0 == comp) s.add(v); //命中
        if (0 <= comp) findAllNodes((BSTreeNode) v.getRChild(), k, s, c);//递归查找右子树
    }

    //将v的所有后代节点中存放的Entry放到一个List中
    protected static void concatenate(List list, BSTreeNode v){
        if (v == null) return;
        list.add(v);
        if (v.hasLChild())
            concatenate(list, (BSTreeNode) v.getLChild());
        if (v.hasRChild())
            concatenate(list, (BSTreeNode) v.getRChild());
    }
}
