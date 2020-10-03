package com.example.jcbages.disasterts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View messageCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card, parent, false);
        return new MessageViewHolder(messageCard);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int position) {
        Message message = this.messages.get(position);
        viewHolder.setMessage(message);
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }
}
