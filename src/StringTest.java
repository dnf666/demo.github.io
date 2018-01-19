/**
 * create by 戴哥 on 2018/1/2
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "0123456789";
        String s1 = new String(s.toCharArray(),2,3);
        System.out.println(s1);
    }
}
