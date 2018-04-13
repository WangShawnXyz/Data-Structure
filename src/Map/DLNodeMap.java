package Map;

import Linear.list.List;
import Linear.list.ListDoubleLinkedNode;
import Linear.position.Position;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;

import java.util.Iterator;

public class DLNodeMap implements Map {
    private List L;//存放条目的列表
    private EqualityTester T;//判等器

    public DLNodeMap() {
        this(new DefaultEqualityTester());
    }

    public DLNodeMap(EqualityTester t) {
        T = t;
        L = new ListDoubleLinkedNode();
    }

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    @Override
    public Object get(Object key) {
        Iterator P = L.positions();
        while (P.hasNext()){
            Position pos = (Position) P.next();
            Entry e = (EntryDefault) pos.getElem();
            if (T.isEqualTo(e.getKey(), key)){
                return e.getValue();
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        Iterator P = L.positions();
        while (P.next() != null){
            Position pos = (Position) P.next();
            Entry entry = (Entry) pos.getElem();
            if (T.isEqualTo(entry.getKey(), key)){
                Object oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        Entry entry = new EntryDefault(key, value);
        L.insertFisrt((Position) entry);
        String s = "123";
        s.hashCode();
        return null;
    }

    @Override
    public Iterator entries() {
        return null;
    }
}
