package pro.sky.homeworks.homework216.services;

import java.util.Arrays;
import java.util.Objects;

import pro.sky.homeworks.homework216.exceptions.ElementAlreadyExistsException;
import pro.sky.homeworks.homework216.exceptions.ElementNotFoundException;
import pro.sky.homeworks.homework216.exceptions.NullParameterException;
import pro.sky.homeworks.homework216.exceptions.OutOfBoundsException;

public class IntegerListImpl implements IntegerList {
    private Integer[] list = {2, 5, 7, 3, 10, 14, 25, 130, 11, 27};

    @Override
    public Integer add(Integer item) {
        int length = list.length;
        Integer[] result;
        if (list[0] == null) {
            result = new Integer[length];
            result[length - 1] = item;
        } else {
            result = new Integer[length + 1];
            System.arraycopy(list, 0, result, 0, length);
            result[length] = item;
        }
        list = result;
        return list[length - 1];
    }

    @Override
    public Integer add(int index, Integer item) {
        int length = list.length;
        if (index >= length || index < 0) {
            throw new OutOfBoundsException("индекс выходит за пределы фактического количества элементов массива");
        } else if (list[index] != null) {
            throw new ElementAlreadyExistsException("Элемент уже существует");
        }
        list[index] = item;
        return list[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        int length = list.length;
        if (index >= length || index < 0) {
            throw new OutOfBoundsException("индекс выходит за пределы фактического количества элементов массива");
        }
        list[index] = item;
        return list[index];
    }

    @Override
    public Integer remove(Integer item) {
        int length = list.length;
        Integer[] result = new Integer[length - 1];
        for (int i = 0; i < length; i++) {
            if (Objects.equals(list[i], item)) {
                list[i] = null;
            } else if (i == list.length - 1) {
                throw new ElementNotFoundException("Элемент отсутсвует");
            }
            if (list[i] != null) {
                result[i] = list[i];
            }
        }
        list = result;
        return item;
    }

    @Override
    public Integer remove(int index) {
        int length = list.length;
        Integer[] result = new Integer[length - 1];
        if (index > length || list[0] == null) {
            throw new ElementNotFoundException("Элемент отсутсвует");
        }
        Integer out = null;
        for (int i = 0; i < length; i++) {
            if (i == index) {
                out = list[i];
                list[i] = null;
            }
            if (list[i] != null) {
                result[i] = list[i];
            }
        }
        list = result;
        return out;
    }

    @Override
    public boolean contains(Integer item) {
        if (binarySearch(list, item) != -1) {
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(item, list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (Objects.equals(item, list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (list.length <= index) {
            throw new OutOfBoundsException("индекс выходит за пределы фактического количества элементов массива");
        }
        return list[index];
    }

    @Override
    public boolean equals(Integer[] otherList) {
        boolean isEqual = false;
        if (otherList == null) {
            throw new NullParameterException("Параметр не определен");
        }
        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(list[i], otherList[i])) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        return list[0] == null;
    }

    @Override
    public Integer[] toArray() {
        return (Integer[]) Arrays.stream(list).toArray();

    }

    @Override
    public void clear() {
        Arrays.fill(list, null);
    }


    private static void swap(Integer[] list, int idx1, int idx2) {
        int tmp = list[idx1];
        list[idx1] = list[idx2];
        list[idx2] = tmp;
    }


    public void swapSort(Integer[] list) {
        boolean needIter = true;
        while (needIter) {
            needIter = false;
            for (int i = 1; i < list.length; i++) {
                if (list[i] < list[i - 1]) {
                    swap(list, i, i - 1);
                    needIter = true;
                }
            }
        }
    }

    @Override
    public void selectionSort(Integer[] list) {
        for (int left = 0; left < list.length; left++) {
            int minIdx = left;
            for (int i = left; i < list.length; i++) {
                if (list[i] < list[minIdx]) {
                    minIdx = i;
                }
            }
            swap(list, left, minIdx);
        }
    }

    @Override
    public void insertionSort(Integer[] list) {
        for (int left = 0; left < list.length; left++) {
            Integer value = list[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < list[i]) {
                    list[i + 1] = list[i];
                } else {
                    break;
                }
            }
            list[i + 1] = value;
        }
    }

    @Override
    public int binarySearch(Integer[] list, Integer item) {
        int first = 0;
        int last = list.length - 1;

        while (first <= last) {
            int middle = (first + last) / 2;
            if (Objects.equals(list[middle], item)) {
                return middle;
            } else if (list[middle] < item) {
                first = middle + 1;
            } else if (list[middle] > item) {
                last = middle - 1;
            }
        }
        return -1;
    }
}

