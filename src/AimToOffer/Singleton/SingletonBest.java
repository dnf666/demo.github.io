package AimToOffer.Singleton;

/**
 * 最好的方法是使用静态构造函数，或按需创建实例
 * @author demo
 */
public class SingletonBest {
    /**
     * 使用静态构造函数
     */
    private static SingletonBest instance = new SingletonBest();

    private SingletonBest() {
    }

    public static SingletonBest getInstance(){
        return instance;
    }



}
