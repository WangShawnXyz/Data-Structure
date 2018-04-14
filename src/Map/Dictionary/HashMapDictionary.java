package Map.Dictionary;

import Map.DefaultEqualityTester;
import Map.EqualityTester;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashMapDictionary implements Dictionary {
    private Dictionary[] A;//桶数组
    private int N;//散列表长度
    private final double maxLoadFactor = 0.75;//装填因子上限
    private int size;//实际规模
    private EqualityTester T;//比较器

    public HashMapDictionary() {
        this(0, new DefaultEqualityTester());
    }

    public HashMapDictionary(int n,EqualityTester t) {
        T = t;
        N = p(n);
        A = new Dictionary[N];
        for (int i = 0; i < N; i ++){
            A[i] = new DLinkedDictionary();
        }
        size = 0;
    }

//---------------辅助方法------------
    //散列定址函数
    private int h(Object key){
        return key.hashCode() % N;
    }
    //取不小于n的最小素数
    private int p(int n) {
        if (n < 3)
            return 3;
        n = n | 1; //奇数化
        while (isPrime(n)) {
            n += 2;
        }
        return n;
    }
    //判断n是否为素数
    private boolean isPrime(int n){
        for (int i = 3; i < Math.sqrt(n); i ++){
            if (n/i * i == n){ //n和i都是整数， 直接除的结果向下取整了
                return false;
            }
        }
        return true;
    }

//---------------实现接口---------------------
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry find(Object key) {
        return A[h(key)].find(key);
    }

    @Override
    public Iterator findAll(Object key) {
        return A[h(key)].findAll(key);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = A[h(key)].insert(key, value);
        size ++;
        if (size > N*maxLoadFactor){
            rehash();
        }
        return e;
    }

    private void rehash() {
        Iterator iterator = entries();
        N = p(N << 1);
        A = new Dictionary[N];
        for (int i = 0; i < N; i ++){
            A[i] = new DLinkedDictionary();
        }
        while (iterator.hasNext()){
            Entry e = (Entry) iterator.next();
            Object key = e.getKey();
            Object value = e.getValue();
            A[h(key)].insert(key, value);
        }

    }

    @Override
    public Entry remove(Object key) {
        Entry oldEntry = A[h(key)].remove(key);
        if (oldEntry != null) {
            size --;
        }
        return oldEntry;
    }

    @Override
    public Iterator entries() {
        List L = new LinkedList<>();
        for (int i = 0; i < N; i ++){
            Iterator iterator = A[i].entries();
            while (iterator.hasNext()){
                L.add(iterator.next());
            }
        }
        return L.iterator();
    }
}
