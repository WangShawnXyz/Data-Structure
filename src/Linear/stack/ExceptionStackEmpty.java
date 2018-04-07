package Linear.stack;

/**
 * 定义异常类
 * 当对空栈执行pop或top时， 将抛出本异常
 */
public class ExceptionStackEmpty extends RuntimeException {
    public ExceptionStackEmpty(String err){
        super(err);
    }
}
