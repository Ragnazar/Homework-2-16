package pro.sky.homeworks.homework216.services;

public interface IntegerList {
    Integer add(Integer item);

    Integer add(int index, Integer item);

    Integer set(int index, Integer item);

    Integer remove(Integer item);

    Integer remove(int index);

    boolean contains(Integer item);

    int indexOf(Integer item);

    int lastIndexOf(Integer item);

    Integer get(int index);

    boolean equals(Integer[] otherList);

    int size();

    boolean isEmpty();

    Integer[] toArray();

    void clear();

     void swapSort(Integer[] list);

    void selectionSort(Integer[] list);

    void insertionSort(Integer[] list);

    int binarySearch(Integer[] list, Integer item);
}
