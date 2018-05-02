package Tree.binarytree.AVLTree;

import Map.Dictionary.Dictionary;
import PriorityQueue.comparator.Comparator;
import PriorityQueue.entry.Entry;
import Tree.binarytree.BinarySearchTree.BSTree;
import Tree.binarytree.BinaryTreePosition;

public class AVLTree extends BSTree implements Dictionary {
    public AVLTree(){super();}
    public AVLTree(BinaryTreePosition r){super(r);}
    public AVLTree(BinaryTreePosition p, Comparator c){super(p, c);}
    @Override
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);
        root = rebalance(lastV.getParent(), root);  //从被插入节点的父亲开始重平衡AVL树
        return e;
    }

    @Override
    public Entry remove(Object key) {
        Entry e = super.remove(key);
        if (e != null) root = rebalance(lastV.getParent(), root);  //从被删除节点的父亲开始重平衡AVL树
        return e;
    }

    /**
     *
     * @param z
     * @param r
     * @return  被重新平衡后的树根节点
     *
     * 从z节点开始，从上而下平衡二叉树
     */

    protected static BinaryTreePosition rebalance(BinaryTreePosition z, BinaryTreePosition r) {
        if (z == null) return r;
        while (true){   //从z开始逐级向上查找z的祖先
            if (!isBalanced(z)) rotate(z);  //如果z已经失衡了， 先旋转调整z
            if (!z.hasParent()) return z;   //找到根节点或者z本身就是根节点直接返回
            z = z.getParent();
        }   //while
    }

    protected static boolean isBalanced(BinaryTreePosition z) {
        if (z == null) return true;
        int lH = (z.hasLChild()) ? z.getLChild().getHeight() : -1;
        int rH = (z.hasRChild()) ? z.getRChild().getHeight() : -1;
        int delta = lH - rH;
        return (-1 <= delta) && (delta <= 1);
    }

    protected static BinaryTreePosition rotate(BinaryTreePosition z) {
        // x, y, z 爷父孙三个节点
        BinaryTreePosition y = tallerChild(z);
        BinaryTreePosition x = tallerChild(y);
        boolean childType = z.isLChild();   //记录z是否是左孩子
        BinaryTreePosition p = z.getParent();
        BinaryTreePosition a, b, c; //从左到右三个节点
        BinaryTreePosition t0, t1, t2, t3;  //从左到右四棵子树
        /**
         * 以下分为四种情况讨论
         */
        if (y.isLChild()){  //y为左孩子
            c = z;
            t3 = z.getRChild();
            if (x.isLChild()){  //x也是左孩子
                b = y; t2 = y.getRChild();
                a = x; t1 = x.getRChild();
                t0 = x.getLChild();
            } else {  //x为右孩子的情况
                a = y; t0 = y.getLChild();
                b = x; t1 = x.getLChild();
                t2 = x.getLChild();
            }
        } else {    //y是右孩子
            a = z; t0 = z.getLChild();
            if (x.isLChild()){  //x是左孩子
                b = y; t1 = y.getLChild();
                c = x; t2 = x.getLChild();
                t3 = x.getRChild();
            } else {    //x是右孩子
                c = y; t3 = y.getRChild();
                b = x; t1 = x.getLChild();
                t2 = z.getRChild();
            }
        }
        //取下三个节点
        z.secede();
        y.secede();
        x.secede();

        //摘下四棵子树
        t0.secede();
        t1.secede();
        t2.secede();
        t3.secede();

        //重新连接
        a.attachL(t0); a.attachR(t1); b.attachL(a);
        c.attachL(t2); c.attachR(t3); b.attachR(c);

        //子树重新加入原树
        if (p != null){
            if (childType) p.attachL(b);
            else p.attachR(b);
        }

        return b;   //返回新的子树根
    }

    protected static BinaryTreePosition tallerChild(BinaryTreePosition z) {
        int lH = (z.hasLChild()) ? z.getLChild().getHeight() : -1;
        int rH = (z.hasRChild()) ? z.getRChild().getHeight() : -1;

        if (lH > rH) return z.getLChild();
        if (lH < rH) return z.getRChild();

        if (z.isLChild()) return z.getLChild();
        else return z.getRChild();
    }

    protected static BinaryTreePosition shorterChild(BinaryTreePosition z) {
        int lH = (z.hasLChild()) ? z.getLChild().getHeight() : -1;
        int rH = (z.hasRChild()) ? z.getRChild().getHeight() : -1;

        if (lH > rH) return z.getRChild();
        if (lH < rH) return z.getLChild();

        if (z.isLChild()) return z.getRChild();
        else return z.getLChild();
    }

}
