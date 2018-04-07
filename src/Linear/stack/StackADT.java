package Linear.stack;

public interface StackADT {
    void push(Object x) throws ExceptionStackFull;
    Object pop() throws ExceptionStackEmpty;
    int getSize();
    boolean isEmpty();
    Object top() throws ExceptionStackEmpty;

}
