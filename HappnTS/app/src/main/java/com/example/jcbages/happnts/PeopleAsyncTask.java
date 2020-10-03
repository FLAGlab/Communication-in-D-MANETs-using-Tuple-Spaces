package com.example.jcbages.happnts;

import android.os.AsyncTask;
import android.util.Log;

import com.uniandes.jcbages10.tuplespace.ITuple;
import com.uniandes.jcbages10.tuplespace.ITupleSpace;
import com.uniandes.jcbages10.tuplespace.TupleSpace;

import java.lang.ref.WeakReference;
import java.util.concurrent.Future;

public class PeopleAsyncTask extends AsyncTask<Void, Void, Person> {

    private static final String TAG = PeopleAsyncTask.class.getName();

    private WeakReference<MainActivity> mainActivity;

    private final static ITupleSpace TS = TupleSpace.getInstance();

    public PeopleAsyncTask(MainActivity activity) {
        mainActivity = new WeakReference<>(activity);
    }

    @Override
    protected Person doInBackground(Void... args) {
        ITuple inTuple = Person.inTuple(PeopleData.OWNER_ID);
        Future<ITuple> future = TS.in(inTuple);

        ITuple personTuple = null;
        try {
            personTuple = future.get();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        if (personTuple == null) {
            return null;
        }

        return Person.outTuple(personTuple);
    }

    @Override
    protected void onPostExecute(Person person) {
        if (person != null) {
            PeopleData.addPerson(person);
        }

        if (this.mainActivity.get() != null) {
            this.mainActivity.get().restartAsyncTask();
        }
    }

}
