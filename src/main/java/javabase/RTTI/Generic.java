package javabase.RTTI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazhao on 17/2/13.
 */
class CountedIngeger {
    private static int count = 0;
    private final int num = ++count;

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}

public class Generic<T> {
    private Class<T> type;
    private List<T> list = new ArrayList<T>();

    Generic(Class<T> c) {
        type = c;
    }

    public List<T> create(int n) {
        try {
            for (int i = 0; i < n; ++i) {
                T t = type.newInstance();
                list.add(t);
            }
        } catch (IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        Generic<CountedIngeger> g = new Generic<CountedIngeger>(CountedIngeger.class);
        System.out.println(g.create(10));
    }
}
