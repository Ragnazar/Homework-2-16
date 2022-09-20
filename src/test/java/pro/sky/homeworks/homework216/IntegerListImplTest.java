package pro.sky.homeworks.homework216;

import org.junit.jupiter.api.Test;
import pro.sky.homeworks.homework216.services.IntegerList;
import pro.sky.homeworks.homework216.services.IntegerListImpl;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    final Integer item = 100000;
    final int index = 0;
    final int minus = -1;
    IntegerList integerList = new IntegerListImpl();

    @Test
    void shouldAddStringToArray() {
        assertEquals(integerList.add(item), item);
    }

    @Test
    void shouldAddStringToArrayByIndex() {
        assertEquals(integerList.add(index, item), item);
    }


    @Test
    void shouldSetStringToArray() {
        assertEquals(integerList.set(index, item), item);
    }


    @Test
    void shouldRemoveItemFromArray() {
        integerList.add(item);
        assertEquals(integerList.remove(item), item);

    }


    @Test
    void shouldRemoveItemByIndexFromArray() {
        integerList.add(item);
        assertEquals(integerList.remove(index), item);
    }


    @Test
    void shouldFindItemInArray() {
        integerList.add(item);
        assertTrue(integerList.contains(item));
    }

    @Test
    void shouldReturnFalseIfCantFindItem() {
        assertFalse(integerList.contains(item));
    }

    @Test
    void shouldReturnIndexOfItemCorrectly() {
        integerList.add(item);
        integerList.add(item);
        assertEquals(integerList.indexOf(item), index);
    }

    @Test
    void shouldReturnMinusOneForIndexOf() {
        assertEquals(integerList.indexOf(item), minus);
    }

    @Test
    void shouldReturnLastIndexOfItemCorrectly() {
        integerList.add(item);
        integerList.add(item);
        assertEquals(integerList.lastIndexOf(item), index + 1);
    }

    @Test
    void shouldReturnMinusOneForLastIndexOf() {
        assertEquals(integerList.lastIndexOf(item), minus);
    }

    @Test
    void shouldGetItemCorrectly() {
        integerList.add(item);
        assertEquals(integerList.get(index), item);
    }


    @Test
    void shouldShowSizeCorrectly() {
        integerList.add(item);
        integerList.add(item);
        assertEquals(integerList.size(), 2);
    }

    @Test
    void shouldShowThatArrayIsEmpty() {
        assertTrue(integerList.isEmpty());
    }


    @Test
    void shouldClearArray() {
        integerList.add(item);
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    void shouldCheckEquality() {
        integerList.add(item);
        Integer[] check = {item};
        assertTrue(integerList.equals(check));
    }

    @Test
    void swapSort() {
        integerList.swapSort(generateRandomArray());

    }

    @Test
    void binarySearch() {
        Integer[] arr = generateRandomArray();
        integerList.swapSort(arr);
        integerList.binarySearch(arr, item);
    }

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }
}