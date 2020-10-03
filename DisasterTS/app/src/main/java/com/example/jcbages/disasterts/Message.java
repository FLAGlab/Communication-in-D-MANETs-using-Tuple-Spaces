package com.example.jcbages.disasterts;

import com.uniandes.jcbages10.tuplespace.Field;
import com.uniandes.jcbages10.tuplespace.ITuple;
import com.uniandes.jcbages10.tuplespace.Tuple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Message implements Comparable<Message> {

    public final static String TAG = Message.class.getName();

    private final static int ID_POS = 1;
    private final static int TEXT_POS = 2;
    private final static int AUTHOR_POS = 3;
    private final static int DATE_POS = 4;

    private UUID id;
    private String text;
    private String author;
    private Date date;

    private Message(UUID id, String text, String author, Date date) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public Message(String text, String author) {
        this(UUID.randomUUID(), text, author, new Date());
    }

    public UUID id() {
        return this.id;
    }

    public String text() {
        return this.text;
    }

    public String author() {
        return this.author;
    }

    public String prettyDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        return format.format(this.date);
    }

    public ITuple toTuple(UUID owner) {
        long leasingTime = Long.MAX_VALUE;
        return new Tuple(
            owner,
            leasingTime,
            new Field<>(String.class, TAG),
            new Field<>(UUID.class, this.id),
            new Field<>(String.class, this.text),
            new Field<>(String.class, this.author),
            new Field<>(Date.class, this.date)
        );
    }

    public static ITuple inTuple(UUID owner) {
        return new Tuple(
            owner,
            new Field<>(String.class, TAG),
            new Field<>(UUID.class),
            new Field<>(String.class),
            new Field<>(String.class),
            new Field<>(Date.class)
        );
    }

    public static Message outTuple(ITuple tuple) {
        return new Message(
            (UUID) tuple.get(ID_POS).element(),
            (String) tuple.get(TEXT_POS).element(),
            (String) tuple.get(AUTHOR_POS).element(),
            (Date) tuple.get(DATE_POS).element()
        );
    }

    public Date date() {
        return this.date;
    }

    @Override
    public int compareTo(Message that) {
        long time1 = this.date().getTime();
        long time2 = that.date().getTime();
        return -Long.compare(time1, time2);
    }

}
