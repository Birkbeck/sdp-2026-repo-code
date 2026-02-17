package creational.prototype.client;

//Prototype

public abstract class Item implements Cloneable {
    private String title;
    private double price;

    @Override
    public Item clone() {
        try {
            //use default object clone.
            Item clonedItem = (Item) super.clone();
            //specialised clone
            clonedItem.setPrice(price);
            clonedItem.setTitle(title);
            return clonedItem;
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError("impossible");
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

//Concrete Prototypes

class Book extends Item {
    //extra book stuff
}

class CD extends Item {
    //extra cd stuff
}
