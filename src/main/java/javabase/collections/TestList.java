package javabase.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazhao on 16/7/1.
 */
public class TestList {
    public static void main(String[] args) {
        List<String> test = new ArrayList<String>();
        test.add("hello");
        test.add("world");


        List<String> mylist = new ArrayList<String>(){{add("a");add("b");}};
        for (String x : mylist) {
            System.out.println(x);
        }
        mylist.add("c");
        mylist.add("d");
    }
}
