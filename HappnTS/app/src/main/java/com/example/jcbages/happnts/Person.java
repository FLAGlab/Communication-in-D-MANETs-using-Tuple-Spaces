package com.example.jcbages.happnts;

import android.os.Parcel;
import android.os.Parcelable;

import com.uniandes.jcbages10.tuplespace.Field;
import com.uniandes.jcbages10.tuplespace.ITuple;
import com.uniandes.jcbages10.tuplespace.Tuple;

import java.util.UUID;

class Person implements Parcelable {

    public final static String TAG = Person.class.getName();

    private final static int ID_POS = 1;
    private final static int NAME_POS = 2;
    private final static int SHORT_BIO_POS = 3;
    private final static int AGE_POS = 4;
    private final static int PROFILE_PIC_POS = 5;
    private final static int EMAIL_POS = 6;
    private final static int PHONE_POS = 7;

    private UUID id;
    private String name;
    private String shortBio;
    private int age;
    private String profilePic;
    private String email;
    private long phone;

    private Person(UUID id, String name, String shortBio, int age, String profilePic, String email, long phone) {
        this.id = id;
        this.name = name;
        this.shortBio = shortBio;
        this.age = age;
        this.profilePic = profilePic;
        this.email = email;
        this.phone = phone;
    }

    public Person(String name, String shortBio, int age, String profilePic, String email, long phone) {
        this(UUID.randomUUID(), name, shortBio, age, profilePic, email, phone);
    }

    public Person(Parcel in) {
        id = UUID.fromString(in.readString());
        name = in.readString();
        shortBio = in.readString();
        age = in.readInt();
        profilePic = in.readString();
        email = in.readString();
        phone = in.readLong();
    }

    public UUID id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String shortBio() {
        return this.shortBio;
    }

    public int age() {
        return this.age;
    }

    public String email() {
        return this.email;
    }

    public long phone() {
        return this.phone;
    }

    public String profilePic() {
        return this.profilePic;
    }

    public ITuple toTuple(UUID owner) {
        long leasingTime = Long.MAX_VALUE;
        return new Tuple(
            owner,
            leasingTime,
            new Field<>(String.class, TAG),
            new Field<>(UUID.class, this.id),
            new Field<>(String.class, this.name),
            new Field<>(String.class, this.shortBio),
            new Field<>(Integer.class, this.age),
            new Field<>(String.class, this.profilePic),
            new Field<>(String.class, this.email),
            new Field<>(Long.class, this.phone)
        );
    }

    public static ITuple inTuple(UUID owner) {
        return new Tuple(
            owner,
            new Field<>(String.class, TAG),
            new Field<>(UUID.class),
            new Field<>(String.class),
            new Field<>(String.class),
            new Field<>(Integer.class),
            new Field<>(String.class),
            new Field<>(String.class),
            new Field<>(Long.class)
        );
    }

    public static Person outTuple(ITuple tuple) {
        return new Person(
            (UUID) tuple.get(ID_POS).element(),
            (String) tuple.get(NAME_POS).element(),
            (String) tuple.get(SHORT_BIO_POS).element(),
            (Integer) tuple.get(AGE_POS).element(),
            (String) tuple.get(PROFILE_PIC_POS).element(),
            (String) tuple.get(EMAIL_POS).element(),
            (Long) tuple.get(PHONE_POS).element()
        );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id.toString());
        dest.writeString(this.name);
        dest.writeString(this.shortBio);
        dest.writeInt(this.age);
        dest.writeString(this.profilePic);
        dest.writeString(this.email);
        dest.writeLong(this.phone);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
