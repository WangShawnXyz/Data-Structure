package Linear.stack;

/**
 * 对满的栈进行push操作时，将抛出本异常
 */
public class ExceptionStackFull extends RuntimeException {
    public ExceptionStackFull(String err) {
        super(err);
    }
}
