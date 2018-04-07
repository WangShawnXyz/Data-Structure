package Tree.tree;

public class TreeLinkedList implements Tree{
    private Object element;
    private TreeLinkedList parent, firstChild, nextSibling;

    public TreeLinkedList() {
        this(null, null, null, null);
    }

    public TreeLinkedList(Object element, TreeLinkedList parent,
                          TreeLinkedList firstChild, TreeLinkedList nextSibling) {
        this.element = element;
        this.parent = parent;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    @Override
    public Object getElem() {
        return this.element;
    }

    @Override
    public Object setElem(Object object) {
        Object old = element;
        element = object;
        return old;
    }

    @Override
    public TreeLinkedList getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList getNextSibling() {
        return nextSibling;
    }

    @Override
    public int getSize() {
        int size = 1;
        TreeLinkedList subTree = firstChild;
        while (subTree != null){
            size += subTree.getSize();
            subTree = subTree.getNextSibling();
        }
        return size;
    }

    @Override
    public int getHeight() {
        int height = -1;
        TreeLinkedList subTree = firstChild;
        while (subTree != null){
            height = Math.max(height, subTree.getHeight());
        }
        return height + 1;//当前节点也算高度的。
    }

    @Override
    public int getDepth() {
        int depth = 0;
        TreeLinkedList p = getParent();
        while (p != null){
            depth ++;
            p = p.getParent();
        }
        return depth;
    }


    public static void main(String[] args) {

    }
}
