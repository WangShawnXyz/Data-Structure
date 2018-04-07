package Algorithm.Backtrack;

public class AllSubset {
    int N;
    int[] path;
    public AllSubset(int n){
        N = n;
        path = new int[n];
    }
    void backtrack(int t){
        if (t > N-1){ //排列完成  打印结果
            System.out.print("{");
            for (int i = 0; i < N; i ++){
                if (path[i] == 1)
                    System.out.print(i + " ");
            }
            System.out.println("}");
            return;
        }
        for (int i = 0; i < 2; i ++){
            path[t] = i;
            backtrack(t+1);
        }
    }

    public static void main(String[] args) {
        AllSubset main = new AllSubset(4);
        main.backtrack(0);
    }
}
