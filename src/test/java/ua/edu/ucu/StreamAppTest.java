package ua.edu.ucu;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import java.util.Arrays;

/**
 * @author andrii
 */
public class StreamAppTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamAverage() {
        int[] arr = {-1, 0, 1, 2, 3};
        double expResult = 1.0;
        double result = AsIntStream.of(arr).average();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testStreamMax() {
        int[] arr = {-1, 0, 1, 2, 3};
        int expResult = 3;
        int result = AsIntStream.of(arr).max();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamMin() {
        int[] arr = {-1, 0, 1, 2, 3};
        int expResult = -1;
        int result = AsIntStream.of(arr).min();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamCount() {
        int[] list = {-1, 0, 1, 2, 3};
        long expResult = 5;
        long result = AsIntStream.of(list).count();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamSum() {
        int[] list = {-1, 0, 1, 2, 3};
        int expResult = 5;
        long result = AsIntStream.of(list).sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamFilter() {
        int[] list = {-1, 0, 1, 2, 3};
        IntPredicate newStream = value -> (value > 1);
        String expResult = "[2, 3]";
        String result = Arrays.toString(AsIntStream.of(list).filter(newStream).toArray());
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamMap() {
        int[] newStr = {-1, 1, 2, 3, 4, 5};
        IntUnaryOperator operation = x -> x * x;
        String expResult = "[1, 1, 4, 9, 16, 25]";
        String result = Arrays.toString(AsIntStream.of(newStr).map(operation).toArray());
//        System.out.println(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamFlatMap() {

        int[] expResult = {-1, 0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5};

        int[] result = intStream.flatMap(x -> AsIntStream.of(x, x + 1, x + 2)).toArray();
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testStreamReduce() {
        int expResult = 5;

        int result = intStream.reduce(0, (x, sum) -> sum += x);
        assertEquals(expResult, result);
    }

}
