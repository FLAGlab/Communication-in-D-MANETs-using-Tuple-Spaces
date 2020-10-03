package com.example.jcbages.happnts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

class PeopleViewHolder extends RecyclerView.ViewHolder {

    private ImageView profilePic;
    private TextView title;
    private TextView subtitle;

    private Button ignoreButton;
    private Button viewMoreButton;

    private boolean isPeopleView;

    private IPersonClickListener listener;

    public PeopleViewHolder(View view, IPersonClickListener listener, boolean isPeopleView) {
        super(view);

        this.profilePic = view.findViewById(R.id.profilePic);
        this.title = view.findViewById(R.id.title);
        this.subtitle = view.findViewById(R.id.subtitle);

        this.isPeopleView = isPeopleView;

        this.listener = listener;

        this.ignoreButton = view.findViewById(R.id.ignore_button);
        this.viewMoreButton = view.findViewById(R.id.view_more_button);

        if (!this.isPeopleView) {
            this.ignoreButton.setVisibility(View.GONE);
            this.viewMoreButton.setVisibility(View.GONE);
        }
    }

    public void setPerson(Person person) {
        String title = String.format(Locale.US, "%s %d", person.name(), person.age());
        this.title.setText(title);
        this.subtitle.setText(person.shortBio());

        byte[] decodedString = Base64.decode(person.profilePic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        this.profilePic.setImageBitmap(decodedByte);

        if (this.isPeopleView) {
            this.ignoreButton.setOnClickListener(v ->
                this.listener.onIgnoreClicked(person)
            );

            this.viewMoreButton.setOnClickListener(v ->
                this.listener.onViewMoreClicked(person)
            );
        }
    }
}
