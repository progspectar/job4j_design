package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = indexForKey(key);
        boolean res = (table[i] == null);
        if (res) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            res = true;
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = capacity << 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : oldTable) {
            if (el != null) {
                newTable[indexForKey(el.key)] = el;
            }
            table = newTable;
        }
    }

    boolean areKeysEqual(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2)
                && Objects.equals(key1, key2);
    }

    @Override
    public V get(K key) {
        int i = indexForKey(key);
        V res = null;
        if (table[i] != null && areKeysEqual(table[i].key, key)) {
            res = table[i].value;
        }
        return res;
    }

    @Override
    public boolean remove(K key) {
        int i = indexForKey(key);
        boolean res = false;
        if (table[i] != null && areKeysEqual(table[i].key, key)) {
            table[i] = null;
            count++;
            modCount++;
            res = true;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {

        Iterator<K> it = new Iterator<K>() {

            private int index = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean res = false;
                if (count != 0) {
                    while (index < capacity && table[index] == null) {
                        index++;
                    }
                    res = index < capacity;
                }
                return res;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[index++].key;
            }
        };
        return it;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}