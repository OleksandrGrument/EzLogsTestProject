package com.grument.ezlogstestproject.sort;

import com.grument.ezlogstestproject.sort.base.BaseSortedClass;

import java.util.List;


public class BubbleSort<T extends Comparable> extends BaseSortedClass<T> {

    private final static String SORT_TYPE = "Bubble Sort";


    public <T extends Comparable> void sort(List<T> unsortedList) {

        for (int iter = 1; iter < unsortedList.size(); iter++) {

            for (int inner = 0; inner < (unsortedList.size() - iter); inner++) {
                if (unsortedList.get(inner).compareTo(unsortedList.get(inner + 1)) > 0) {
                    T tmp = unsortedList.get(inner);
                    unsortedList.set(inner, unsortedList.get(inner + 1));
                    unsortedList.set(inner + 1, tmp);
                }
            }
        }
    }

    @Override
    public String getSortType() {
        return SORT_TYPE;
    }


}
