import java.util.Optional;

/**
 * java8的optional 安全解决nullexception
 */
public class Java8Optional {
    public static void main(String[] args) {
        testOptional();
    }

    /**
     * test optional的方法
     */
    private static void testOptional() {
        Integer test1 = null;
        Integer test2 = 1;
        /*ofnullable方法*/
       Optional<Integer> a =  Optional.ofNullable(test1);
       Optional<Integer> b = Optional.of(test2);
        System.out.println(sum(a,b));
    }

    private static int sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());
        //orelse方法是当值为空时，另外赋予新值
            Integer i = a.orElse(new Integer(0));
            //get方法是取得值，但是必须保证值不为空
            Integer i2 = b.get();
        return i+i2;
    }
}
