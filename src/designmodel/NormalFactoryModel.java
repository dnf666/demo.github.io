package designmodel;

import java.util.Optional;

/**
 * 普通工厂模式
 */
public class NormalFactoryModel {
    public static void main(String[] args) {
       car baoma = CarFactory.createCar(new baoma());
       car benchi = CarFactory.createCar(new Benchi());
       baoma.named("宝马");
       baoma.run();
       baoma.stop();
       benchi.named("奔驰");
       benchi.run();
       benchi.stop();
    }
}

/**
 * car interface
 */
interface car {
    //default method
    default void named(String name) {
        System.out.println("我叫" + name);
    }

    //car run
    void run();

    //car stop
    void stop();
}

/**
 * benchi class
 */
class Benchi implements car{


    @Override
    public void run() {
        System.out.println("奔驰发动了");
    }

    @Override
    public void stop() {
        System.out.println("奔驰停车");
    }
}

/**
 * baoma class
 */
class baoma implements car{

    @Override
    public void run() {
        System.out.println("宝马运行");
    }

    @Override
    public void stop() {
        System.out.println("宝马停下");

    }
}

/**
 * car factory
 */
class CarFactory{
   static car createCar(car car1){
        Optional<car> cars = Optional.of(car1);
        if(cars.isPresent())
        {
            return cars.get();
        }
        return null;
    }
}
