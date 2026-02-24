void main() {
    int i = 1, j = 0;
    IO.println(f1(i, j));
    IO.println(f2(i, j));
    IO.println(f3(i, j));
}

// all 3 methods below have the same behaviour!

static int f1(int i, int j) {
    return i / j;
}

static int f2(int i, int j) {
    if (j == 0)
        throw new ArithmeticException("/ by zero");
    return i / j;
}

static int f3(int i, int j) {
    try {
        return i / j;
    }
    catch (ArithmeticException e) {
        throw new ArithmeticException("/ by zero");
    }
}
