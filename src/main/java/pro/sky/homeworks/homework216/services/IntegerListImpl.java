package pro.sky.homeworks.homework216.services;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.stereotype.Service;
import pro.sky.homeworks.homework216.exceptions.ElementAlreadyExistsException;
import pro.sky.homeworks.homework216.exceptions.ElementNotFoundException;
import pro.sky.homeworks.homework216.exceptions.NullParameterException;
import pro.sky.homeworks.homework216.exceptions.OutOfBoundsException;

@Service
public class IntegerListImpl implements IntegerListService {
    public Integer[] list = new Integer[10];

    @Override
    public Integer add(Integer item) {
        int length = list.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (list[i] == null) {
                list[i] = item;
                count = i;
                break;
            }
        }
        if (count == length - 1) {
            grow();
        }
        return item;
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
        for (int i = 0; i < length; i++) {
            if (Objects.equals(list[i], item)) {
                list[i] = null;
                break;
            } else if (!Objects.equals(list[length - 1], item)) {
                throw new ElementNotFoundException("Элемент отсутсвует");
            }
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        int length = list.length;
        if (index > length || list[0] == null) {
            throw new ElementNotFoundException("Элемент отсутсвует");
        }
        Integer out = null;
        for (int i = 0; i < length; i++) {
            if (i == index) {
                out = list[i];
                list[i] = null;
            }
        }
        return out;
    }

    @Override
    public boolean contains(Integer item) {
        return binarySearch(item) != -1;
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
        if (list.length != otherList.length) {
            return false;
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
        int count = 0;
        for (Integer integer : list) {
            if (integer != null) {
                count++;
            }
        }
        return count;
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

    @Override
    public void mergeSort(Integer[] list) {
        if (list.length < 2) {
            return;
        }
        int mid = list.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[list.length - mid];

        System.arraycopy(list, 0, left, 0, left.length);
        System.arraycopy(list, mid, right, 0, right.length);

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    private void merge(Integer[] list, Integer[] left, Integer[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                list[mainP++] = left[leftP++];
            } else {
                list[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            list[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            list[mainP++] = right[rightP++];
        }
    }

    @Override
    public int binarySearch(Integer item) {
        int index = Integer.MAX_VALUE;
        int first = 0;
        int last = list.length - 1;
        mergeSort(list);

        while (first <= last) {
            int mid = (first + last) / 2;
            if (list[mid] < item) {
                first = mid + 1;
            } else if (list[mid] > item) {
                last = mid - 1;
            } else if (Objects.equals(list[mid], item)) {
               index = mid;
               break;
            }
        }
        return index;
    }

    private void grow() {
        int length = list.length;
        list = Arrays.copyOf(list, length * 3 / 2);
        for (int i = length; i < list.length; i++) {
            if (list[i] == 0) {
                list[i] = null;
            }
        }
    }
}

