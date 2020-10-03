package com.example.jcbages.disasterts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.uniandes.jcbages10.routing.IMessage;
import com.uniandes.jcbages10.routing.Message;
import com.uniandes.jcbages10.routing.Routing;
import com.uniandes.jcbages10.tuplespace.ITuple;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mRecyclerView = findViewById(R.id.recycler_view);

        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);

        this.mAdapter = new MessageAdapter(MessagesData.messages());
        this.mRecyclerView.setAdapter(this.mAdapter);

        MessagesData.setAdapter(this.mAdapter);
        restartMessageAsyncTask();

        Button viewMatches = findViewById(R.id.add_message_button);
        viewMatches.setOnClickListener(v -> {
            startAddMessageActivity();
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            Log.v(TAG, "Adding tuple...");

            com.example.jcbages.disasterts.Message message = new com.example.jcbages.disasterts.Message(
                    "ashiod asiasioas odihas i asiodas iod asod asodahsodhas odhas iod iohdio hdaio dhasoi.",
                    "anafj"
            );

            List<IMessage<ITuple>> messages = new ArrayList<>();
            messages.add(new Message<>(message.toTuple(UUID.randomUUID())));
            Routing.getInstance().receiveMessages(messages);
        });
    }

    private void startAddMessageActivity() {
        Intent intent = new Intent(this, AddMessageActivity.class);
        startActivity(intent);
    }

    public void restartMessageAsyncTask() {
        MessageAsyncTask asyncTask = new MessageAsyncTask(this);
        asyncTask.execute();
    }
}
