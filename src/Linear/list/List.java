package Linear.list;

import Linear.position.ExceptionPositionInvalid;
import Linear.position.Position;
import Linear.vector.ExceptionBoundaryViolation;

import java.util.Iterator;

public interface List {
    int getSize();
    boolean isEmpty();
    Position first();
    Position last();
    Position getNext(Position position) throws ExceptionBoundaryViolation, ExceptionPositionInvalid;
    Position getPrev(Position position) throws ExceptionBoundaryViolation, ExceptionPositionInvalid;
    Position insertFisrt(Position position);
    Position insertLast(Position position);

    //在position之前或之后添加对象e, 并将e返回， 方便链式编程。
    Position insetBefore(Position position, Object object) throws ExceptionPositionInvalid;
    Position insetAfter(Position position, Object object) throws ExceptionPositionInvalid;

    //删除给定位置的元素， 并将其返回
    Position remove(Position position) throws ExceptionPositionInvalid;
    Position removeFirst() throws ExceptionListEmpty;
    Position removeLast() throws ExceptionListEmpty;

    //替换给定位置的元素， 并将之前的元素返回
    Object replace(Position position, Object object) throws ExceptionPositionInvalid;
    //位置迭代器
    Iterator positions();
    //元素迭代器
    Iterator elements();

}
