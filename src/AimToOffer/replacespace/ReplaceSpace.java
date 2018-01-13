package AimToOffer.replacespace;

import java.util.Arrays;
import java.util.List;

/**
 * 替换字符串的空格
 *
 * @author demo
 */
public class ReplaceSpace {
    private String test = "we are family";
    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();
        replaceByJava(replaceSpace.test);
    }

    private static void replaceByJava(String test) {
       String result = test.replace(" ","#20");
        System.out.println(result);
    }

}
