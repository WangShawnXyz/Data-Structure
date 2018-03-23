package linearStructure.vector;

public interface Vector {
    int getSize();
    boolean isEmpty();

    //Vector的增删改查
    //以下接口都返回更改之前的元素/或者被更改的元素
    Object getAtRank(int r) throws ExceptionBoundaryViolation;
    Object replaceAtRank(int r, Object object) throws ExceptionBoundaryViolation;
    Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation;
    Object removeAtRank(int r) throws ExceptionBoundaryViolation;
    Object add(Object object);
}
