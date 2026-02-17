package structural.composite.product;

import java.util.List;

public class ProductGroup extends Product {

    private final List<Product> products;
    private double discount;

    public ProductGroup(List<Product> products, double discount) {
        super(0); // Have to set something, but we don't use price so the value is irrelevant
        this.products = products;
        this.discount = discount;
    }

    // Override this so it computes the discounted price
    @Override
    public double getPrice() {
        double price = 0;
        for (Product p : products)
            price += p.getPrice();

        return price * discount;
    }

}
