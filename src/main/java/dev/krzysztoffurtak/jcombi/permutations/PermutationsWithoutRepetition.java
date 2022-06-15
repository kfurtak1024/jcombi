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
package dev.krzysztoffurtak.jcombi.permutations;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static dev.krzysztoffurtak.jcombi.Combinatorics.factorial;
import static java.util.Collections.emptyIterator;

public class PermutationsWithoutRepetition<T> implements Iterable<T> {
    private final int n;
    private final Function<int[], T> permutationsVisitor;

    public PermutationsWithoutRepetition(int n, Function<int[], T> permutationsVisitor) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater of equal to zero");
        }
        if (permutationsVisitor == null) {
            throw new IllegalArgumentException("permutationsVisitor cannot be null");
        }

        this.n = n;
        this.permutationsVisitor = permutationsVisitor;
    }

    /**
     * Returns the size of the input set from which permutations are selected.
     *
     * @return size of the input set
     */
    public int n() {
        return n;
    }

    public long count() {
        return empty() ? 0 : factorial(n);
    }
    /**
     * Returns a sequential {@code Stream} with all permutations of this as its source.
     *
     * @return sequential {@code Stream} over all permutations
     */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns an iterator over permutations of type {@code T}.
     *
     * @return an {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return empty() ? emptyIterator() : new Iter();
    }

    private boolean empty() {
        return n == 0;
    }

    private class Iter implements Iterator<T> {
        private final int[] index = IntStream.range(0, n).toArray();
        private boolean nextAvailable = true;

        @Override
        public boolean hasNext() {
            return nextAvailable;
        }

        @Override
        public T next() {
            final T permutation = permutationsVisitor.apply(index);

            int i = n - 1;
            while ((i > 0) && (index[i - 1] >= index[i])) {
                i--;
            }

            if (i > 0) {
                int j = n;
                while (index[j - 1] <= index[i - 1]) {
                    j--;
                }

                swap(index, i - 1, j - 1);

                i++;
                j = n;
                while (i < j) {
                    swap(index, i - 1, j - 1);
                    i++;
                    j--;
                }
            } else {
                nextAvailable = false;
            }

            return permutation;
        }
    }

    private static void swap(int[] array, int i, int j) {
        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
