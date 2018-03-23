package linearStructure.deque;

import linearStructure.position.Position;

public class DoubleLinkedNode implements Position{
    protected Object element;
    protected DoubleLinkedNode prev;
    protected DoubleLinkedNode next;

    public DoubleLinkedNode(){this(null, null, null);}
    public DoubleLinkedNode(Object element){
        this();
        this.element = element;
    }
    public DoubleLinkedNode(Object element, DoubleLinkedNode prev, DoubleLinkedNode next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
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

//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("<" + this.element + " " + this.prev + " " + this.next + ">");
//        return sb.toString();
//    }

    //双链表方法
    public DoubleLinkedNode getNext() {
        return next;
    }

    public DoubleLinkedNode getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkedNode prev) {
        this.prev = prev;
    }

    public void setNext(DoubleLinkedNode next) {
        this.next = next;
    }


    public static void main(String[] args) {
        DoubleLinkedNode d1 = new DoubleLinkedNode(1);
        DoubleLinkedNode d2 = new DoubleLinkedNode(2);
        DoubleLinkedNode d3 = new DoubleLinkedNode(3);
        DoubleLinkedNode head = new DoubleLinkedNode("HEAD");
        DoubleLinkedNode tail = new DoubleLinkedNode("TAIL");
        head.setNext(tail);
        tail.setPrev(head);
        //模拟插入头节点
        d1.next = head.next;
        head.next = d1;
        d1.prev = head;
        d1.next.prev = d1;
        DoubleLinkedNode tmp = head;
        //尾节点前插入
        d2.next = tail;
        d2.prev = tail.prev;
        tail.prev.next = d2;//和下面的顺序不能换
        tail.prev = d2;

        //普通节点插入
        d3.next = d1.next;
        d3.prev = d1;
        d3.prev.next = d3;
        d3.next.prev = d3;

        while (tmp.next != null){
            System.out.println(tmp.toString() + " " + tmp.element);
            tmp = tmp.getNext();
        }
        System.out.println();
        tmp = tail;
        while (tmp.prev != null){
            System.out.println(tmp.toString() + " " + tmp.element);
            tmp = tmp.prev;
        }
    }
}
