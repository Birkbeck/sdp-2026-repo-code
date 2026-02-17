import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class WrapperTypes {
    void main() {
        int i1 = 2;
        int i2 = 3;
        int i = sum(i1, i2); // all primitive (32-bit ints)

        List<Integer> list = List.of(2, 3); // a list with two objects
        int i3 = sumInteger(list.get(0), list.get(1)); // auto-unboxing to store an Integer in i3

        Integer o = list.get(0) + list.get(1); // auto-unboxing to perform +, followed by auto-boxing to store the result in o
        int i4 = list.get(0) + list.get(1); // auto-unboxing to perform +; the result is simply stored in i4

        BinaryOperator<Integer> binOpInteger = WrapperTypes::sumInteger; // no auto-boxing/unboxing if everything is Integer (but the method still goes through auto-unboxing/boxing to do +)
        BinaryOperator<Integer> binOpInt = WrapperTypes::sum; // extra auto-boxing/unboxing around sum
        IntBinaryOperator intBinOp = WrapperTypes::sum; // no auto-boxing/unboxing if everything is int
        IntBinaryOperator intBinOpInteger = WrapperTypes::sumInteger; // extra auto-boxing/unboxing around sumInteger and also auto-boxing/unboxing in sumInteger for +

        int r = binOpInteger.apply(i1, i2); // arguments boxed and the result unboxed
        int rb = intBinOp.applyAsInt(i1, i2); // no boxing and unboxing
        int rb2 = intBinOpInteger.applyAsInt(i1, i2); // no boxing and unboxing on the surface, but needed to call sum

        int r1 = binOpInteger.apply(list.get(0), list.get(1)); //
        int rb1 = intBinOp.applyAsInt(list.get(0), list.get(1)); // no boxing and unboxing
        int rbb2 = intBinOpInteger.applyAsInt(list.get(0), list.get(1)); // no boxing and unboxing
    }

    static Integer sumInteger(Integer a, Integer b) {
        return a + b;
    }

    static int sum(int a, int b) {
        return a + b;
    }
}
