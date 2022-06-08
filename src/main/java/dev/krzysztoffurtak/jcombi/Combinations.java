package dev.krzysztoffurtak.jcombi;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyIterator;

public class Combinations<T> implements Iterable<T> {
    private final int n;
    private final int k;
    private final Function<int[], T> toCombinationFunc;

    Combinations(int n, int k, Function<int[], T> toCombinationFunc) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater of equal to zero");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k must be greater of equal to zero");
        }
        if (k > n) {
            throw new IllegalArgumentException("k must be less or equal to n");
        }

        this.n = n;
        this.k = k;
        this.toCombinationFunc = toCombinationFunc;
    }

    public int n() {
        return n;
    }

    public int k() {
        return k;
    }

    public long count() {
        return empty() ? 0 : Combinatorics.binomial(n, k);
    }

    // TODO: Implement spliterator

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<T> iterator() {
        return empty() ? emptyIterator() : new Iter();
    }

    private boolean empty() {
        return (n == 0) || (k == 0);
    }

    private class Iter implements Iterator<T> {
        private final int[] index;
        private boolean nextAvailable;

        Iter() {
            this.index = new int[k + 1];
            for (int i = 0; i < k; i++) {
                index[i] = i;
            }
            index[k] = n;
            nextAvailable = true;
        }

        @Override
        public boolean hasNext() {
            return nextAvailable;
        }

        @Override
        public T next() {
            int i = k - 1;
            final T combination = toCombinationFunc.apply(index);

            while (index[i] + 1 == index[i + 1]) {
                i--;
                if (i < 0) {
                    nextAvailable = false;
                    break;
                }
            }

            if (nextAvailable) {
                index[i]++;

                for (; i < k - 1; i++) {
                    index[i + 1] = index[i] + 1;
                }
            }

            return combination;
        }
    }
}
