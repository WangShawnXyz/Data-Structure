package Map;

import java.util.Iterator;

public interface Map {
    int getSize();
    boolean isEmpty();
    Object get(Object key);
    //如果map中不存在以key为关键字的条目则插入（key，value）并返回null；
    //否则返回原先的value;
    Object put(Object key, Object value);
    Iterator entries();
}
