package javabase.generic;

/**
 * Created by likoguan on 7/10/17.
 */
class Node<T> {
    public T data;
    public Node(T data) { this.data = data; }
    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

public class Tester {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn; // A raw type - compiler throws an unchecked warning
        n.setData("Hello"); // Causes a ClassCastException to be thrown. 因为擦除后，编译器为MyNode进行了Bridge，
        //setData(Object data) {setData((Integer)data);} 这样String转Integer失败，才抛出异常
// Integer x = mn.data;
    }
}


