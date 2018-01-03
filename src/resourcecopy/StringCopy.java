package resourcecopy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * string的源码重写
 * @author demo
 */
@SuppressWarnings("没用")
public class StringCopy implements Serializable,Comparable<String> {
    /**
     * string的主体
     */
    @SuppressWarnings("要求不赋值")
    private final char value[] ;

    public static void main(String[] args) {
        String s = "0123455";

        System.out.println(s.indexOf("12"));
        System.out.println(s.startsWith("23",2));

    }

    public StringCopy(char[] chars){

        this.value = Arrays.copyOf(chars,chars.length);

    }

    public StringCopy() {
        this.value = new char[20];
    }
    public StringCopy(char[] chars,int offset,int count){
        if(offset<0||count<0){
            throw new StringIndexOutOfBoundsException(offset+""+count);
        }
        if(count == 0){
            this.value = new char[20];
            return;
        }

        this.value = Arrays.copyOfRange(chars,offset,offset+count);
    }


    public char charAt(int index){
        if(index<0 || index > value.length)
        {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }


    @Override
    public int compareTo(String o) {

        return 0;
    }
}
