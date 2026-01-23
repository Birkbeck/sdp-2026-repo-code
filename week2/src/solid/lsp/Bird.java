package solid.lsp;

public abstract class Bird {
    public abstract void fly();
}

class Parrot extends Bird {
    @Override
    public void fly() { /* implementation */ }

    public void speak() { /* implementation */ }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }
}