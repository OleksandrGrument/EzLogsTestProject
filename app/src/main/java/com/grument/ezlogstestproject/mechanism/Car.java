package com.grument.ezlogstestproject.mechanism;

import com.grument.ezlogstestproject.mechanism.base.Mechanism;


public class Car extends Mechanism {

    private final static String TYPE = "Car";

    public Car(String mechanismName) {
        super(mechanismName);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
