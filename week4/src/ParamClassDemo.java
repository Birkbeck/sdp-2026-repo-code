
// parameterised classes
class ParamClass0<T> {

    T get() { return null; }

    void f(T t) {};

    // parameterised method
    // (a parameterised method can also be declared in a non-parametrised class)
    <TT> void f2(TT b) {}
}

public class ParamClassDemo {
    public static void main() {
        ParamClass0<String> s = new ParamClass0<>();
        s.f("String");
        //s.f(1); does not compile
        s.f2(1); // TT is Integer
        s.f2("String"); // TT is String
        s.f2(s); // TT is ParamClass0<String>
    }
}