package com.grument.ezlogstestproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import com.grument.ezlogstestproject.mechanism.Car;
import com.grument.ezlogstestproject.mechanism.Plane;
import com.grument.ezlogstestproject.mechanism.Ship;
import com.grument.ezlogstestproject.mechanism.base.Mechanism;
import com.grument.ezlogstestproject.thread.ManagerThread;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SortingService extends Service {


    public static final String NOTIFICATION = "service.activity.notification";
    public static final String RESULT_KEY = "RESULT_KEY";

    SortingServiceBinder binder = new SortingServiceBinder();

    String[] mechanismNames = {"Toyota", "Siemens", "Cat", "Subaru", "Bosh", "Opel", "Matra", "Ferrari", "Clinton"};

    Random random = new Random();

    public void onCreate() {
        super.onCreate();

        HandlerThread mHandlerThread = new HandlerThread("LocalServiceThread");
        mHandlerThread.start();

        new Handler(mHandlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {

                final ManagerThread managerThread = new ManagerThread(SortingService.this);

                managerThread.start();


                // infinite produce and append new data for thread manager
                while (true) {
                    try {

                        managerThread.appendNewData(generateRandomMechanismCollection());

                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }



    // This method is for generating random collection of random mechanism for thread manager
    private <T> List<T> generateRandomMechanismCollection() {

        int type = random.nextInt(3) + 1;

        Class randomType = Mechanism.class;

        switch (type) {
            case 1:
                randomType = Car.class;
                break;
            case 2:
                randomType = Plane.class;
                break;
            case 3:
                randomType = Ship.class;
                break;
        }

        int countMechanismInCollection = random.nextInt(10) + 1;

        List<T> mechanismCollection = new ArrayList<>();

        for (int i = 0; i < countMechanismInCollection; i++) {


            try {

                String randomMechanismName = mechanismNames[random.nextInt(mechanismNames.length)];

                Constructor<T> constructor = randomType.getConstructor(String.class);
                T randomMechanism = constructor.newInstance(new Object[]{randomMechanismName});


                mechanismCollection.add(randomMechanism);

            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return mechanismCollection;
    }

    public IBinder onBind(Intent arg0) {
        return binder;
    }

    class SortingServiceBinder extends Binder {
        SortingService getService() {
            return SortingService.this;
        }
    }

}


