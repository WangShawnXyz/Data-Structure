package queue;

public class JosephusCircle {
    //利用队列结构模拟Josophus环
    public static Object Josephus(QueueArray Q, int k) {
        if (Q.isEmpty()) return null;
//        System.out.println(Q.Q[0]);
        while (Q.getSize() > 1) {//不断迭代
            Q.traversal();//显示当前的环
            for (int i=0; i < k; i++)//将山芋向前传递k次
                Q.enqueue(Q.dequeue());
            Object e = Q.dequeue();//拿着山芋的孩子退出
            System.out.println("\n\t" + e + "退出");
        }
        return Q.dequeue();//最后剩下的那个孩子
    }
    //将一组对象组织为一个队列
    public static QueueArray buildQueue(Object a[]) {
        QueueArray Q = new QueueArray(a.length+1);
        for (int i=0; i<a.length; i++)
            Q.enqueue(a[i]);
        return Q;
    }
    //测试用main方法        rear = (rear+1) % capacity;
    public static void main(String[] args) {
        String[] kid = {"Alice", "Bob", "Cindy",
                "Fred", "Gene", "Hope", "Kim",
                "Lance", "Mike", "Doug", "Irene",
                "Nancy", "Ed", "Jack", "Ollie"};
        System.out.println("最终的幸运者是" + Josephus(buildQueue(kid), 1));
    }
}
