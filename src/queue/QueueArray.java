package queue;

public class QueueArray<T> implements QueueADT{
    @Override
    public void enqueue(Object x) throws ExceptionQueueFull {

    }

    @Override
    public T dequeue() throws ExceptionQueueEmpty {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        return null;
    }

    @Override
    public void traversal() {

    }


    public static void main(String[] args){
        System.out.println("Hello");
    }
}
