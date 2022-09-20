package pro.sky.homeworks.homework216;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.homeworks.homework216.exceptions.ElementNotFoundException;
import pro.sky.homeworks.homework216.services.IntegerListService;
import pro.sky.homeworks.homework216.services.IntegerListImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IntegerListImplTest {
    final Integer item = 100000;
    final int index = 0;
    final int minus = -1;
    IntegerListService integerList = new IntegerListImpl();

    @Test
    void shouldAddItemToArray() {
        assertEquals(integerList.add(item), item);
    }

    @Test
    void shouldAddItemToArrayByIndex() {
        assertEquals(integerList.add(index, item), item);
    }

    @Test
    void shouldSetItemToArray() {
        assertEquals(integerList.set(index, item), item);
    }

    @Test
    void shouldRemoveItemFromArray() {
        integerList.add(item);
        assertEquals(integerList.remove(item), item);
    }
    @Test
    void shouldThrowNotFoundExceptionWhenRemoveItemFromArray() {
        Throwable thrown = catchThrowable(() -> integerList.remove(item));
        assertThat(thrown).isInstanceOf(ElementNotFoundException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }


    @Test
    void shouldRemoveItemByIndexFromArray() {
        integerList.add(item);
        assertEquals(integerList.remove(index), item);
    }
    @Test
    void shouldThrowOutOfBoundsExceptionWhenRemoveItemFromArrayByIndex() {
        Throwable thrown = catchThrowable(() -> integerList.remove(integerList.size()));
        assertThat(thrown).isInstanceOf(ElementNotFoundException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }
    @Test
    void shouldFindItemInArray() {
        integerList.add(item);
        assertTrue(integerList.contains(item));
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
        assertFalse(integerList.equals(check));
    }
}