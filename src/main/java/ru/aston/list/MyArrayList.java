package ru.aston.list;

import java.util.*;

/**
 * Класс описывает структуру данных основанной на массивах
 * @author Artem Averin
 * @version 1.0
 */

public class MyArrayList<T> implements SimpleArraylist<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] data;
    private int modCount;

    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.data = (T[]) new Object[capacity];
    }

    private void expand() {
        if (data.length == 0) {
            data = (T[]) new Object[DEFAULT_CAPACITY];
        }
        if (size >= data.length) {
            this.data = Arrays.copyOf(data, data.length * 2);
        }
    }

    /**
     * Добавление значения в коллекцию (по умолчанию в конец)
     * @param value - элемент
     */
    public void add(T value) {
        expand();
        data[size++] = value;
        modCount++;
    }

    /**
     * Добавление значения в коллекцию по индексу
     * @param index - индекс
     * @param value - элемент
     */
    @Override
    public void add(int index, T value) {
        expand();
        Objects.checkIndex(index, size);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
        modCount++;
    }

    /**
     * Получение элемента по индексу
     * @param index - индекс
     * @return возвращает элемент
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    /**
     * Обновляет значение по индексу
     * @param index - индекс
     * @param newValue - новый элемент
     */
    @Override
    public void set(int index, T newValue) {
        Objects.checkIndex(index, size);
        data[index] = newValue;
    }

    /**
     *
     * @return возвращает размер коллекции
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Удаление элемента по индексу
     * @param index - индекс
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        modCount++;
    }

    /**
     * Метод очищает всю коллекцию
     */
    public void clear() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "data=" + Arrays.toString(Arrays.copyOfRange(data, 0, size)) +
                '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int count;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[count++];
            }
        };
    }
}
