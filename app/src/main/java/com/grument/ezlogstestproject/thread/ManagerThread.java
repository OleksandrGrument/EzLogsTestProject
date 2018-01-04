package com.grument.ezlogstestproject.thread;

import android.content.Context;
import android.content.Intent;

import com.grument.ezlogstestproject.mechanism.MechanismResult;
import com.grument.ezlogstestproject.mechanism.base.Mechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import static com.grument.ezlogstestproject.service.SortingService.NOTIFICATION;
import static com.grument.ezlogstestproject.service.SortingService.RESULT_KEY;


public class ManagerThread<T> extends Thread {

    private LinkedBlockingDeque<List<T>> randomMechanismDeque = new LinkedBlockingDeque<>();

    private LinkedBlockingDeque<MechanismResult> sortedMechanismResults = new LinkedBlockingDeque<>();

    private Context context;

    private boolean isReadyForNextThreeSortThreads = true;

    public ManagerThread(Context context) {
        this.context = context;
    }

    public void run() {

        while (this.isAlive()) {


            // check if ready for new part of random mechanism
            if (randomMechanismDeque.size() >= 3 && isReadyForNextThreeSortThreads) {

                isReadyForNextThreeSortThreads = false;

                ArrayList<MechanismResult> unsortedMechanism = new ArrayList<>();

                // generating the mechanism results for further publishing
                for (int i = 0; i < 3; i++) {

                    List<Mechanism> mechanisms = (List<Mechanism>) randomMechanismDeque.poll();
                    String type = mechanisms.get(0).getType();

                    MechanismResult mechanismResult = new MechanismResult(true, type, "unsorted", mechanisms, System.currentTimeMillis());
                    unsortedMechanism.add(mechanismResult);
                }


                publishResults(unsortedMechanism);

                // starting new threads with callback with results
                for (MechanismResult mechanismResult : unsortedMechanism) {

                    new SortThread(mechanismResult, new SortThread.SortThreadCallback() {

                        @Override
                        public void onEndSort(MechanismResult mechanismResult) {
                            getResultsFromSortingThread(mechanismResult);
                        }
                    }).run();
                }

            }

        }

    }

    //this method to consume results from sort threads
    private void getResultsFromSortingThread(MechanismResult mechanismResult) {

        sortedMechanismResults.add(mechanismResult);
        if (sortedMechanismResults.size() >= 3) {

            ArrayList<MechanismResult> mechanismResultsToSend = new ArrayList<>();
            sortedMechanismResults.drainTo(mechanismResultsToSend);
            publishResults(mechanismResultsToSend);
            isReadyForNextThreeSortThreads = true;

        }

    }

    //this method is for append new data in thread manager
    public void appendNewData(List<T> mechanisms) {
        randomMechanismDeque.add(mechanisms);
    }


    //this method for publishing results to activity
    private void publishResults(ArrayList<MechanismResult> mechanismResults) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT_KEY, mechanismResults);
        context.sendBroadcast(intent);
    }

}
