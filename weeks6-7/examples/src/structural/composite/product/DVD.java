package structural.composite.product;

public class DVD extends Product {
    private final String title;

    public DVD(String title, float price) {
        super(price);
        this.title = title;
    }
}
