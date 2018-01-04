package com.grument.ezlogstestproject.mechanism;

import com.grument.ezlogstestproject.mechanism.base.Mechanism;

import java.io.Serializable;
import java.util.List;


public class MechanismResult implements Serializable {

    public MechanismResult(boolean isStartSort, String type, String sortMethod, List<Mechanism> mechanismCollection, long time) {
        this.isStartSort = isStartSort;
        this.type = type;
        this.sortMethod = sortMethod;
        this.mechanismCollection = mechanismCollection;
        this.time = time;
    }

    private boolean isStartSort;

    private String type;

    private String sortMethod;

    private List<Mechanism> mechanismCollection;

    private long time;

    public boolean isStartSort() {
        return isStartSort;
    }

    public String getType() {
        return type;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    public List<? extends Mechanism> getMechanismCollection() {
        return mechanismCollection;
    }

    public long getTime() {
        return time;
    }

    public void setStartSort(boolean startSort) {
        isStartSort = startSort;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }

    public void setMechanismCollection(List<Mechanism> mechanismCollection) {
        this.mechanismCollection = mechanismCollection;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
