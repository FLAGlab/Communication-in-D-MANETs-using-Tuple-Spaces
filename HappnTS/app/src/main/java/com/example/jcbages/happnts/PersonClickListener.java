package com.example.jcbages.happnts;

import java.lang.ref.WeakReference;

class PersonClickListener implements IPersonClickListener {

    private WeakReference<MainActivity> mainActivity;

    public PersonClickListener(MainActivity activity) {
        this.mainActivity = new WeakReference<>(activity);
    }

    @Override
    public void onIgnoreClicked(Person person) {
        PeopleData.ignorePerson(person);
    }

    @Override
    public void onViewMoreClicked(Person person) {
        if (this.mainActivity.get() != null) {
            this.mainActivity.get().startDetailPersonActivity(person);
        }
    }
}
