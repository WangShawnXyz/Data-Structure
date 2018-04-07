package PriorityQueue.point;

import PriorityQueue.comparator.Comparator;

public class CompatatorLexicographic implements Comparator {
    @Override
    public int compare(Object a, Object b) {
        Point2D x = (Point2D) a;
        Point2D y = (Point2D) b;
        if (x.getX() != y.getX()){
            return y.getX() - x.getX();
        }else {
            return y.getY() - x.getX();
        }
    }
}
