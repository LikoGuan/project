package javabase.RTTI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by likoguan on 1/10/17.
 */

interface Flyable {
    public void fly();
}

interface Runnable {
    public void run();
}

abstract class Aerocraft implements Flyable {
    private Integer speed;

    public Aerocraft() {
    }

    public Aerocraft(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public abstract String getMererial();
}

public class Plane extends Aerocraft implements Runnable {
    private Integer weight;

    public Plane() {
    }

    public Plane(Integer weight) {
        super();
        this.weight = weight;
    }

    public Plane(Integer weight, Integer speed) {
        super(speed);
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight, boolean special) {
        if (special) {
            this.weight = weight / 2;
        } else {
            this.weight = weight;
        }
    }

    @Override
    public String getMererial() {
        return "iron";
    }

    public void fly() {
        System.out.println("fly ...");
    }

    public void run() {
        System.out.println("run ...");
    }

    private Integer calculate(Integer arg) {
        return getSpeed() * getWeight();
    }

    public static void print() {
        System.out.println("print");
    }

    public static void main(String[] args) {
        try {
        /*
        获取class
         */
            Class<?> clazz1 = Plane.class;

            Class<?> clazz2 = null;
            try {
                clazz2 = Class.forName("javabase.RTTI.Plane");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Plane plane = new Plane(1, 1);
            Class<?> clazz3 = plane.getClass();
            Aerocraft aerocraft = plane;
            Class<?> clazz4 = aerocraft.getClass();
            Runnable runnable = plane;
            Class<?> clazz5 = runnable.getClass();
            Flyable flyable = plane;
            Class<?> clazz6 = plane.getClass();
            System.out.println(clazz1 == clazz2);
            System.out.println(clazz1 == clazz3);
            System.out.println(clazz1 == clazz4);
            System.out.println(clazz1 == clazz5);
            System.out.println(clazz1 == clazz6);

        /*
        获取类名
         */
            //全限定类名
            String className = plane.getClass().getName();
            System.out.println(className);
            //类名
            String simpleClassName = plane.getClass().getSimpleName();
            System.out.println(simpleClassName);

        /*
        获取包
         */
            Package pkg = plane.getClass().getPackage();
            System.out.println(pkg.getName());

        /*
        获取父类
         */
            Class<?> subClazz = plane.getClass();
            while (subClazz.getSuperclass() != null) {
                System.out.println(subClazz.getName() + "'s super : " + subClazz.getSuperclass().getName());
                subClazz = subClazz.getSuperclass();
            }

        /*
        获取接口:只会获取到直接的接口，获取不到父类的接口
         */
            subClazz = plane.getClass();
            while (subClazz != null) {
                Class<?>[] interfaceArray = subClazz.getInterfaces();
                for (Class<?> item : interfaceArray) {
                    System.out.println(subClazz.getName() + "'s interface: " + item.getName());
                }
                subClazz = subClazz.getSuperclass();
            }

        /*
        获取构造器
         */
            subClazz = plane.getClass();
            //get all public constructors
            Constructor<?>[] constructors = subClazz.getConstructors();
            for (Constructor<?> item : constructors) {
                System.out.println(item);
            }
            //get public constructor by parameter
            System.out.println(subClazz.getConstructor());
            System.out.println(subClazz.getConstructor(Integer.class));
            System.out.println(subClazz.getConstructor(Integer.class, Integer.class));
            //System.out.println(subClazz.getConstructor(Integer.class, Integer.class, Integer.class));//no such method
            //get private constructor
            constructors = subClazz.getDeclaredConstructors();
            for (Constructor<?> item : constructors) {
                System.out.println(item);
            }
            System.out.println(subClazz.getDeclaredConstructor(Integer.class));

        /*
        使用构造器实例化
         */
            Constructor<?> constructor = subClazz.getConstructor();
            Object a = constructor.newInstance();
            constructor = subClazz.getConstructor(Integer.class);
            Object b = constructor.newInstance(1);
            constructor = subClazz.getConstructor(Integer.class, Integer.class);
            Object c = constructor.newInstance(1, 2);
            //必须通过getConstructor(Class<?>... parameterTypes) 获取的constructor才能用来实例化对象
            //constructor = subClazz.getConstructors()[2];
            //Object c = constructor.newInstance(1, 2);

        /*
        获取方法
         */
            Method[] methods = subClazz.getMethods();
            for (Method item : methods) {
                System.out.println(item);
            }
            //System.out.println(subClazz.getMethod("Plane", null));
            System.out.println(subClazz.getMethod("getWeight", null));
            System.out.println(subClazz.getMethod("setWeight", Integer.class, boolean.class));
            //System.out.println(subClazz.getMethod("calculate", Integer.class));

            methods = subClazz.getDeclaredMethods();
            Method method = subClazz.getDeclaredMethod("getWeight", null);
            Integer result = (Integer)method.invoke(c, null);
            method = subClazz.getDeclaredMethod("calculate", Integer.class);
            result = (Integer)method.invoke(c, 1);
            //get static method
            method = subClazz.getDeclaredMethod("print", null);
            method.invoke(c, null);


        /*
        获取属性
         */
        Field[] fields = subClazz.getDeclaredFields();
        for (Field item : fields) {
            System.out.println(item);
        }
        Field field = subClazz.getDeclaredField("weight");
        System.out.println(field);
        System.out.println(field.get(c));

        /*
        获取注解, 省略，之前写CSV工具的时候自己写过注解
         */

        /*
        获取泛型
         */

        /*
        获取class，interface, method，field的修饰符
         */
            subClazz = plane.getClass();
            int modifiers = subClazz.getModifiers();
            System.out.println(Modifier.isFinal(modifiers));

            modifiers = plane.getClass().getSuperclass().getModifiers();
            System.out.println(Modifier.isAbstract(modifiers));
            System.out.println(Modifier.isInterface(modifiers));

            modifiers = plane.getClass().getInterfaces()[0].getModifiers();
            System.out.println(Modifier.isAbstract(modifiers));
            System.out.println(Modifier.isInterface(modifiers));

        /*
        获取数组相关
         */



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
