package ru.aston.list;

import java.util.Comparator;

/**
 * Класс сортирует коллекцию способом "quick sort" в соответствии с переданным компаратором
 */
public class QuickSort {

    /**
     * Публичный метод
     * @param sequence - коллекция для сортировки
     * @param comparator - компаратор
     * @param <T> - параметризация
     */
    public static <T> void quickSort(SimpleArraylist<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(SimpleArraylist<T> sequence, int from, int to, Comparator<T> comparator) {
        if (from < to) {
            int divideIndex = partition(sequence, from, to, comparator);
            quickSort(sequence, from, divideIndex - 1, comparator);
            quickSort(sequence, divideIndex, to, comparator);
        }
    }

    private static <T> int partition(SimpleArraylist<T> sequence, int from, int to, Comparator<T> comparator) {
        int leftIndex = from;
        int rightIndex = to;
        T pivot = sequence.get(from);;
        while (leftIndex <= rightIndex) {
            while (comparator.compare(sequence.get(leftIndex), pivot) < 0) {
                leftIndex++;
            }
            while (comparator.compare(sequence.get(rightIndex), pivot) > 0) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                swap(sequence, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static <T> void swap(SimpleArraylist<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
