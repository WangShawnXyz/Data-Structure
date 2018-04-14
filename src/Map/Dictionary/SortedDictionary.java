package Map.Dictionary;

import PriorityQueue.entry.Entry;

import java.util.Iterator;

public interface SortedDictionary extends Dictionary{
    Entry first();
    Entry last();
    //返回关键码不小于key的条目组成的非降序迭代器
    Iterator successors(Object key);
    //返回关键码不大于key的条目组成的非升序迭代器
    Iterator predecessors(Object key);
}
