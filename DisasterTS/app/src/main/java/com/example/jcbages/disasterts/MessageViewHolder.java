package com.example.jcbages.disasterts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView messageText;
    private TextView messageAuthor;
    private TextView messageDate;

    public MessageViewHolder(View view) {
        super(view);

        this.messageText = view.findViewById(R.id.message_text);
        this.messageAuthor = view.findViewById(R.id.author_text);
        this.messageDate = view.findViewById(R.id.created_at_text);
    }

    public void setMessage(Message message) {
        this.messageText.setText(message.text());
        this.messageAuthor.setText("Author: " + message.author());
        this.messageDate.setText("Created at: " + message.prettyDate());
    }

}
