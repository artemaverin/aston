package ru.aston.list;

/**
 * Параметризованный интерфейс содержит CRUD методы для работы с коллекцией
 * @param <T>
 */
public interface SimpleArraylist<T> extends Iterable<T> {
    void add(T value);
    void add(int index, T value);
    void remove(int index);
    T get(int index);
    void set(int index, T newValue);
    int size();
    void clear();
}
