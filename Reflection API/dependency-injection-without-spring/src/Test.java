import java.lang.reflect.Field;

public class Test {
    public static void main( String[] args) throws Exception {

        Class<Car> carClass = Car.class;
        Class<Engine> engineClass = Engine.class;

        Car car = carClass.getDeclaredConstructor().newInstance();
        Engine engine = engineClass.getDeclaredConstructor().newInstance();

        Field field = carClass.getDeclaredField("engine");
        field.setAccessible(true);
        field.set(car,engine);

        car.drive();
    }
}
