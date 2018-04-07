import java.util.Scanner;
public class Main {
    //求排列数
    public static long A(int up,int bellow)
    {
        long result=1;
        for(int i=up;i>0;i--)
        {
            result*=bellow;
            bellow--;
        }
        return result;
    }
    public static long C(int up,int below)//应用组合数的互补率简化计算量
    {
        long helf=below/2;
        if(up>helf)
        {
//            System.out.print(up+"---->");
            up=below-up;
//            System.out.print(up+"\n");

        }
        long denominator=A(up,up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        long numerator=A(up,below);//分子的排列数
        if (denominator == 0) return numerator;
        return numerator/denominator;

    }

    public static long getCombNum(int k, int a, int b, int c, int d){
        if (a == 0 || b == 0) return 0;
        long sum = 0;
        if (k % a == 0){    // 能被a整除的情况
            int num = k/a;
            if (num < b){
                sum += C(num, b);
            }
        }
        if (k % c == 0){ // 能被c整除的情况
            int num = k/c;
            if (num < d){
                sum += C(num, d);
            }
        }
        //不能被整除了

        int num = k / a; //计算k里面最多有几个a
        for (int i = 1; i <= num; i ++){
            long tmp_sum = 0;
            if (num <= a){
                tmp_sum = C(i, b);
            }

            int remain = k - a * i;
            if (remain%c == 0){
                int c_num = remain/c;
                if (c_num <= d){
                    sum += tmp_sum*C(c_num, d);
                }
            }
        }

        return sum % 1000000007;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int k = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();

            System.out.println(getCombNum(k, a, b, c, d));
        }
    }
}