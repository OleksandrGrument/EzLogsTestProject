package com.grument.ezlogstestproject.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grument.ezlogstestproject.R;
import com.grument.ezlogstestproject.mechanism.MechanismResult;
import com.grument.ezlogstestproject.mechanism.base.Mechanism;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMechanismAdapter extends RecyclerView.Adapter<RecyclerViewMechanismAdapter.ViewHolder> {

    private List<MechanismResult> mechanismResults = new ArrayList<>();

    private Context context;

    public RecyclerViewMechanismAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_mechanism, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        MechanismResult mechanismResult = mechanismResults.get(position);

        StringBuilder mechanismListStringBuilder = new StringBuilder();

        mechanismListStringBuilder.append(context.getString(R.string.list_of));
        for (Mechanism mechanism : mechanismResult.getMechanismCollection()) {
            mechanismListStringBuilder.append("\n \t");
            mechanismListStringBuilder.append(mechanism.getMechanismName());
        }


        String mechanismType = context.getString(R.string.list) + mechanismResult.getType();
        String sortMethod = context.getString(R.string.method) + mechanismResult.getSortMethod();
        String time = context.getString(R.string.time) + mechanismResult.getTime();


        if (mechanismResult.isStartSort())
            view.startSortTextView.setText(R.string.start_sort);
        else
            view.startSortTextView.setText(R.string.end_sort);

        view.mechanismTypeTextView.setText(mechanismType);
        view.sortMethodTextView.setText(sortMethod);
        view.timeTextView.setText(time);
        view.listMechanismTextView.setText(mechanismListStringBuilder.toString());

    }

    @Override
    public int getItemCount() {
        return mechanismResults.size();
    }

    public void addResults(List<MechanismResult> newMechanismResults) {
        mechanismResults.addAll(newMechanismResults);
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView startSortTextView, mechanismTypeTextView, sortMethodTextView, timeTextView, listMechanismTextView;

        ViewHolder(View view) {
            super(view);
            this.setIsRecyclable(false);

            startSortTextView = view.findViewById(R.id.tv_start_sort);
            mechanismTypeTextView = view.findViewById(R.id.tv_mechanism_type);
            sortMethodTextView = view.findViewById(R.id.tv_sort_method);
            timeTextView = view.findViewById(R.id.tv_time);
            listMechanismTextView = view.findViewById(R.id.tv_mechanism_list);

        }
    }

}
