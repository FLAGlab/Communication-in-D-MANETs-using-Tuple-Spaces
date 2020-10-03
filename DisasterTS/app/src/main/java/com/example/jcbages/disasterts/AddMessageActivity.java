package com.example.jcbages.disasterts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        EditText editText = findViewById(R.id.edit_text);
        Button addMessageButton = findViewById(R.id.add_button);

        addMessageButton.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                Message message = new Message(text, MessagesData.AUTHOR);
                MessagesData.addMessage(message, true);

                Toast toast = Toast.makeText(getApplicationContext(), "Message added", Toast.LENGTH_SHORT);
                toast.show();

                finish();
            }
        });
    }
}
