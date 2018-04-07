package Linear.stack;

public class TestStack extends StackArray{
    public static boolean ParenMatch(String s){
        if (s.isEmpty())    return true;
        StackArray stack = new StackArray(s.length());
        for (int i = 0; i < s.length(); i ++){
            if (s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }else if (s.charAt(i) == ')'){
                if (stack.isEmpty()){
//                    System.out.println("empty exit");
                    return false;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty()){

            return true;
        }
//        System.out.println("done");
        return false;

    }
    public static void testParenMatch(){
        String[] s = new String[4];
        s[0] = "()()(())(((()())))";   //matched
        s[1] = "((())"; //not match
        s[2] = "";
        s[3] = "(";
        for (int i = 0; i < s.length; i ++){
            System.out.println(TestStack.ParenMatch(s[i]));
        }
    }
    public static void main(String[] args){
        TestStack.testParenMatch();


    }

}
