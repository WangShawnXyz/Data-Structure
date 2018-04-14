package Map.Dictionary;

import PriorityQueue.comparator.Comparator;
import PriorityQueue.comparator.ComparatorDefault;
import PriorityQueue.entry.Entry;
import PriorityQueue.entry.EntryDefault;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSortedDictionary implements SortedDictionary {
    ArrayList S; //有序查找表
    Comparator C;//比较器

    public ArrayListSortedDictionary() {
        this(new ComparatorDefault());
    }

    public ArrayListSortedDictionary(Comparator c) {
        C = c;
        S = new ArrayList();
    }

//---------------------辅助方法--------------------------
    private int binSearch(ArrayList s, Comparator c, Object key, int low, int high){
        if (low > high) return -1;
        int middle = (low + high)>>1;
        Entry e = (Entry)s.get(middle);
        int flag = c.compare(key, e.getKey());

        if (flag < 0)
            return binSearch(s, c, key, low, middle-1);
        else if (flag > 0)
            return binSearch(s, c, key, middle+1, high);
        else
            return middle;
    }

//----------------------无序ADT方法--------------------------


    @Override
    public int getSize() {
        return S.size();
    }

    @Override
    public boolean isEmpty() {
        return S.isEmpty();
    }

    @Override
    public Entry find(Object key) {
        int k = binSearch(S, C, key, 0, S.size()-1);
        Entry e = (Entry)S.get(k);
        if (0 > k || k >= S.size() || (0 != C.compare(key,e.getKey())))
            return null;
        return e;
    }

    @Override
    public Iterator findAll(Object key) {
        ArrayList list = new ArrayList();
        int k = binSearch(S, C, key, 0, S.size()-1);
        Entry e = (Entry)S.get(k);
        if (0 > k || k >= S.size() || (0 != C.compare(key,e.getKey())))
            return list.iterator();
        list.add(e);
        int low = k-1;
        while (low >= 0){
            Entry lowE = (Entry) S.get(low);
            if (C.compare(key, lowE.getKey()) == 0){
                list.add(lowE);
                low --;
            }else{
                break;
            }
        }
        int high = k + 1;
        while (high <= S.size()){
            Entry highE = (Entry) S.get(low);
            if (C.compare(key, highE.getKey()) == 0){
                list.add(highE);
                high ++;
            }else{
                break;
            }
        }
        return list.iterator();
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);
        if (S.isEmpty()){
            S.add(e);
            return e;
        }
        int k = binSearch(S, C, key, 0, S.size()-1);
        if (0 > k || k >= S.size() || C.compare(key, (Entry)S.get(k)) != 0){
            S.add(e);
        }else {
            S.add(k, e);
        }
        return e;
    }

    @Override
    public Entry remove(Object key) {
        int k = binSearch(S, C, key, 0, S.size()-1);
        if (!(0 > k || k >= S.size() || C.compare(key, (Entry)S.get(k)) != 0)){
            S.remove(k);
            return (Entry) S.get(k);
        }
        return null;
    }

    @Override
    public Iterator entries() {
        return S.iterator();
    }

    @Override
    public Entry first() {
        if (S.isEmpty()){
            return (Entry) S.get(0);
        }
        return null;
    }

    @Override
    public Entry last() {
        if (!S.isEmpty()){
            return (Entry)S.get(S.size()-1);
        }
        return null;
    }

    @Override
    public Iterator successors(Object key) {
        ArrayList list = new ArrayList();
        int k = binSearch(S, C, key, 0, S.size()-1);
        if (0 > k || k >= S.size() || C.compare(key, ((Entry)S.get(k)).getKey()) != 0)
            return list.iterator(); // 没找到就返回空的迭代器
        while (++k < S.size()){
            if (C.compare(key, ((Entry)S.get(k)).getKey()) != 0){
                break;
            }
        }
        for (int i = k; i < S.size(); i ++){
            list.add((Entry)S.get(i));
        }
        return list.iterator();
    }

    @Override
    public Iterator predecessors(Object key) {
        ArrayList list = new ArrayList();
        int k = binSearch(S, C, key, 0, S.size()-1);
        if (0 > k || k >= S.size() || C.compare(key, ((Entry)S.get(k)).getKey()) != 0)
            return list.iterator(); // 没找到就返回空的迭代器
        while (-1 < --k){
            if (C.compare(key, ((Entry)S.get(k)).getKey()) != 0){
                break;
            }
        }
        for (int i = 0; i < k; i ++){
            list.add((Entry)S.get(i));
        }
        return list.iterator();
    }
}