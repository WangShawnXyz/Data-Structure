package linearStructure.queue;

public interface QueueADT<T> {
    //基本方法
    void enqueue(T x) throws ExceptionQueueFull; //入队
    T dequeue() throws ExceptionQueueEmpty; //出队
    //
    int getSize();
    boolean isEmpty();
    T front() throws ExceptionQueueEmpty;  //若队列非空返回队首元素(不删除)

}
