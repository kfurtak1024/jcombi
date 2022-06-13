package dev.krzysztoffurtak.jcombi.combinations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CombinationVisitorRecorder<T> implements Function<int[], T> {
    private final List<int[]> recordedCombinations;

    public CombinationVisitorRecorder() {
        this.recordedCombinations = new LinkedList<>();
    }

    @Override
    public T apply(int[] ints) {
        recordedCombinations.add(Arrays.copyOf(ints, ints.length - 1)); // TODO: -1 !!!!!!!!!!!!!
        return null;
    }

    public void verify(int[][] expectedCombinations) {
        assertThat(recordedCombinations.size()).isEqualTo(expectedCombinations.length);
        for (int i = 0; i < expectedCombinations.length; i++) {
            assertThat(recordedCombinations.get(i)).isEqualTo(expectedCombinations[i]);
        }
    }
}

