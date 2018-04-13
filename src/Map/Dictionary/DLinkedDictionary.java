package Map.Dictionary;

import Map.DefaultEqualityTester;
import Map.EqualityTester;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DLinkedDictionary implements Dictionary {
    private List<Entry> L;
    private EqualityTester T;

    public DLinkedDictionary() {
        this(new DefaultEqualityTester());
    }

    public DLinkedDictionary(EqualityTester t) {
        T = t;
        L = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return L.size();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    @Override
    public Entry find(Object key) {
        Iterator P = L.iterator();
        while (P.hasNext()){
            Entry e = (Entry) P.next();
            if (T.isEqualTo(e.getKey(), key)){
                return e;
            }
        }
        return null;
    }

    @Override
    public Iterator findAll(Object key) {
        List list = new LinkedList();
        Iterator iter = L.iterator();
        while (iter.hasNext()){
            Entry e = (Entry) iter.next();
            if (T.isEqualTo(key, e.getKey())){
                list.add(e);
            }
            return list.iterator();
        }
        return null;
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);
        L.add(e);
        return e;
    }

    @Override
    public Entry remove(Object key) {
        Iterator iterator = L.iterator();
        while (iterator.hasNext()){
            Entry e = (Entry) iterator.next();
            if (T.isEqualTo(e.getKey(), key)){
                L.remove(e);
                return e;
            }
        }
        return null;
    }

    @Override
    public Iterator entries() {
        return L.iterator();
    }
}
