import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * java8的新特性学习
 * @author demo
 */
public class Java8Study {
    public static void main(String[] args)
    {
        lambdaTestList();
        lambdaTestNonInnerClass();
        lambdaTestRunnable();
        lambdaTestPredicate();
    }
    /**
     * lambada 应用场景 predicate接口实现对集合的操作
     */
    private static void lambdaTestPredicate() {
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1,s2,s3,s4,s5);
        //正常情况
        for(String s:list){
            if (s.startsWith("1")){
                System.out.println(s);
            }
        }

        //lambda
        filter(list,(str)->str.startsWith("1"));

    }

    /**
     * lambda的过滤方法
     * @param names 实验list
     * @param condition 过滤条件
     */
    public  static void filter(List<String> names, Predicate<String> condition){
        for (String name : names){
            if(condition.test(name)){
                System.out.println(name);
            }

        }
    }



    /**
     * lambda 应用场景 实现runnable接口
     */
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
