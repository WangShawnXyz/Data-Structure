package Map.Dictionary;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        List l = new ArrayList<>();
        for (int i = 0; i < 10; i ++){
            l.add(i);
        }
        Iterator I = l.iterator();
        while (I.hasNext()){
            System.out.println(I.next());
        }
    }
}
