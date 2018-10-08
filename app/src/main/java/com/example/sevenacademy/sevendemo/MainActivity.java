package com.example.sevenacademy.sevendemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.emailField)
    EditText emailEditText;
    @BindView(R.id.passwordField)
    EditText passwordEditText;
    //auth
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            startChatActivity(currentUser);
        }
    }

    @OnClick(R.id.loginButton)
    public void clickLogin(){
        Toast.makeText(this, "Signing in please wait ...", Toast.LENGTH_LONG).show();
        //login on firebase
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //user login
                            startChatActivity(mAuth.getCurrentUser());
                        }else{
                            Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void startChatActivity(FirebaseUser user) {
        //start new activity;
        String username = user.getDisplayName();
        String email = user.getEmail();
        String id = user.getUid();
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("username", email);
        intent.putExtra("id", id);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}
