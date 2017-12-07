import java.util.ArrayList;
import java.util.List;

/**
 * java8的新特性学习
 */
public class Java8Study {
    public static void main(String[] args) {
        lambdaTest();
    }


    /**
     * lambda表达式的魅力
     */
    public static void lambdaTest(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.forEach((play)-> System.out.println(play));
    }

}
