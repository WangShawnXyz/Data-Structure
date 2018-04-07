import java.util.Scanner;
public class Main2 {
    public static long getSum(int n, int m){
        if (n <= 0 || m <= 0) return 0;
        long sum = 0;
        boolean flag = false;
        for (int i = 1; i <= n; i ++){
            if (flag == false){
                sum -= i;
            }else{
                sum += i;
            }
            if (i % m == 0){
                flag = !flag;
            }
        }

        return sum;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(getSum(a, b));
        }
    }
}