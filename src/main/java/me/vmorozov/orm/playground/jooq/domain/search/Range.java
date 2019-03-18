package me.vmorozov.orm.playground.jooq.domain.search;

public class Range<T> {

    private T min;
    private T max;

    public Range(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public Range<T> setMin(T min) {
        this.min = min;
        return this;
    }

    public T getMax() {
        return max;
    }

    public Range<T> setMax(T max) {
        this.max = max;
        return this;
    }
}
