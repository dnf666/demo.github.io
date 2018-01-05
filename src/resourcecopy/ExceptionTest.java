package resourcecopy;

public class ExceptionTest extends Exception {

    public ExceptionTest(){
        super();
    }
    public ExceptionTest(String s){
        super(s);
    }
}


class test{
    public static void main(String[] args) {


        try {
            throw new ExceptionTest("CUOLE");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
