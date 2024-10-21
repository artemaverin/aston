package ru.aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.list.MyArrayList;
import ru.aston.list.SimpleArraylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;

class MyArrayListTest {

    private SimpleArraylist<Integer> list;

    @BeforeEach
    public void initData() {
        list = new MyArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void whenAddThenSizeIncrease() {
        list.add(4);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void whenAddByIndexThenSizeIncrease() {
        list.add(0, 4);
        assertThat(list.get(0)).isEqualTo(4);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void whenAddNullThenMustBeSameBehavior() {
        list = new MyArrayList<>(3);
        list.add(null);
        list.add(null);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isNull();
        assertThat(list.get(1)).isNull();
    }

    @Test
    void whenSetNewValue() {
        list.set(1, 123);
        assertThat(list.get(1)).isEqualTo(123);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void whenClearCollectionThenSizeIsZero() {
        list.clear();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void whenRemoveThenCheckSizeDecrease() {
        list.remove(1);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void whenGetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new MyArrayList<>(5);
        assertThat(list.iterator().hasNext()).isFalse();
    }

    @Test
    void checkIterator() {
        assertThat(list.size()).isEqualTo(3);
        assertThat(list).hasSize(3);
    }

    @Test
    void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(  ConcurrentModificationException.class);
    }

}