public class Main {
    public static final int buyer_limit = 10;

    public static void main(String[] args) {
        final Dealership dealership = new Dealership();
        new Thread(null, dealership::productionCar, "Производитель ").start();
        for (int i=0; i<buyer_limit; i++) {
            new Thread(null, dealership::releaseCar, "Покупатель " + (i + 1)).start();
        }

//        new Thread(null, dealership::productionCar, "Производитель ").start();
    }
}
