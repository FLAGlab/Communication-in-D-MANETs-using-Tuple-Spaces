package com.example.jcbages.happnts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonDetailActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView name;
    private TextView shortBio;
    private TextView age;
    private TextView email;
    private TextView phone;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detail);

        Intent intent = getIntent();
        Person person = intent.getParcelableExtra(Person.TAG);

        this.profilePic = findViewById(R.id.profilePic);
        this.name = findViewById(R.id.name);
        this.shortBio = findViewById(R.id.shortBio);
        this.age = findViewById(R.id.age);
        this.email = findViewById(R.id.email);
        this.phone = findViewById(R.id.phone);

        byte[] decodedString = Base64.decode(person.profilePic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        this.profilePic.setImageBitmap(decodedByte);

        this.name.setText("Name :" + person.name());
        this.shortBio.setText("Bio: " + person.shortBio());
        this.age.setText("Age: " + person.age());
        this.email.setText("Email: " + person.email());
        this.phone.setText("Phone: " + person.phone());

        this.saveButton = findViewById(R.id.save_button);
        this.saveButton.setOnClickListener(v -> {
            PeopleData.addMatch(person);

            Toast toast = Toast.makeText(getApplicationContext(), "Added to matches", Toast.LENGTH_SHORT);
            toast.show();

            finish();
        });
    }

}
