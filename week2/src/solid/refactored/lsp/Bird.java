package solid.refactored.lsp;

public abstract class Bird {
}

abstract class FlyingBird extends Bird {
    public abstract void fly();
}

class Parrot extends FlyingBird {
    public void fly() { /* implementation */ }

    public void speak() { /* implementation */ }
}

class Penguin extends Bird {
}