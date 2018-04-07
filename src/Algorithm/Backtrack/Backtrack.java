package Algorithm.Backtrack;

public class Backtrack<T> {
    boolean finished = false;
    int ncandidates;
    int MAXCANDIDATES;
    int[] c = new int[MAXCANDIDATES];

    void backtrack(int[] a, int k, T input){
        if (isASolution(a, k, input)){
            processSolution(a, k, input);
        }else{
            k = k + 1;
            constructCandiates(a, k,input, c, ncandidates);
            for (int i = 0; i < ncandidates; i ++){
                a[k] = c[i];
                makeMove(a, k, input);
                backtrack(a, k, input);
                if (finished)   return;
                unmakeMove(a, k, input);
            }
        }
    }
    boolean isASolution(int[] a, int k, T input){
        return false;
    }
    void processSolution(int[] a, int k, T input){

    }
    void constructCandiates(int[] a, int k, T input, int[] c, int ncandidates){

    }
    void makeMove(int[] a, int k, T input){}
    void unmakeMove(int[] a, int k, T input){}
}
