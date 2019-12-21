package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;


public class AsIntStream implements IntStream {

    private ArrayList<Integer> arr;

    private AsIntStream(ArrayList<Integer> arrayList) {
        this.arr = arrayList;
    }

    private void checkEmptyness() {
        if (arr.isEmpty()) throw new IllegalArgumentException("Stream is empty.");
    }

    public static IntStream of(int... values) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i : values) {
            lst.add(i);
        }
        return new AsIntStream(lst);
    }


    @Override
    public Double average() {
        checkEmptyness();
        double result = sum() / count();
        return result;
    }

    @Override
    public Integer max() {
        checkEmptyness();
        int max = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        checkEmptyness();
        int min = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < min) {
                min = arr.get(i);
            }
        }
        return min;
    }

    @Override
    public long count() {
        return arr.size();
    }

    @Override
    public Integer sum() {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> newArr = new ArrayList<>();
        for (int el : arr) {
            if (predicate.test(el)) {
                newArr.add(el);
            }
        }
        return new AsIntStream(newArr);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int element : arr) {
            action.accept(element);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> newArr = new ArrayList<>();
        AsIntStream newStr = new AsIntStream(newArr);
        arr.forEach(el -> newStr.arr.add(mapper.apply(el)));
        return newStr;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {

        ArrayList<IntStream> streamsLst = new ArrayList<>();
        for (int i = 0; i < arr.size(); ++i) streamsLst.add(func.applyAsIntStream(arr.get((i))));

        ArrayList<Integer> newArr = new ArrayList<>();
        for (IntStream stream : streamsLst) {
            for (int i : stream.toArray()) {
                newArr.add(i);
            }
        }
        return new AsIntStream(newArr);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int i : arr) identity = op.apply(identity, i);
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] newArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            newArr[i] = arr.get(i);
        }
        return newArr;
    }

}
