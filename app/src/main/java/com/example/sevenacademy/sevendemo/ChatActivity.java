package com.example.sevenacademy.sevendemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import adapter.ChatAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chatField)
    EditText chatField;
    private String username;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.chatList)
    RecyclerView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        username = getIntent().getStringExtra("username");
        toolbar.setTitle("Chat from "+username);
        setSupportActionBar(toolbar);
        ChatAdapter chatAdapter = new ChatAdapter(this, Chat.class, R.layout.chat_item, ChatAdapter.ViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("messages"));
        chatList.setAdapter(chatAdapter);

    }

    @OnClick(R.id.fab)
    public void sendMessage(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("messages");
        Chat chat = new Chat();
        chat.setName(username);
        chat.setTime(System.currentTimeMillis());
        chat.setMessage(chatField.getText().toString());
        ref.push().setValue(chat)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    chatField.setText("");
                }
            }
        });
    }

    @OnClick(R.id.fabImage)
    public void sendImage(){
        //function to send image files
        Intent pickImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickImageIntent.setType("image/*");
        startActivityForResult(pickImageIntent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data.getData() != null){
            Toast.makeText(this, "Sending message", Toast.LENGTH_LONG).show();
            //upload image here
            final StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("images/" + System.currentTimeMillis() + ".jpg");
            UploadTask uploadTask = ref.putFile(data.getData());
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Chat chat = new Chat();
                        chat.setTime(System.currentTimeMillis());
                        chat.setMessage("New image sent");
                        chat.setName(username);
                        chat.setImage(task.getResult());;;;

                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("messages")
                                .push()
                                .setValue(chat);
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });
        }else{
            Toast.makeText(this, "Error image", Toast.LENGTH_LONG).show();
        }
    }
}
