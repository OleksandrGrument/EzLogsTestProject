package com.grument.ezlogstestproject.sort;

import com.grument.ezlogstestproject.sort.base.BaseSortedClass;

import java.util.List;

public class SelectionSort<T extends Comparable> extends BaseSortedClass<T> {

    private final static String SORT_TYPE = "Selection Sort";

    @Override
    public <T extends Comparable> void sort(List<T> unsortedList) {

        for (int i = 0; i < unsortedList.size() - 1; i++) {
            for (int j = i + 1; j < unsortedList.size(); j++) {
                if ((unsortedList.get(i).compareTo(unsortedList.get(j))) > 0) {
                    T temp = unsortedList.get(j);

                    unsortedList.set(j, unsortedList.get(i));
                    unsortedList.set(i, temp);
                }
            }
        }
    }

    @Override
    public String getSortType() {
        return SORT_TYPE;
    }

}

