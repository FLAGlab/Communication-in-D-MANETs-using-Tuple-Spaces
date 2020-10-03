package com.example.jcbages.disasterts;

import android.os.AsyncTask;
import android.util.Log;

import com.uniandes.jcbages10.tuplespace.ITuple;
import com.uniandes.jcbages10.tuplespace.ITupleSpace;
import com.uniandes.jcbages10.tuplespace.TupleSpace;

import java.lang.ref.WeakReference;
import java.util.concurrent.Future;

public class MessageAsyncTask extends AsyncTask<Void, Void, Message> {

    private final static String TAG = MessageAsyncTask.class.getName();

    private WeakReference<MainActivity> mainActivity;

    private static ITupleSpace TS = TupleSpace.getInstance();

    public MessageAsyncTask(MainActivity activity) {
        this.mainActivity = new WeakReference<>(activity);
    }

    @Override
    protected Message doInBackground(Void... voids) {
        ITuple inTuple = Message.inTuple(MessagesData.OWNER_ID);
        Future<ITuple> future = TS.in(inTuple);

        ITuple messageTuple = null;
        try {
            messageTuple = future.get();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        Log.v(TAG, "Receiving tuple...");

        if (messageTuple == null) {
            return null;
        }

        return Message.outTuple(messageTuple);
    }

    @Override
    protected void onPostExecute(Message message) {
        if (message != null) {
            MessagesData.addMessage(message, false);
        }

        // publish it again with me as an owner
        ITupleSpace TS = TupleSpace.getInstance();
        ITuple tuple = message.toTuple(MessagesData.OWNER_ID);
        TS.out(tuple);

        if (this.mainActivity.get() != null) {
            this.mainActivity.get().restartMessageAsyncTask();
        }
    }
}
