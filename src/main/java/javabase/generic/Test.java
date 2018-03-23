package javabase.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazhao on 17/2/12.
 */
public class Test {

    public void func() {
        List<? extends Apple> alist = new ArrayList<Apple>();
        Apple apple = new Apple();
        //alist.add(apple);
        Apple a = alist.get(0);

        List<? super Apple> blist = new ArrayList<Apple>();
        blist.add(new Apple());
    }
}
