package me.ruslan.task3.math_eval;

import java.io.Serializable;
import java.util.Objects;

public class Pair<K, V> implements Serializable {
    private final K key;
    private final V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Pair(%s=%s)", key, value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (key != null ? key.hashCode() : 0);
        hash = 31 * hash + (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (!Objects.equals(key, pair.key))
                return false;
            return Objects.equals(value, pair.value);
        }
        return false;
    }
}
