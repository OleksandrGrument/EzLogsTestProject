package com.grument.ezlogstestproject.mechanism;

import com.grument.ezlogstestproject.mechanism.base.Mechanism;


public class Plane extends Mechanism {

    private final static String TYPE = "Plane";

    public Plane(String mechanismName) {
        super(mechanismName);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
