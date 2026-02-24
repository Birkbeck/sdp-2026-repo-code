void main() {
    int i, j, k;

    i = (j = (k = 0));

    int r = ((i + j) + k);

    k += 2;

    i += j += k += 2;

    f(i = 1);

    System.out.println(i + " " + j + " " + k);
}

static void f(int v) {
    IO.println(v);
}
