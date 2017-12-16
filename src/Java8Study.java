import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8的新特性学习
 *
 * @author demo
 */
public class Java8Study {
    public static void main(String[] args) {
        /*lambdaTestList();
        lambdaTestNonInnerClass();
        lambdaTestRunnable();
        lambdaTestPredicate();*/
//        lambdaTestMapAndReduce();
//        lambdaTestString();
//        lambdaTestDistinct();
            lambdaTestSum();
    }

    /**
     * lambda表达式创建一个String列表
     */
    private static void lambdaTestString() {
         String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1, s2, s3, s4, s5);
        //去除字符串为1的元素
        List<String> list1 = list.stream().filter((x)->!x.equals("1")).collect(Collectors.toList());//collect方法将stream对象转换成list或set或hashmap或currenthashmap
        list1.forEach((x)-> System.out.println(x));
    }

    /**
     * lambda表达式的map和reduce
     */
    private static void lambdaTestMapAndReduce() {
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1, s2, s3, s4, s5);
        //正常情况
        for (String s:list)
        {
            System.out.println(s+"2");
        }
        //lambda表达式
        list.stream().map((str)->str+"2").forEach((str)-> System.out.println(str));
        Integer i1 = 1;
         Integer i2 = 2;
         Integer i3 = 3;
         Integer i4 = 4;
         Integer i5 = 5;
         Integer i6 = 6;
        List<Integer> list1 = Arrays.asList(i1,i2,i3,i4,i5,i6);
        Integer i = list1.stream().map((str)->str+1).reduce((sum,cost)->sum+cost).get();
        System.out.println(i);
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
        List<String> list = Arrays.asList(s1, s2, s3, s4, s5);
        //正常情况
        for (String s : list) {
            if (s.startsWith("1")) {
                System.out.println(s);
            }
        }

        //lambda
        filter(list, (str) -> str.startsWith("1"));

    }

    /**
     * lambda的过滤方法
     *
     * @param names     实验list
     * @param condition 过滤条件
     */
    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
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
        new Thread(() -> System.out.println("start")).start();
    }


    /**
     * lambda表达式在集合中的魅力
     */
    public static void lambdaTestList() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        //正常使用迭代
        for (String play : list) {
            System.out.println(play);
        }
        //lambda
        list.forEach((play) -> System.out.println(play));
    }

    /**
     * 替代匿名内部类
     */
    public static void lambdaTestNonInnerClass() {
        testInnerClass(() -> System.out.println("test1"));

    }

    /**
     * 用distinct方法去除重复元素
     */
    public static void lambdaTestDistinct(){
        List<Integer> list = Arrays.asList(2,3,4,56,1,2,3);
        List list1 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list1);
    }
    public static void testInnerClass(TestClass testClass) {
        System.out.println("start test");
    }


    /**
     * 用lambda表达式实现求java的最大值，和，最小值，平均数
     */
    public static void lambdaTestSum(){
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

}

interface TestClass {
    void lalla();
}
