package PriorityQueue;

import PriorityQueue.entry.Entry;

public interface PQueue {
    int getSize();
    boolean isEmpty();
    Entry getMin() throws ExceptionPQueueEmpty;
    Entry insert(Object key, Object value) throws ExceptionKeyInvalid;
    Entry delMin() throws ExceptionPQueueEmpty;
}
