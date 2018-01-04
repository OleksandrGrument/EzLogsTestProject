package com.grument.ezlogstestproject.sort.base;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;


public abstract class BaseSortedClass<T extends Comparable> {

    public abstract <T extends Comparable> void sort(List<T> unsortedList);

    public abstract String getSortType();

}
