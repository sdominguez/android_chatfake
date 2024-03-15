package com.sdi.chatfake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.sdi.chatfake.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Message> msgList;
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        msgList = new ArrayList<>();
        msgList.add(new Message(0,"Hola bienvenido al FakeChat"));
        msgList.add(new Message(0,"escribe algo"));

        adapter = new MessageAdapter();
        binding.recycler.setAdapter(adapter);
        adapter.submitList(msgList);

        binding.buttonSend.setOnClickListener(v->{
            String newMsg = binding.editTextMessage.getText().toString();
            if(!newMsg.isEmpty()){
                msgList.add(new Message(1, newMsg));
                adapter.notifyItemInserted(msgList.size()-1);
                binding.editTextMessage.getText().clear();
                binding.recycler.smoothScrollToPosition(msgList.size() - 1);
            }

        });

    }
}