package com.grument.ezlogstestproject.thread;

import com.grument.ezlogstestproject.mechanism.MechanismResult;
import com.grument.ezlogstestproject.mechanism.base.Mechanism;
import com.grument.ezlogstestproject.sort.BubbleSort;
import com.grument.ezlogstestproject.sort.InsertionSort;
import com.grument.ezlogstestproject.sort.SelectionSort;
import com.grument.ezlogstestproject.sort.base.BaseSortedClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;


public class SortThread<T> extends Thread {


    public interface SortThreadCallback {
        void onEndSort(MechanismResult mechanismResult);
    }

    private MechanismResult mechanismResult;

    private SortThreadCallback sortThreadCallback;

    public SortThread(MechanismResult mechanismResult, SortThreadCallback sortThreadCallback) {
        this.mechanismResult = mechanismResult;
        this.sortThreadCallback = sortThreadCallback;
    }


    public void run() {

        try {

            List<Mechanism> mechanisms = (List<Mechanism>) mechanismResult.getMechanismCollection();

            BaseSortedClass randomSortType = generateRandomSortType();

            randomSortType.sort(mechanisms);

            mechanismResult.setStartSort(false);
            mechanismResult.setTime(System.currentTimeMillis());
            mechanismResult.setSortMethod("sorted");
            mechanismResult.setSortMethod(randomSortType.getSortType());

            sortThreadCallback.onEndSort(mechanismResult);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    // this method is generating random sort type for further use
    public BaseSortedClass generateRandomSortType() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        int type = new Random().nextInt(3) + 1;

        Class randomSortType = Mechanism.class;

        switch (type) {
            case 1:
                randomSortType = BubbleSort.class;
                break;
            case 2:
                randomSortType = InsertionSort.class;
                break;
            case 3:
                randomSortType = SelectionSort.class;
                break;
        }

        Constructor<T> constructor = randomSortType.getConstructor();

        BaseSortedClass randomSort = (BaseSortedClass) constructor.newInstance(new Object[]{});

        return randomSort;

    }

}
