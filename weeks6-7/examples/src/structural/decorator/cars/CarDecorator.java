package structural.decorator.cars;

public class CarDecorator implements Car {
    protected final Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        car.assemble();
    }
}