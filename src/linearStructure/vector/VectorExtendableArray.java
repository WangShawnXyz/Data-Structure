package linearStructure.vector;

public class VectorExtendableArray implements Vector{
    private int N = 10;
    private int n = 0;//向量的实际规模
    private Object[] A;

    public VectorExtendableArray() {
        A = new Object[N];
        n  = 0;
    }
    public VectorExtendableArray(int initCapacity) throws ExceptionBoundaryViolation{
        if (initCapacity <= 0) throw new ExceptionBoundaryViolation("CannotInitAnEmptyVector");
        A = new Object[initCapacity];
        N = initCapacity;
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
        if (N <= n){
            Object[] B = new Object[N * 2];
            for (int i = 0; i < N; i ++){
                B[i] = A[i];
            }
            N *= 2;
            A = B;
        }
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
        if (N <= n){
            Object[] B = new Object[N * 2];
            for (int i = 0; i < N; i ++){
                B[i] = A[i];
            }
            N *= 2;
            A = B;
        }
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
        Vector v = new VectorExtendableArray();
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
