package PriorityQueue;

import Linear.list.List;
import Linear.list.ListDoubleLinkedNode;
import PriorityQueue.comparator.Comparator;
import PriorityQueue.comparator.ComparatorDefault;
import PriorityQueue.entry.Entry;

public class PQueueUnsortedList implements PQueue {
    private List L;
    private Comparator C;
    public PQueueUnsortedList(Comparator c){
        this(c, null);
    }
    public PQueueUnsortedList(List l){
        this(new ComparatorDefault(), l);
    }
    public PQueueUnsortedList(Comparator c, List l) {
        L = new ListDoubleLinkedNode();
        C = c;
        while (!l.isEmpty()){
            Entry e = (Entry)l.removeFirst();
            insert(e.getKey(), e.getValue());
        }
    }

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        if (L.isEmpty())
            throw new ExceptionPQueueEmpty("");
        Entry minPos = (Entry) L.first();
        return minPos;
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        return null;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        return null;
    }
}
