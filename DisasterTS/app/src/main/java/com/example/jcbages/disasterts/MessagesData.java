package com.example.jcbages.disasterts;

import android.support.v7.widget.RecyclerView;

import com.uniandes.jcbages10.tuplespace.ITuple;
import com.uniandes.jcbages10.tuplespace.ITupleSpace;
import com.uniandes.jcbages10.tuplespace.TupleSpace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MessagesData {

    public static final UUID OWNER_ID = UUID.randomUUID();

    public static final String AUTHOR = "jcbages";

    private static final int MESSAGES_LIMIT = 100;

    private static List<Message> messages = new ArrayList<>();

    private static Set<UUID> addedMessages = new HashSet<>();

    private static RecyclerView.Adapter mAdapter;

    public static void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public static List<Message> messages() {
        return messages;
    }

    public static void addMessage(Message message, boolean publish) {
        if (!addedMessages.contains(message.id())) {
            addedMessages.add(message.id());

            messages.add(message);
            Collections.sort(messages);

            while (messages.size() >= MESSAGES_LIMIT) {
                int lastPos = messages.size() - 1;
                messages.remove(lastPos);
            }

            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }

            if (publish) {
                ITupleSpace TS = TupleSpace.getInstance();
                ITuple tuple = message.toTuple(MessagesData.OWNER_ID);
                TS.out(tuple);
            }
        }
    }

}
