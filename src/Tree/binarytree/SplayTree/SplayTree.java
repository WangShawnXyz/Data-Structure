package Tree.binarytree.SplayTree;

import Map.Dictionary.Dictionary;
import PriorityQueue.comparator.Comparator;
import Tree.binarytree.BinarySearchTree.BSTree;
import Tree.binarytree.BinaryTreePosition;

/**
 *  单层伸展的问题是容易形成单侧链， 最差情况复杂度是O（n）
 *  最合理的实现是双层伸展。爷父孙三个节点再平衡。
 *  双层伸展的结果就是树的高度都是单层伸展的一半。分摊复杂度大概是O（logN）
 *  四种旋转方式：
 *  zig-zig/zag-zag     双右旋/双左旋
 *  zig-zag/zag-zig     左右旋/右左旋
 */
public class SplayTree extends BSTree implements Dictionary {
    public SplayTree() { super(); }
    public SplayTree(BinaryTreePosition root) {
        super(root);
    }
    public SplayTree(BinaryTreePosition r, Comparator c) {
        super(r, c);
    }

    /**
     * 伸展树的辅助方法
     */
    /**
     * 从根节点到z， 层层重平衡
     * @param z 终点
     * @return 重平衡后的子树根节点
     */
    protected static BinaryTreePosition moveToRoot(BinaryTreePosition z){
        while (z.hasParent()) {
            if(!z.getParent().hasParent()){
                if (z.isLChild()) z = zig(z);
                else z = zag(z);
            } else {
                if (z.isLChild()){
                    if (z.getParent().isLChild()) z = zigzig(z);
                    else z = zigzag(z);
                } else {
                    if (z.getParent().isLChild()) z = zagzig(z);
                    else z = zagzag(z);
                }
            }

        }
        return z;
    }

    /**
     * z是左孩子 右旋转，是z上升一层
     * @param z
     * @return 新的子树树根
     */
    protected static BinaryTreePosition zig(BinaryTreePosition z){
        if (z != null && z.isLChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            boolean asLChild = p.isLChild();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c = z.getRChild();   //找到z的右孩子

            if (c != null) p.attachL(c.secede());  //将c作为p的左孩子
            p.secede(); //将p摘出来
            z.attachR(p);   //将p左为z的右孩子
            if (g != null) {
                if (asLChild) g.attachL(z);
                else g.attachR(z);
            }
        }
        return z;
    }

    protected static BinaryTreePosition zag(BinaryTreePosition z){
        if (z != null && z.isRChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            boolean asLChild = p.isLChild();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c = z.getLChild();   //找到z的zuo孩子

            if (c != null) p.attachR(c.secede());  //将c作为p的you孩子
            p.secede(); //将p摘出来
            z.attachL(p);   //将p左为z的zuo孩子
            if (g != null) {
                if (asLChild) g.attachL(z);
                else g.attachR(z);
            }
        }
        return z;
    }

    protected static BinaryTreePosition zigzig(BinaryTreePosition z){
        if (z != null && z.isLChild() && z.getParent().isLChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            BinaryTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c;   //temp var
            c = p.getRChild();
            if (c != null) g.attachL(c.secede());  //将c作为g的左孩子
            c = z.getRChild();
            if (c != null) p.attachL(c.secede());
            p.attachR(g);
            z.attachR(p);
            if (s != null) {
                if (asLChild) s.attachL(z);
                else s.attachR(z);
            }
        }
        return z;
    }

    protected static BinaryTreePosition zagzag(BinaryTreePosition z){
        if (z != null && z.isLChild() && z.getParent().isRChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            BinaryTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c;   //temp var
            c = p.getLChild();
            if (c != null) g.attachR(c.secede());  //将c作为g的左孩子
            c = z.getRChild();
            if (c != null) p.attachR(c.secede());
            p.attachR(g);
            z.attachR(p);
            if (s != null) {
                if (asLChild) s.attachL(z);
                else s.attachR(z);
            }
        }
        return z;
    }

    protected static BinaryTreePosition zigzag(BinaryTreePosition z){
        if (z != null && z.isLChild() && z.getParent().isLChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            BinaryTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c;   //temp var
            c = p.getRChild();
            if (c != null) g.attachR(c.secede());  //将c作为g的左孩子
            c = z.getRChild();
            if (c != null) p.attachL(c.secede());
            p.attachR(g);
            z.attachR(p);
            if (s != null) {
                if (asLChild) s.attachL(z);
                else s.attachR(z);
            }
        }
        return z;
    }

    protected static BinaryTreePosition zagzig(BinaryTreePosition z){
        if (z != null && z.isLChild() && z.getParent().isLChild()) {
            BinaryTreePosition p = z.getParent();   //父亲
            BinaryTreePosition g = p.getParent();   //祖父
            BinaryTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            z.secede(); //摘掉z以后 p的左孩子为空了
            BinaryTreePosition c;   //temp var
            c = p.getRChild();
            if (c != null) g.attachL(c.secede());  //将c作为g的左孩子
            c = z.getRChild();
            if (c != null) p.attachR(c.secede());
            p.attachR(g);
            z.attachR(p);
            if (s != null) {
                if (asLChild) s.attachL(z);
                else s.attachR(z);
            }
        }
        return z;
    }

}
