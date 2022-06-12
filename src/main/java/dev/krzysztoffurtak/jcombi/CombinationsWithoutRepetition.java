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
package dev.krzysztoffurtak.jcombi;

import java.util.Iterator;
import java.util.function.Function;

public class CombinationsWithoutRepetition<T> extends Combinations<T> {

    CombinationsWithoutRepetition(int n, int k, Function<int[], T> toCombinationFunc) {
        super(n, k, toCombinationFunc);
        if (k > n) {
            throw new IllegalArgumentException("k must be less or equal to n");
        }
    }

    public CombinationsWithRepetition<T> withRepetition() {
        return new CombinationsWithRepetition<>(n, k, toCombinationFunc);
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
