package com.grument.ezlogstestproject.mechanism.base;

import android.support.annotation.NonNull;

import java.io.Serializable;


public class Mechanism implements Comparable<Mechanism>, Serializable {

    private String mechanismName;

    private static final String TYPE = "Mechanism";

    public Mechanism(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public String getMechanismName() {
        return mechanismName;
    }

    private void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public int compareTo(@NonNull Mechanism mechanism) {
        return this.mechanismName.compareTo(mechanism.getMechanismName());
    }

}

