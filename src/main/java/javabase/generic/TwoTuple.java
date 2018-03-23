package javabase.generic;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by likoguan on 30/09/17.
 */
public class TwoTuple<T, U> {
    public final T t;

    public final U u;

    public TwoTuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static <T, U> TwoTuple<T, U> tuple(T t, U u) {
        return new TwoTuple<T, U>(t, u);
    }

    public static TwoTuple<String, Integer> tuple() {
        return tuple("a", 0);
    }

    public static TwoTuple tuple2() {
        return tuple("a", 0);
    }

    public static void func1(TwoTuple<String, String> x) {

    }

    public static void main(String[] args) {
        Class clazz1 = (new ArrayList<Integer>()).getClass();
        Class clazz2 = (new ArrayList<String>()).getClass();

        List<Integer> list = new ArrayList<Integer>();
        TypeVariable[] tv = list.getClass().getTypeParameters();
        for (TypeVariable x : tv) {
            System.out.println(x.getName());
            System.out.println(x.getBounds());
        }
        System.out.println(clazz1 == clazz2);
        TwoTuple<String, Integer> t1 = tuple();
        TwoTuple<System, Integer> t2 = tuple2();
        func1(tuple2());
    }
}
