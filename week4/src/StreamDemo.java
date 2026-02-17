import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Pair<T>(T a, T b) {
    static <T> Pair<T> of(T a, T b) {
        return new Pair<>(a, b);
    }
}

public class StreamDemo {

    public static void main(String... args) {
        parallelStreamDontDo();
    }

    static void streamExamples() {
        List<Dish> list = Dish.getMenu();

        list.stream()
                .map(Dish::name)
                .forEach(System.out::println);

        // are variables stream0 and stream1 useful?
        Stream<Dish> stream0 = list.stream();
        Stream<String> stream1 = stream0.map(d -> d.name());
        stream1.forEach(n -> System.out.println(n));

        // this loop produces the same results as the Stream API version above
        for (var d: list) {
            var n = d.name();
            System.out.println(n);
        }

        List<Dish> vd = list.stream()
                .filter(Dish::vegetarian)
                .toList();
        System.out.println(vd);

        // this loop produces the same results as the Stream API version above
        List<Dish> vda = new ArrayList<>();
        for (var d: list) {
            if (d.vegetarian()) {
                vda.add(d);
            }
        }
        System.out.println(vda);

        List<Integer> cl = list.stream()
                .filter(Dish::vegetarian)
                .map(Dish::calories)
                .toList();
        System.out.println(cl);

        // this loop produces the same results as the Stream API version above
        List<Integer> cla = new ArrayList<>();
        for (var d: list) {
            if (d.vegetarian()) {
                var c = d.calories();
                cla.add(c);
            }
        }
        System.out.println(cla);

        // now, try writing something like this using loops
        List<Dish> cl2 = list.stream()
                .filter(Dish::vegetarian)
                .sorted(Comparator.comparingInt(Dish::calories).reversed())
                .limit(2)
                .toList();
        System.out.println(cl2);


        List<Pair<Dish>> list3 = list.stream()
                .filter(d1 -> d1.vegetarian())
                .flatMap(d1 -> list.stream()
                        .filter(d2 -> d2.calories() < 500)
                        .map(d2 -> Pair.of(d1, d2)))
                .toList();
        System.out.println(list3);

        // this loop produces the same results as the Stream API version above
        List<Pair<Dish>> list3a = new ArrayList<>();
        for (Dish d1 : list) {
            if (d1.vegetarian()) {
                for (Dish d2 : list) {
                    if(d2.calories() < 500)
                        list3a.add(Pair.of(d1, d2));
                }
            }
        }
        System.out.println(list3a);


        List<Pair<Dish>> list4 = list.stream()
                .flatMap(d1 -> list.stream()
                        .filter(Dish::vegetarian)
                        .map(d2 -> Pair.of(d1, d2)))
                .distinct()
                .toList();
        System.out.println(list4);

        // this loop produces the same results as the Stream API version above
        // (note how distinct() makes the code with loops more complicated
        Set<Pair<Dish>> set4a = new HashSet<>();
        for (Dish d1 : list) {
            for (Dish d2 : list) {
                if (d2.vegetarian()) {
                    set4a.add(Pair.of(d1, d2));
                }
            }
        }
        List<Pair<Dish>> list4a = new ArrayList<>(set4a);
        System.out.println(list4a);


        // peek can be used for debugging streams
        Optional<Dish> e = list.stream()
                .peek(d -> System.out.println("before filter " + d))
                .filter(Dish::vegetarian)
                .peek(d -> System.out.println("after filter " + d))
                .filter(d -> d.name().length() < 5)
                .peek(d -> System.out.println("after second filter " + d))
                .findFirst();

        e.ifPresent(s -> System.out.println(s));


        // is this a good example of using Stream API?
        List<Dish> r = new ArrayList<>();
        list.stream()
                .filter(Dish::vegetarian)
                .forEach(d -> r.add(d));
        System.out.println(r);

        // different examples of reduce and collect
        // note mapToInt instead of map,
        // which would create Stream<Integer> (very inefficient)
        OptionalInt c1 = list.stream()
                .mapToInt(Dish::calories)
                .reduce(Integer::max);

        int c2 = list.stream()
                .mapToInt(Dish::calories)
                .reduce(0, Integer::sum);

        String s = list.stream()
                .map(Dish::name)
                .reduce("", String::concat); // very inefficient

        String s2 = list.stream()
                .map(Dish::name)
                .collect(Collectors.joining());

        int c3 = list.stream()
                .map(Dish::calories)
                .parallel()
                .reduce(0, Integer::sum, Integer::sum);
    }

    // accumulator and reducer are both associative but not necessarily commutative
    // associativity: a + b + c = (a + b) + c = a + (b + c) -
    // commutativity: a + b = b + a
    static void parallelStreams() {
        System.out.println(
                Stream.iterate(0, i -> i + 1)
                        .map(i -> "" + i)
                        .limit(102400)
                        .parallel()
                        .reduce(
                                "",
                                (r, e) -> r + e + "/",
                                (r1, r2) -> r1 + "\n" + r2));
    }

    static void parallelStreamDontDo() {
        List<String> list = new ArrayList<>();
        int c = 0;
        int s = Stream.iterate(0, i -> i + 1)
                .map(i -> "" + i)
                .limit(102400)
                .parallel()
                .collect(Collectors.toList()).size();
                //.forEach(e -> list.add(e));
        System.out.println(s);
    }
}
