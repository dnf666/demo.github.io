package AimToOffer.Singleton;

/**
 * 单例模式--多线程实现，但是效率不高
 * 使用同步锁，双重检验锁
 * @author demo
 */
public class Singletonmutiprocess {

     private static Singletonmutiprocess instance = null;

    private Singletonmutiprocess() {
    }

    public  static Singletonmutiprocess getInstance() {
        if(instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singletonmutiprocess();

                }
            }
        }
        return instance;
}}
