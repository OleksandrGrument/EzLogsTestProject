package com.grument.ezlogstestproject.sort;

import com.grument.ezlogstestproject.sort.base.BaseSortedClass;

import java.util.List;


public class InsertionSort<T extends Comparable> extends BaseSortedClass<T> {

    private final static String SORT_TYPE = "Insertion Sort";

    @Override
    public <T extends Comparable> void sort(List<T> unsortedList) {

        int i, j;
        T key;

        for (j = 1; j < unsortedList.size(); j++) {
            key = unsortedList.get(j);
            i = j - 1;
            while (i >= 0) {

                if ((key.compareTo(unsortedList.get(i))) > 0) {
                    break;
                }
                unsortedList.set(i + 1, unsortedList.get(i));
                i--;
            }
            unsortedList.set(i + 1, key);
        }

    }

    @Override
    public String getSortType() {
        return SORT_TYPE;
    }

}
