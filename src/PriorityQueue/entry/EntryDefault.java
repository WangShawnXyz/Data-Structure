package PriorityQueue.entry;

public class EntryDefault implements Entry {
    protected Object key;
    protected Object value;

    public EntryDefault(Object k, Object v) {
        key = k;
        value = v;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object setKey(Object k) {
        Object old = key;
        key = k;
        return old;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object v) {
        Object old = value;
        value = v;
        return old;
    }
}
