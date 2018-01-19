package javaprogramfacetest;

/**
 * 弄清值传递和引用传递
 * @author demo
 */

class value{
    public int i = 15;
}
public class ValueTransmit {
    public static void main(String[] args) {
        first();
    }

    private static void first() {
        int i = 100;
        value v = new value();
        v.i = 25;
        second(v,i);
        System.out.println(","+v.i);
        System.out.println(i);

    }

    private static void second(value v, int i) {

         i = 0;
        v.i = 20;
        value value = new value();
        v = value;
        System.out.print(v.i+","+i);
    }

}
