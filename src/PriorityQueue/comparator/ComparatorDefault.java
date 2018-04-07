package PriorityQueue.comparator;

public class ComparatorDefault implements Comparator {
    @Override
    public int compare(Object a, Object b) {
        return ((Comparable)a).compareTo(b);
    }
}
