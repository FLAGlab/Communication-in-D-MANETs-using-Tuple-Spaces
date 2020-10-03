package com.example.jcbages.happnts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MatchesAdapter extends RecyclerView.Adapter<PeopleViewHolder> {

    private List<Person> mDataset;

    public MatchesAdapter(List<Person> matches) {
        this.mDataset = matches;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View peopleCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_card, parent, false);
        return new PeopleViewHolder(peopleCard, null, false);
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
