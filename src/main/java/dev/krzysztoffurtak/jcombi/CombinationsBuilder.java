package dev.krzysztoffurtak.jcombi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CombinationsBuilder<T> extends InputSetBuilder<T, CombinationsBuilder.Builder<T>> {
    interface Builder<T> {
        Combinations<List<T>> choose(int k);
    }

    static Combinations<int[]> build(int n, int k) {
        return new Combinations<>(n, k, index -> Arrays.copyOf(index, index.length - 1));
    }

    static <T> CombinationsBuilder<T> builder() {
        return inputSet -> (Builder<T>) k -> new Combinations<>(inputSet.size(), k, index -> Arrays.stream(index)
                .limit(index.length - 1)
                .mapToObj(inputSet::get)
                .collect(Collectors.toUnmodifiableList()));
    }
}
