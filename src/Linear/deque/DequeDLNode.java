package Linear.deque;

import Linear.queue.ExceptionQueueEmpty;

public class DequeDLNode implements Deque{
    protected int size;
    protected DoubleLinkedNode header;
    protected DoubleLinkedNode tailer;

    public DequeDLNode() {
        header = new DoubleLinkedNode("HEADER");
        tailer = new DoubleLinkedNode("TAILER");
        header.setNext(tailer);
        tailer.setPrev(header);
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    @Override
    public Object first() throws ExceptionQueueEmpty {
        if (isEmpty()) throw new ExceptionQueueEmpty("DoubleEndedQueueEmpty");
        return header.getNext().getElem();
    }

    @Override
    public Object last() throws ExceptionQueueEmpty {
        if (isEmpty()) throw new ExceptionQueueEmpty("DoubleEndedQueueEmpty");
        return tailer.getPrev().getElem();
    }

    @Override
    public void insertFirst(Object object) {
        DoubleLinkedNode tmp = new DoubleLinkedNode(object, header, header.getNext());
        header.getNext().setPrev(tmp);
        header.setNext(tmp);
        size ++;
    }

    @Override
    public void insertLast(Object object) {
        DoubleLinkedNode tmp = new DoubleLinkedNode(object, tailer.getPrev(), tailer);
        tailer.getPrev().setNext(tmp);
        tailer.setPrev(tmp);
        size ++;
    }

    @Override
    public Object removeFirst() throws ExceptionQueueEmpty {
        if (isEmpty()) throw new ExceptionQueueEmpty("DoubleEndedQueueEmpty");
        DoubleLinkedNode old = header.getNext();
        old.getNext().setPrev(header);
        header.setNext(old.getNext());
        size --;
        return old.getElem();
    }

    @Override
    public Object removeLast() throws ExceptionQueueEmpty {
        if (isEmpty()) throw new ExceptionQueueEmpty("DoubleEndedQueueEmpty");
        DoubleLinkedNode old = tailer.getPrev();
        old.getPrev().setNext(tailer);
        tailer.setPrev(old.getPrev());
        size --;
        return old.getElem();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Deque %");
        DoubleLinkedNode tmp = header;
        do {
            sb.append(tmp.getElem() + " ");
            tmp = tmp.getNext();
        } while (tmp != null);
        sb.append(">");
        return sb.toString();
    }

    @Override
    public void traversal() {
        DoubleLinkedNode tmp = header;
        do {
            System.out.print(tmp.getElem() + " ");
            tmp = tmp.getNext();
        } while (tmp != null);
        System.out.println();
    }

    public static void main(String[] args) {
        Deque d = new DequeDLNode();
        for (int i = 0; i < 10; i ++){
            d.insertFirst(i);
        }
        d.traversal();
        for (int i = 0; i < 10; i ++){
            d.insertLast(i);
        }
        d.traversal();
        for (int i = 0; i < 10; i ++){
            System.out.print(d.removeFirst() + " ");
        }
        System.out.println();
        d.traversal();
        for (int i = 0; i < 10; i ++){
            System.out.print(d.removeLast() + " ");
        }
        System.out.println();
        d.traversal();

    }
}
