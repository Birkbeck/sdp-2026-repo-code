package creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Prototype implements Cloneable {
    private List<Prototype> list = new ArrayList<>();

    // Note that class Object contains the following declaration
    // protected Object clone() throws CloneNotSupportedException

    // In the declaration below we
    //    - make the method more visible (public)
    //    - remove some of the checked exceptions from the throws clause (CloneNotSupportedException)
    //    - narrow the return type (from Object to creational.prototype.Prototype)
    // All of these language features support the creational.prototype.Prototype pattern

    @Override
    public Prototype clone() {
        try {
            Prototype clone = (Prototype) super.clone();
            clone.list = new ArrayList<>(list);
            return clone;
        }
        catch (CloneNotSupportedException e) {
            // the class is Cloneable, so this exception cannot be thrown
            throw new AssertionError("this should never happen");
        }
    }
}
