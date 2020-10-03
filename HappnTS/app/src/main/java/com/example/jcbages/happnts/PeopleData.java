package com.example.jcbages.happnts;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PeopleData {

    public static final UUID OWNER_ID = UUID.randomUUID();

    private static List<Person> people = new ArrayList<>();

    private static List<Person> matches = new ArrayList<>();

    private static Set<UUID> peopleIds = new HashSet<>();

    private static Set<UUID> ignoredPeople = new HashSet<>();

    private static RecyclerView.Adapter peopleAdapter = null;

    private static RecyclerView.Adapter matchesAdapter = null;

    public static void setPeopleAdapter(RecyclerView.Adapter adapter) {
        peopleAdapter = adapter;
    }

    public static void setMatchesAdapter(RecyclerView.Adapter adapter) {
        matchesAdapter = adapter;
    }

    public static List<Person> people() {
        return people;
    }

    public static List<Person> matches() {
        return matches;
    }

    public static void addPerson(Person person) {
        if (!peopleIds.contains(person.id())) {
            peopleIds.add(person.id());
            people.add(person);
            tryUpdatePeopleAdapter();
        }
    }

    public static void ignorePerson(Person person) {
        if (!ignoredPeople.contains(person.id())) {
            ignoredPeople.add(person.id());

            int pos = -1;
            for (int i = 0; i < people.size() && pos == -1; ++i) {
                Person currentPerson = people.get(i);
                pos = currentPerson.id().equals(person.id()) ? i : pos;
            }
            if (pos != -1) {
                people.remove(pos);
                tryUpdatePeopleAdapter();
            }
        }
    }

    private static void tryUpdatePeopleAdapter() {
        if (peopleAdapter != null) {
            peopleAdapter.notifyDataSetChanged();
        }
    }

    private static void tryUpdateMatchesAdapter() {
        if (matchesAdapter != null) {
            matchesAdapter.notifyDataSetChanged();
        }
    }

    public static void addMatch(Person person) {
        matches.add(person);
        ignorePerson(person);
        tryUpdateMatchesAdapter();
    }
}
