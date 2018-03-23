package javabase.generic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by likoguan on 30/09/17.
 */
public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] creat(int size) {
        //return (T[])Array.newInstance(kind, size);
        Object obj = Array.newInstance(kind, size);
        String[] x = (String[])Array.newInstance(String.class, size);
        Object y = null;
        try {
            y = kind.newInstance();
        } catch (Exception ex) {

        }

        return (T[])obj;
    }

    public boolean f(Object obj) {
        //return obj instanceof T;
        //T t = new T();
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        ArrayMaker<String> am = new ArrayMaker<String>(String.class);
        String[] sa = am.creat(9);
        System.out.println(Arrays.toString(sa));
        System.out.println(am.f(new String()));
        System.out.println(am.f(new Integer(1)));
    }
}

class Building {}
class House extends Building {}

class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object obj) {
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        ClassTypeCapture<House> ctc = new ClassTypeCapture<House>(House.class);
        System.out.println(ctc.f(new Building()));
        System.out.println(ctc.f(new House()));
    }
}

interface English {
    void get();
}

interface Frech {
    void put();
}

class Colored<T extends English & Frech> {
    T item;
    public Colored(T item) {
        this.item = item;
    }
    public void getColor() {
        item.get();
        item.put();
    }
}
