package stack;

public class StackArray implements StackADT{
    public static final int CAPACITY = 1024;// 数组的默认容量
    protected int capacity; // 数组实际容量
    protected Object[] S;   // 对象数组
    protected int top = -1;

    public StackArray() {
       capacity = this.CAPACITY;
       S = new Object[capacity];
    }
    //按给定大小创建栈
    public StackArray(int cap){
        this.capacity = cap;
        S = new Object[capacity];
    }

    //入栈操作
    @Override
    public void push(Object obj) throws ExceptionStackFull{
        if (getSize() == this.capacity){
            throw new ExceptionStackFull("Exception: StackOverflow");
        }
        S[++top] = obj;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty()){
            throw new ExceptionStackEmpty("Exception: StackEmpty");
        }
        Object elem = S[top];
        S[top--] = null;
        return elem;
    }

    //返回当前栈的规模
    @Override
    public int getSize() {
        return top+1;
    }

    //测试当前栈是否为空
    @Override
    public boolean isEmpty() {
        return top<0;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()){
            throw new ExceptionStackEmpty("Exception: StackEmpty");
        }
        return S[top];
    }
}
