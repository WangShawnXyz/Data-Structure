package list;

import deque.DoubleLinkedNode;
import position.ExceptionPositionInvalid;
import position.Position;
import vector.ExceptionBoundaryViolation;
import java.util.Iterator;

public class ListDoubleLinkedNode implements List {
    protected int size;
    protected DoubleLinkedNode header, tailer;

    public ListDoubleLinkedNode() {
        size = 0;
        header = new DoubleLinkedNode("HEADER", null, null);
        tailer = new DoubleLinkedNode("TAILER", header, null);
        header.setNext(tailer);
    }
    protected DoubleLinkedNode checkPosition(Position position) throws ExceptionPositionInvalid{
        if (position == null) throw new ExceptionPositionInvalid("NullPosition");
        if (position == header) throw new ExceptionPositionInvalid("HeaderPosition");
        if (position == tailer) throw new ExceptionPositionInvalid("TailerPosition");
        return (DoubleLinkedNode)position;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (getSize() == 0)? true:false;
    }

    @Override
    public Position first() throws ExceptionListEmpty{
        if (isEmpty()) throw new ExceptionListEmpty("ListEmpty");
        return header.getNext();
    }

    @Override
    public Position last() throws ExceptionListEmpty{
        if (isEmpty()) throw new ExceptionListEmpty("ListEmpty");
        return header.getPrev();
    }

    @Override
    public Position getNext(Position position) throws ExceptionBoundaryViolation, ExceptionPositionInvalid {
        DoubleLinkedNode v = checkPosition(position);
        DoubleLinkedNode next = v.getNext();
        if (next == tailer) throw new ExceptionBoundaryViolation("ListRankOutofRange");
        return next;
    }

    @Override
    public Position getPrev(Position position) throws ExceptionBoundaryViolation, ExceptionPositionInvalid {
        DoubleLinkedNode v = checkPosition(position);
        DoubleLinkedNode prev = v.getPrev();
        if (prev == header) throw new ExceptionBoundaryViolation("ListRankOutofRange");
        return prev;
    }

    @Override
    public Position insertFisrt(Position position) {
        DoubleLinkedNode v = checkPosition(position);

        return null;
    }

    @Override
    public Position insertLast(Position position) {
        return null;
    }

    @Override
    public Position insetBefore(Position position, Object object) throws ExceptionPositionInvalid {
        return null;
    }

    @Override
    public Position insetAfter(Position position, Object object) throws ExceptionPositionInvalid {
        return null;
    }

    @Override
    public Position remove(Position position) throws ExceptionPositionInvalid {
        return null;
    }

    @Override
    public Position removeFirst() {
        return null;
    }

    @Override
    public Position removeLast() {
        return null;
    }

    @Override
    public Object replace(Position position, Object object) throws ExceptionPositionInvalid {
        return null;
    }

    @Override
    public Iterator positions() {
        return null;
    }

    @Override
    public Iterator elements() {
        return null;
    }

    public static void main(String[] args) {

    }
}
