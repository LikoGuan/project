package javabase.inter;

/**
 * Created by huazhao on 17/2/9.
 */
public abstract class AbstractProcessor implements Processor{
    public abstract String process(Object obj);

    public static void main(String[] args) {
        Processor p = new StringProcessor();
        p.process("hello");
    }
}
