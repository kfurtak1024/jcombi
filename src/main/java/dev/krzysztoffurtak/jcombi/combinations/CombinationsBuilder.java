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

import dev.krzysztoffurtak.jcombi.InputSetBuilder;

import java.util.List;

public interface CombinationsBuilder<T> extends InputSetBuilder<T, CombinationsBuilder.Builder<T>> {
    interface Builder<T> {
        CombinationsWithoutRepetition<List<T>> choose(int k);
    }

    static CombinationsWithoutRepetition<int[]> build(int n, int k) {
        return new CombinationsWithoutRepetition<>(n, k, InputSetBuilder.sequenceFromIndexes());
    }

    static <T> CombinationsBuilder<T> builder() {
        return inputSet -> (Builder<T>) k -> new CombinationsWithoutRepetition<>(
                inputSet.size(), k, InputSetBuilder.sequenceFromInputSet(inputSet));
    }
}
