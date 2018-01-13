package AimToOffer.Singleton;

/**
 * 面试第一题，实现一个单例模式
 */
public class SingletonOnlyprocess {
    /**
     * 单例的引用
     */
    private static SingletonOnlyprocess instance = null;

    private SingletonOnlyprocess() {
    }

    public static SingletonOnlyprocess getInstance() {
        if (instance == null) {
            instance = new SingletonOnlyprocess();

        }
        return instance;


    }


}
