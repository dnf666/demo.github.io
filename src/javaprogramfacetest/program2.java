package javaprogramfacetest;

/**
 * i++的问题 该程序的运行结果是什么
 * @author demo
 */
public class program2 {
    static {
        int x = 5;
    }
    static int x,y;
    public static void main(String[] args) {
        x--;
        myMethod();
        System.out.println(x + y++ + x);
    }

    private static void myMethod() {
        y=x++ + ++x;
    }
}
