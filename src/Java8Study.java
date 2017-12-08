import java.util.ArrayList;
import java.util.List;

/**
 * java8的新特性学习
 */
public class Java8Study {
    public static void main(String[] args)
    {
        lambdaTestList();
        lambdaTestNonInnerClass();
        lambdaTestRunnable();
    }

    private static void lambdaTestRunnable() {
        //正常使用
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
            }
        }).start();

        //lambda使用
        new Thread(()-> System.out.println("start")).start();
    }


    /**
     * lambda表达式在集合中的魅力
     */
    public static void lambdaTestList(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        //正常使用迭代
        for (String play: list){
            System.out.println(play);
        }
        //lambda
        list.forEach((play)-> System.out.println(play));
    }

    /**
     * 替代匿名内部类
     */
    public static void lambdaTestNonInnerClass(){
        testInnerClass(()-> System.out.println("test1"));

    }
    public static void testInnerClass(TestClass testClass){
        System.out.println("start test");
    }


}
interface TestClass{
   void lalla();
}
