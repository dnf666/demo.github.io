package javaprogramfacetest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author demo
 */
public class ReflectTest {
    public static void main(String[] args) {
        try {
            Class<program2> program2Class = (Class<program2>) Class.forName("javaprogramfacetest.program2");
            System.out.println(program2Class.getName());
          Field[] fields = program2Class.getFields();
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getName());
            }
            Method[] method = program2Class.getMethods();
            for (Method method1:method) {
                System.out.println(method1.getName());
                System.out.println(method1.getGenericReturnType());

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
