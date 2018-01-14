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
//        int j = 0;
//        for (int i = 0; i <100 ; i++) {
//            j=j++;
//
//        }
        int i = 0;
        i = i+++ ++i;
        System.out.println(i);
//        System.out.println(j);
        y=x++ + ++x;

    }
}
