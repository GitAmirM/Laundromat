package com.example.laundromatsystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText UserTxt;
    EditText PassTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        UserTxt = findViewById(R.id.EmailAddress);
        PassTxt = findViewById(R.id.Password);
    }
    public String getUsername(){
        String user = UserTxt.getText().toString();
        return user;
    }
    public String getPass(){
        String pass = PassTxt.getText().toString();
        return pass;
    }
    public void onLogin(View v)
    {
        if (v.getId()== R.id.btnLogin) {
            mAuth.signInWithEmailAndPassword(UserTxt.getText().toString(),
                            PassTxt.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("username", user.getEmail());
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        if (v.getId() == R.id.btnSign)
        {
            mAuth.createUserWithEmailAndPassword(UserTxt.getText().toString(), PassTxt.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Create User With Email : success \nPlease login",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d("TAG", "createUserWithEmail:failure: " + task.getException().toString() );
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }

}