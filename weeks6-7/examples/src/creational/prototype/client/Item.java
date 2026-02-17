package creational.prototype.client;

//Prototype

public abstract class Item implements Cloneable {
    private String title;
    private double price;

    protected Item(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public Item clone() {
        try {
            // use default object clone:
            //   clonedItem is a Book, if this is a Book
            //   clonedItem is a CD, if this is a CD
            Item clonedItem = (Item) super.clone();
            // specialised clone
            clonedItem.setPrice(price);
            clonedItem.setTitle(title);
            return clonedItem;
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError("impossible");
        }
    }

    public void setTitle(String title) {
        System.out.println("setting the title");
        this.title = title;
    }

    public void setPrice(double price) {
        System.out.println("setting price");
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}

//Concrete Prototypes

class Book extends Item {
    public Book(String title, double price) {
        super(title, price);
    }
    //extra book stuff
}

class CD extends Item {
    public CD(String title, double price) {
        super(title, price);
    }
    //extra cd stuff
}
