package dev.krzysztoffurtak.jcombi;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

public class CombinationsWithRepetition<T> extends Combinations<T> {
    CombinationsWithRepetition(int n, int k, Function<int[], T> toCombinationFunc) {
        super(n, k, toCombinationFunc);
    }

    public CombinationsWithoutRepetition<T> withoutRepetition() {
        return new CombinationsWithoutRepetition<>(n, k, toCombinationFunc);
    }

    @Override
    public long count() {
        return empty() ? 0 : Combinatorics.binomial(n + k - 1, k);
    }

    @Override
    protected Iterator<T> newIterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        private final int[] index;
        private boolean nextAvailable;

        Iter() {
            // TODO: Make it size of k
            this.index = new int[k + 1];
            Arrays.fill(this.index, 0);
            index[k] = n - 1;
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

            while (index[i] == index[i + 1]) {
                i--;
                if (i < 0) {
                    nextAvailable = false;
                    break;
                }
            }

            if (nextAvailable) {
                index[i]++;

                for (; i < k - 1; i++) {
                    index[i + 1] = index[i];
                }
            }

            return combination;
        }
    }
}
