package stack;

public interface StackADT {
    public  void push(Object x) throws ExceptionStackFull;
    public  Object pop() throws ExceptionStackEmpty;
    public  int getSize();
    public  boolean isEmpty();
    public  Object top() throws ExceptionStackEmpty;

}
