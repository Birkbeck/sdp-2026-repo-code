public class AccessModifierDemo {
    private int i;

    @Override
    public boolean equals(Object o) {
        if (o instanceof AccessModifierDemo other) {
            return this.i == other.i;
        }
        return false;
    }
}

class SubAccessModifierDemo extends AccessModifierDemo {
}