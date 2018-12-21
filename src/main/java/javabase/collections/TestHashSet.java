package javabase.collections;

import java.util.*;

/**
 * Created by huazhao on 16/7/1.
 */
class ObjectIterable implements Iterable {
    private Integer[] items;

    public ObjectIterable() {
        items = new Integer[10];
        for (int i=0; i<10; i++) {
            items[i] = i;
        }
    }

    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;
            public boolean hasNext() {
                return index < items.length;
            }

            public Object next() {
                return items[index++];
            }

            public void remove() {

            }
        };
    }
}

public class TestHashSet {


    public static void main(String[] args) {
        ObjectIterable obj = new ObjectIterable();
        Iterator it = obj.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("======");



//        obj.forEach(i -> {i = (Integer)i * 2;});

        for (Object x : obj) {
            System.out.println((Integer)x);
        }
//        int x = 97 & 15;
//        for (int i=1; i<200; i++) {
//            int y = i & 15;
//            if (y == x) {
//                System.out.println(i);
//            }
//        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("q", 1);
        map.get("a");
        map.remove("a");
        map.isEmpty();
        Set<String> keySet = map.keySet();
        keySet.add("exception");
        map.values();
        map.entrySet();



        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i=0; i<10; i++) {
            hs.add(i);
        }

        hs.contains(new Integer(5));
        hs.isEmpty();
        hs.size();
        hs.remove(9);
        //Iterator<Integer> it = hs.iterator();
    }
}


