package com.foxkiev.app.utils;

import android.text.TextUtils;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();

    private final T value;

    private Optional() {
        this.value = null;
    }

    public static<T> Optional<T> empty() {
        @SuppressWarnings("unchecked")
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }


    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static <T extends String> Optional<T> ofEmptyString(T value) {
        return TextUtils.isEmpty(value) ? empty() : of(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }


    public T orElse(T other) {
        return value != null ? value : other;
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
