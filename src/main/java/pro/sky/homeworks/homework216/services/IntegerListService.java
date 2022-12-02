package pro.sky.homeworks.homework216.services;

public interface IntegerListService {
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


    void mergeSort(Integer[] list);

    int binarySearch(Integer item);
}
