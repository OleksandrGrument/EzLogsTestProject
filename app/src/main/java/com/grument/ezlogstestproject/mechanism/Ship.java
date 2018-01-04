package com.grument.ezlogstestproject.mechanism;

import com.grument.ezlogstestproject.mechanism.base.Mechanism;


public class Ship extends Mechanism {

    private final static String TYPE = "Ship";

    public Ship(String mechanismName) {
        super(mechanismName);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
