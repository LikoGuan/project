package javabase.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by likoguan on 7/10/17.
 */
public class GenericObject {
    public List<String> lists;

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public static void main(String[] args) {
        try {
            /*
            如果一个方法返回一个泛型类，我们可以通过method对象去调用getGenericReturnType来得到这个泛型类具体的参数化类型是什么

            1 反射得到返回类型为泛型类的方法
            2 调用getGenericReturnType得到方法返回类型中的参数化类型
            3 判断该type对象能不能向下转型为ParameterizedType
            4 转型成功，调用getActualTypeArguments得到参数化类型的数组，因为有的泛型类，不只只有一个参数化类型如Map
            5 取出数组中的每一个的值，转型为Class对象输出。
             */
            Class clz = GenericObject.class;
            Method method = clz.getMethod("getLists");
            Type genericType = method.getGenericReturnType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = ((ParameterizedType) genericType);
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type type : types) {
                    Class actualClz = ((Class) type);
                    System.out.println("参数化类型为 ： " + actualClz);
                }
            }


            /*
            如果没有一个方法返回泛型类型，那么我们也可以通过方法的参数为泛型类，来得到泛型的参数化类型，如上面类中的setLists方法

            因为方法的参数为泛型类型的可能不止一个，所以通过getGenericParameterTypes得到是一个数组，我们需要确定每一个元素，是否是具有参数化类型。
             */
            Method setMethod = clz.getMethod("setLists", List.class);
            Type[] genericParameterTypes = setMethod.getGenericParameterTypes();
            for (Type genericParameterType : genericParameterTypes) {
                System.out.println("GenericParameterTypes为 ： " + genericParameterType.getTypeName());
                if (genericParameterType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = ((ParameterizedType) genericParameterType);
                    System.out.println("ParameterizedType为 :" + parameterizedType.getTypeName());
                    Type types[] = parameterizedType.getActualTypeArguments();
                    for (Type type : types) {
                        System.out.println("参数化类型为 ： " + ((Class) type).getName());
                    }
                }

            }



            /*
            如果连方法参数都不带泛型类，那么只剩下最后一种情况，通过变量类型，即用Field类

            原理和上面的一样，只不过Type对象是通过field.getGenericType()
             */
            Field field = clz.getField("lists");
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType){
                ParameterizedType parameterizedType = ((ParameterizedType) type);
                Type [] types = parameterizedType.getActualTypeArguments();
                for (Type type1 : types) {
                    System.out.println("参数化类型 ： " + ((Class) type1).getTypeName());
                }
            }
        } catch (Exception ex) {

        }
    }
}
