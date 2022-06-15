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
package dev.krzysztoffurtak.jcombi.combinations;

import dev.krzysztoffurtak.jcombi.Combinatorics;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.IntStream;

public class CombinationsWithoutRepetition<T> extends Combinations<T> {

    public CombinationsWithoutRepetition(int n, int k, Function<int[], T> combinationsVisitor) {
        super(n, k, combinationsVisitor);
        if (k > n) {
            throw new IllegalArgumentException("k must be less or equal to n");
        }
    }

    public CombinationsWithRepetition<T> withRepetition() {
        return new CombinationsWithRepetition<>(n, k, combinationsVisitor);
    }

    @Override
    public long count() {
        return empty() ? 0 : Combinatorics.binomial(n, k);
    }

    @Override
    protected Iterator<T> newIterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        private final int[] index = IntStream.range(0, k).toArray();

        private boolean nextAvailable = true;

        @Override
        public boolean hasNext() {
            return nextAvailable;
        }

        @Override
        public T next() {
            final T combination = combinationsVisitor.apply(index);
            int i = k - 1;

            while (i >= 0 && indexAt(i) + 1 == indexAt(i + 1)) {
                i--;
            }

            if (i >= 0) {
                index[i]++;

                for (; i < k - 1; i++) {
                    index[i + 1] = index[i] + 1;
                }
            } else {
                nextAvailable = false;
            }

            return combination;
        }

        private int indexAt(int i) {
            return i < k ? index[i] : n;
        }
    }
}
