package linearStructure.queue;

public class QueueArray<T> implements QueueADT{
    public static final int CAPACITY = 1024;
    protected int capacity = 0;
    protected T[] Q;
    protected int front = 0;
    protected int rear = 0;
    public QueueArray() {
        this.capacity = CAPACITY;
        this.Q = (T[]) new Object[capacity];
    }

    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.Q = (T[]) new Object[capacity];
    }

    @Override
    public void enqueue(Object x) throws ExceptionQueueFull {
        if (getSize() == capacity-1){
            throw new ExceptionQueueFull("Exception: QueueFull");
        }
        Q[rear] = (T) x;
        rear = (rear+1) % capacity;

    }

    @Override
    public T dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()){
            throw new ExceptionQueueEmpty("Exception: QueueEmpty");
        }
        T elem = Q[front];
        front = (front+1) % capacity;
        return elem;
    }

    @Override
    public int getSize() {
        return (capacity-front+rear) % capacity;
    }

    @Override
    public boolean isEmpty() {
        if (front == rear){
            return true;
        }
        return false;
    }

    @Override
    public T front() throws ExceptionQueueEmpty {
        if (isEmpty()){
            throw new ExceptionQueueEmpty("Exception: QueueEmpty");
        }
        return Q[rear];
    }

//    @Override
    public void traversal() {
//        int[] Q = {0, 1, 2, 3, 4, 5, 6, 7, 8, -1};
//        int capacity = 9;
//        int front = 6;
//        int rear = 1;
        for (int i = this.front; i != this.rear; i = (i+1)%(this.capacity)){
            System.out.print(Q[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args){
        QueueArray q = new QueueArray(5+1);
        for (int i = 0; i < 100; i ++){
            q.enqueue(i);
        }
        while (q.getSize()>1){
            for (int i = 1; i <= 2; i ++){
                q.enqueue(q.dequeue());
            }
            q.dequeue();
        }
        System.out.println(q.dequeue());
    }
}
