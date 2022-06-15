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

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyIterator;

public abstract class Variations<T> implements Iterable<T> {
    protected final int n;
    protected final int k;
    protected final Function<int[], T> variationsVisitor;

    protected Variations(int n, int k, Function<int[], T> variationsVisitor) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater of equal to zero");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k must be greater of equal to zero");
        }
        if (variationsVisitor == null) {
            throw new IllegalArgumentException("variationsVisitor cannot be null");
        }

        this.n = n;
        this.k = k;
        this.variationsVisitor = variationsVisitor;
    }

    /**
     * Returns the size of the input set from which variations are selected.
     *
     * @return size of the input set
     */
    public int n() {
        return n;
    }

    /**
     * Returns the size of the variations to be enumerated.
     *
     * @return size of the variation
     */
    public int k() {
        return k;
    }

    public abstract long count();

    /**
     * Returns a sequential {@code Stream} with all variations of this as its source.
     *
     * @return sequential {@code Stream} over all variations
     */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns an iterator over variations of type {@code T}.
     *
     * @return an {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return empty() ? emptyIterator() : newIterator();
    }

    protected boolean empty() {
        return n == 0 || k == 0;
    }

    protected abstract Iterator<T> newIterator();
}
