package stack;

public class TestStack {
    public static void main(String[] args){
        StackArray s = new StackArray();
        for (int i = 0; i < 100; i ++){
            s.push(i);
        }
        for (int i = 0; i < 100; i ++){
            System.out.print(s.pop() + " ");
        }
        System.out.println();
//        System.out.println(s.getSize());
    }

}
