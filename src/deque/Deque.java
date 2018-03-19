package deque;

import queue.ExceptionQueueEmpty;
import queue.QueueArray;

public interface Deque {
    int getSize();
    boolean isEmpty();
    Object first() throws ExceptionQueueEmpty;//返回首元素
    Object last() throws ExceptionQueueEmpty;//返回末元素
    void insertFirst(Object object); //新元素作为首元素插入
    void insertLast(Object object); //将新元素作为末元素插入
    Object removeFirst() throws ExceptionQueueEmpty;
    Object removeLast() throws ExceptionQueueEmpty;
    void traversal(); //遍历
}
