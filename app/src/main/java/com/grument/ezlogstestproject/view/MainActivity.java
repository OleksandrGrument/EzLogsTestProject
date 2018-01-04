package com.grument.ezlogstestproject.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grument.ezlogstestproject.R;
import com.grument.ezlogstestproject.mechanism.MechanismResult;
import com.grument.ezlogstestproject.service.SortingService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView mechanismResultsRecyclerView;
    RecyclerViewMechanismAdapter recyclerViewMechanismAdapter;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ArrayList<MechanismResult> mechanismResultList = (ArrayList<MechanismResult>) bundle.getSerializable(SortingService.RESULT_KEY);
                recyclerViewMechanismAdapter.addResults(mechanismResultList);
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, SortingService.class));

        recyclerViewMechanismAdapter = new RecyclerViewMechanismAdapter(this);

        mechanismResultsRecyclerView = findViewById(R.id.rv_mechanism_results);
        mechanismResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mechanismResultsRecyclerView.setAdapter(recyclerViewMechanismAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                SortingService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
