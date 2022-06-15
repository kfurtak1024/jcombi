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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PermutationsBuilderTest {
    @Test
    @DisplayName("Verify permutations builder for generic type")
    void verifyPermutationsWithoutRepetitionBuilderForGenericType() {
        // Given
        final String[] inputSet = new String[] { "A", "B", "C" };

        // When
        final var permutations =
                PermutationsBuilder.builder().of(inputSet).withoutRepetition();

        // Then
        assertThat(permutations.n()).isEqualTo(inputSet.length);
        assertThat(permutations.count()).isEqualTo(6);
        assertThat(permutations).containsExactly(
                List.of("A", "B", "C"),
                List.of("A", "C", "B"),
                List.of("B", "A", "C"),
                List.of("B", "C", "A"),
                List.of("C", "A", "B"),
                List.of("C", "B", "A")
        );
    }

    @Test
    @DisplayName("Verify permutations builder for ints")
    void verifyPermutationsWithoutRepetitionBuilderForInts() {
        // Given
        final int n = 3;

        // When
        final var permutations = PermutationsBuilder.build(n);

        // Then
        assertThat(permutations.n()).isEqualTo(n);
        assertThat(permutations.count()).isEqualTo(6);
        assertThat(permutations).containsExactly(
                new int[] { 0, 1, 2 },
                new int[] { 0, 2, 1 },
                new int[] { 1, 0, 2 },
                new int[] { 1, 2, 0 },
                new int[] { 2, 0, 1 },
                new int[] { 2, 1, 0 }
        );
    }
}
