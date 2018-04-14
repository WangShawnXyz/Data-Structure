package Algorithm.Sorting;

public class QuickSort {
    protected int partition(int[] A, int l, int h){
        int i;
        int p;
        int firsthigh;
        p = h;
        firsthigh = l;
        for (i = l; i < h; i ++){
            if (A[i] < A[p]){
                swap(A, i, firsthigh);
                firsthigh ++;
            }
        }
        swap(A, firsthigh, p);
        return firsthigh;
    }
    public void quickSort(int[] A, int l, int h){
        int p;
        if (l < h) {
            p = partition(A, l, h);
            quickSort(A, l, p-1);
            quickSort(A, p+1, h);
        }


    }
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort Q = new QuickSort();
        int[] A = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        Q.quickSort(A, 0, A.length-1);
        for (int i = 0; i < A.length; i ++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}
