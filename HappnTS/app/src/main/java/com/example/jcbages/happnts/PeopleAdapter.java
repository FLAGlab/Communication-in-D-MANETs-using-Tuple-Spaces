package com.example.jcbages.happnts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {

    private List<Person> mDataset;

    private IPersonClickListener listener;

    public PeopleAdapter(List<Person> dataset, IPersonClickListener listener) {
        this.mDataset = dataset;
        this.listener = listener;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View peopleCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_card, parent, false);
        return new PeopleViewHolder(peopleCard, listener, true);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder viewHolder, int position) {
        Person person = this.mDataset.get(position);
        viewHolder.setPerson(person);
    }

    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }
}
