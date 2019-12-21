package ua.edu.ucu;

import ua.edu.ucu.stream.*;

public class StreamApp {

    public static int streamOperations(IntStream intStream) {
        //IntStream intStream = AsIntStream.of(-1, 0, 1, 2, 3); // input values
        int res = intStream
                .filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // 0, 1, 2, 3, 4, 5, 8, 9, 10

                .reduce(0, (sum, x) -> sum += x); // 42
        
        return res;
    }

    public static int[] streamToArray(IntStream intStream) {        
        int[] intArr = intStream.toArray();
        return intArr;
    }

    public static String streamForEach(IntStream intStream) {        
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        return str.toString();
    }

    public static void main(String[] args) {
        IntStream intStream = AsIntStream.of( 2 , 3, 4);
        intStream.forEach(b -> System.out.println(b));

        System.out.println("Flatmap");
        IntStream nw = intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        nw.forEach(b -> System.out.println(b));
        System.out.println(nw.toArray());


        System.out.println("Filter");
        IntStream res = intStream.filter(x -> x > 1);
        res.forEach(b -> System.out.println(b));
        int reduced = res.reduce(0, (sum, x) -> sum += x);
        System.out.println(reduced);

        IntStream mapped = res.map(x -> x*x);
        mapped.forEach(b -> System.out.println(b));

    }
}
