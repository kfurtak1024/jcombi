/*
 * Copyright (c) 2022 Krzysztof Furtak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.krzysztoffurtak.jcombi.variations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.IntStream;

import static dev.krzysztoffurtak.jcombi.Combinatorics.factorial;

public class VariationsWithoutRepetition<T> extends Variations<T> {

    public VariationsWithoutRepetition(int n, int k, Function<int[], T> variationsVisitor) {
        super(n, k, variationsVisitor);
        if (k > n) {
            throw new IllegalArgumentException("k must be less or equal to n");
        }
    }

    public VariationsWithRepetition<T> withRepetition() {
        return new VariationsWithRepetition<>(n, k, variationsVisitor);
    }

    @Override
    public long count() {
        return empty() ? 0 : factorial(n) / factorial(n - k);
    }

    @Override
    protected Iterator<T> newIterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        private final int[] index = IntStream.range(0, n).toArray();
        private boolean nextAvailable = true;

        Iter() {
        }

        @Override
        public boolean hasNext() {
            return nextAvailable;
        }

        @Override
        public T next() {
            // TODO: Get rid of Arrays.copyOf
            final T variation = variationsVisitor.apply(Arrays.copyOf(index, k));

            int tailmax = index[n - 1];
            int end = k;

            while ((end > 0) && (index[end - 1] >= tailmax)) {
                tailmax = index[--end];
            }

            if (end > 0) {
                final int i = index[end - 1];
                int j;

                if (i >= index[n - 1]) {
                    j = end;
                    while ((j + 1 < k) && (index[j + 1] > i)) {
                        j++;
                    }
                } else {
                    j = n - 1;
                    while ((j > k) && (index[j - 1] > i)) {
                        j--;
                    }
                }

                index[end - 1] = index[j];
                index[j] = i;

                flip(index, k, n);
                flip(index, end, n);
            } else {
                nextAvailable = false;
            }

            return variation;
        }
    }

    // TODO: Move it somewhere else
    private static void flip(int[] array, int i, int j) {
        for (; i + 1 < j; ++i, --j) {
            swap(array, i, j - 1);
        }
    }

    private static void swap(int[] array, int i, int j) {
        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
