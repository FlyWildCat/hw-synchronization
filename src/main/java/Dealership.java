import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private final List<CarManufacturer> cars = new ArrayList<>();
    private static final int production_time = 2500;
    private static final int release_time = 2000;
    private static final int limit_car = 10;

    public void productionCar() {
        for (int i=0; i<limit_car; i++) {
            try {
                Thread.sleep(production_time);
                cars.add(new CarManufacturer());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");

                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void releaseCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (cars.size() == 0) {
                System.out.println("Машин нет.");
                wait();
            }
            Thread.sleep(release_time);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто.");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
