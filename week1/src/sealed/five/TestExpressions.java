package sealed.five;

sealed interface Expr
        permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {
}

public class TestExpressions {
    public static void main(String[] args) {
        //(6 + 7) * -8
        System.out.println(eval(
                new TimesExpr(
                        new PlusExpr(
                                new ConstantExpr(6),
                                new ConstantExpr(7)
                        ),
                        new NegExpr(new ConstantExpr(8))
                )));
    }

    // from Java 21
    static int eval(Expr expr) {
        return switch (expr) {
            case ConstantExpr(var i) -> i;
            case PlusExpr(var a, var b) -> eval(a) + eval(b);
            case TimesExpr(var a, var b) -> eval(a) * eval(b);
            case NegExpr(var e) -> - eval(e);
            // no need for default as Expr is sealed - exhaustiveness

            // will throw a NullPointerException if expr is null
            // case null -> ... can be used to handle the case
        };
    }
}


record ConstantExpr(int i) implements Expr {
}

record PlusExpr(Expr a, Expr b) implements Expr {
}

record TimesExpr(Expr a, Expr b) implements Expr {
}

record NegExpr(Expr e) implements Expr {
}