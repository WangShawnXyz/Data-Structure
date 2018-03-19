package vector;

public class VectorArray implements Vector{
    final private int N = 1024;
    private int n = 0;//向量的实际规模
    private Object[] A;

    public VectorArray() {
        A = new Object[N];
        n  = 0;
    }

    @Override
    public int getSize() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n == 0)? true:false;
    }

    @Override
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        if (r < 0 ||  n-1 < r) throw new ExceptionBoundaryViolation("RankOutofRange");
        return A[r];
    }

    @Override
    public Object replaceAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        if (r < 0 ||  n-1 < r) throw new ExceptionBoundaryViolation("RankOutofRange");
        Object old = A[r];
        A[r] = object;
        return old;
    }

    @Override
    public Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        if (r < 0 ||  n-1 < r) throw new ExceptionBoundaryViolation("RankOutofRange");
        if (N <= n) throw new ExceptionBoundaryViolation("ArrayIsFull");
        for (int i = n; i > r; i --){
            A[i] = A[i-1];
        }
        A[r] = object;
        n ++;
        return null;
    }

    @Override
    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        if (r < 0 ||  N-1 < r) throw new ExceptionBoundaryViolation("RankOutofRange");
        Object old = A[r];
        for (int i = r; i < n; i ++){
            A[i] = A[i+1];
        }
        n --;
        return old;
    }

    @Override
    public Object add(Object object) {
        if (n >= N) throw new ExceptionBoundaryViolation("ArrayIsFull");
        A[n] = object;
        n ++;
        return object;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < n; i ++){
            sb.append(A[i] + ",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Vector v = new VectorArray();
        for (int i = 0; i < 20; i ++){
            v.add(i);
        }
        System.out.println(v.toString());
        v.insertAtRank(14, 100);
        System.out.println(v.toString());
        v.replaceAtRank(14, 99);
        System.out.println(v.toString());
        v.removeAtRank(14);
        System.out.println(v.toString());
        System.out.println(v.getAtRank(14));
    }
}
